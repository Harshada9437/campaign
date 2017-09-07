package com.campaign.dao;

import com.campaign.dao.UtilClasses.ConnectionHandler;
import com.campaign.dto.campaign.CampaignDTO;
import com.campaign.rest.request.campaign.HeaderDetails;
import com.campaign.rest.request.campaign.SlotDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CampaignDAO {
    public String createCampaign(CampaignDTO campaignDTO) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        StringBuilder query = new StringBuilder("INSERT INTO campaign_master(name,desccription,no_of_person,is_allow_on_full,confirm_sms,confirm_email,notify_email,campaign_header_id,slot_full_sms,campaign_over_txt,campaign_admin,link_hash_id) values (?,?,?,?,?,?,?,?,?,?,?,?)");
        String id = "";
        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement(query.toString());
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getName());
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getDesc());
            preparedStatement.setInt(parameterIndex++,
                    campaignDTO.getNoOfPerson());
            preparedStatement.setInt(parameterIndex++,
                    campaignDTO.getIsAllowOnFull());
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getConfirmSms());
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getConfirmEmail());
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getNotifyEmail());
            preparedStatement.setInt(parameterIndex++,
                    campaignDTO.getHeaderId());
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getSlotFullText());
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getCampaignOverText());
            preparedStatement.setInt(parameterIndex++,
                    campaignDTO.getCreatedBy());
            preparedStatement.setString(parameterIndex++,
                    UUID.randomUUID().toString());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }
            try {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int uniqueId = generatedKeys.getInt(1);
                    id=getCampaignInfoById(uniqueId).getHashId();
                    for (String date : campaignDTO.getDates()) {
                        for (SlotDetails slot : campaignDTO.getSlots()) {
                            insertSlots(date, slot, uniqueId, connection);
                        }
                    }

                    connection.commit();
                } else{
                    throw new SQLException(
                            "Creating campaign failed, no ID obtained.");
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                throw e;
            }
        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
            throw sqlException;
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    private void insertSlots(String date, SlotDetails slot, int uniqueId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        StringBuilder query = new StringBuilder("INSERT INTO slots(date,time_slot,capacity,campaign_master_id) values (?,?,?,?)");
        try {
            int parameterIndex = 1;
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(parameterIndex++, date);
            preparedStatement.setString(parameterIndex++, slot.getTime());
            preparedStatement.setInt(parameterIndex++, slot.getCapacity());
            preparedStatement.setInt(parameterIndex++, uniqueId);

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException sqlException) {
            connection.rollback();
            sqlException.printStackTrace();
            throw sqlException;
        }
    }

    public List<CampaignDTO> getCampaigns(int campaignAdmin) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        List<CampaignDTO> campaigns = new ArrayList<CampaignDTO>();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM campaign_master where campaign_admin="+campaignAdmin);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                CampaignDTO campaignDTO = new CampaignDTO();
                campaignDTO.setId(resultSet.getInt("id"));
                campaignDTO.setName(resultSet.getString("name"));
                campaignDTO.setDesc(resultSet.getString("desccription"));
                campaignDTO.setNoOfPerson(resultSet.getInt("no_of_person"));
                campaignDTO.setIsAllowOnFull(resultSet.getInt("is_allow_on_full"));
                campaignDTO.setCreatedBy(resultSet.getInt("campaign_admin"));
                campaignDTO.setCampaignOverText(resultSet.getString("campaign_over_txt"));
                campaignDTO.setSlotFullText(resultSet.getString("slot_full_sms"));
                campaignDTO.setConfirmEmail(resultSet.getString("confirm_email"));
                campaignDTO.setConfirmSms(resultSet.getString("confirm_sms"));
                campaignDTO.setNotifyEmail(resultSet.getString("notify_email"));
                campaignDTO.setHeaderId(resultSet.getInt("campaign_header_id"));
                HeaderDetails headerDetails = HeaderDAO.getHeaderSetting(campaignDTO.getHeaderId());
                campaignDTO.setHeader(headerDetails);
                campaignDTO.setSms(SmsDAO.getSmsSetting(headerDetails.getSmsId()));
                campaignDTO.setSmtp(SmtpDAO.getSmtpSetting(headerDetails.getSmtpId()));
                campaigns.add(campaignDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return campaigns;

    }

    public CampaignDTO getCampaignInfo(String campaignId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        CampaignDTO campaignDTO = new CampaignDTO();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM campaign_master where link_hash_id='"+campaignId+"'");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {

                campaignDTO.setId(resultSet.getInt("id"));
                campaignDTO.setName(resultSet.getString("name"));
                campaignDTO.setDesc(resultSet.getString("desccription"));
                campaignDTO.setNoOfPerson(resultSet.getInt("no_of_person"));
                campaignDTO.setIsAllowOnFull(resultSet.getInt("is_allow_on_full"));
                campaignDTO.setCreatedBy(resultSet.getInt("campaign_admin"));
                campaignDTO.setCampaignOverText(resultSet.getString("campaign_over_txt"));
                campaignDTO.setSlotFullText(resultSet.getString("slot_full_sms"));
                campaignDTO.setConfirmEmail(resultSet.getString("confirm_email"));
                campaignDTO.setConfirmSms(resultSet.getString("confirm_sms"));
                campaignDTO.setNotifyEmail(resultSet.getString("notify_email"));
                campaignDTO.setHeaderId(resultSet.getInt("campaign_header_id"));
                HeaderDetails headerDetails = HeaderDAO.getHeaderSetting(campaignDTO.getHeaderId());
                campaignDTO.setHeader(headerDetails);
                campaignDTO.setSms(SmsDAO.getSmsSetting(headerDetails.getSmsId()));
                campaignDTO.setSmtp(SmtpDAO.getSmtpSetting(headerDetails.getSmtpId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return campaignDTO;

    }
    public CampaignDTO getCampaignInfoById(int campaignId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        CampaignDTO campaignDTO = new CampaignDTO();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM campaign_master where id="+campaignId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {

                campaignDTO.setId(resultSet.getInt("id"));
                campaignDTO.setName(resultSet.getString("name"));
                campaignDTO.setDesc(resultSet.getString("desccription"));
                campaignDTO.setNoOfPerson(resultSet.getInt("no_of_person"));
                campaignDTO.setIsAllowOnFull(resultSet.getInt("is_allow_on_full"));
                campaignDTO.setCampaignOverText(resultSet.getString("campaign_over_txt"));
                campaignDTO.setSlotFullText(resultSet.getString("slot_full_sms"));
                campaignDTO.setConfirmEmail(resultSet.getString("confirm_email"));
                campaignDTO.setConfirmSms(resultSet.getString("confirm_sms"));
                campaignDTO.setNotifyEmail(resultSet.getString("notify_email"));
                campaignDTO.setHeaderId(resultSet.getInt("campaign_header_id"));
                HeaderDetails headerDetails = HeaderDAO.getHeaderSetting(campaignDTO.getHeaderId());
                campaignDTO.setHeader(headerDetails);
                campaignDTO.setSms(SmsDAO.getSmsSetting(headerDetails.getSmsId()));
                campaignDTO.setHashId(resultSet.getString("link_hash_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return campaignDTO;

    }
}
