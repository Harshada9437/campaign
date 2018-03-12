package com.campaign.dao;

import com.campaign.Exception.CampaignNotFoundException;
import com.campaign.dao.UtilClasses.ConnectionHandler;
import com.campaign.dto.campaign.CampaignDTO;
import com.campaign.dto.campaign.CampaignSlotDTO;
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
        StringBuilder query = new StringBuilder("INSERT INTO campaign_master(isPromoCampaign,campaign_location,email_subject,is_published,name,description,no_of_person,is_allow_on_full,confirm_sms,confirm_email,notify_email,campaign_header_id,slot_full_sms,campaign_over_text,campaign_admin,link_hash_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        String id = "";
        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement(query.toString());
            preparedStatement.setInt(parameterIndex++, campaignDTO.getIsPromoCampaign());
            preparedStatement.setString(parameterIndex++, campaignDTO.getCampaignLocation());
            preparedStatement.setString(parameterIndex++, campaignDTO.getEmailSubject());
            preparedStatement.setInt(parameterIndex++, campaignDTO.getIsPublished());
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
                    id = getCampaignInfoById(uniqueId).getHashId();
                    for (String date : campaignDTO.getDates()) {
                        for (SlotDetails slot : campaignDTO.getSlots()) {
                            insertSlots(date, slot, uniqueId, connection);
                        }
                    }

                    connection.commit();
                } else {
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

    public void insertSlots(String date, SlotDetails slot, int uniqueId, Connection connection) throws SQLException {
        PreparedStatement preparedStatement;
        StringBuilder query = new StringBuilder("INSERT INTO slots(date,time_slot,capacity,campaign_master_id,total_count) values (?,?,?,?,?)");
        connection.setAutoCommit(false);
        try {
            int parameterIndex = 1;
            preparedStatement = connection.prepareStatement(query.toString());
            preparedStatement.setString(parameterIndex++, date);
            preparedStatement.setString(parameterIndex++, slot.getTime());
            preparedStatement.setInt(parameterIndex++, slot.getCapacity());
            preparedStatement.setInt(parameterIndex++, uniqueId);
            preparedStatement.setInt(parameterIndex++, slot.getCapacity());

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
            StringBuilder query = new StringBuilder("SELECT * FROM campaign_master where campaign_admin=" + campaignAdmin);
            ResultSet resultSet = statement.executeQuery(query.toString());

            while (resultSet.next()) {
                CampaignDTO campaignDTO = new CampaignDTO();
                campaignDTO.setIsPromoCampaign(resultSet.getInt("isPromoCampaign"));
                campaignDTO.setCampaignLocation(resultSet.getString("campaign_location"));
                campaignDTO.setId(resultSet.getInt("id"));
                campaignDTO.setStatus(resultSet.getString("status"));
                campaignDTO.setIsPublished(resultSet.getInt("is_published"));
                campaignDTO.setName(resultSet.getString("name"));
                campaignDTO.setEmailSubject(resultSet.getString("email_subject"));
                campaignDTO.setDesc(resultSet.getString("description"));
                campaignDTO.setNoOfPerson(resultSet.getInt("no_of_person"));
                campaignDTO.setIsAllowOnFull(resultSet.getInt("is_allow_on_full"));
                campaignDTO.setCreatedBy(resultSet.getInt("campaign_admin"));
                campaignDTO.setCampaignOverText(resultSet.getString("campaign_over_text"));
                campaignDTO.setSlotFullText(resultSet.getString("slot_full_sms"));
                campaignDTO.setConfirmEmail(resultSet.getString("confirm_email"));
                campaignDTO.setConfirmSms(resultSet.getString("confirm_sms"));
                campaignDTO.setNotifyEmail(resultSet.getString("notify_email"));
                campaignDTO.setLinkHashId(resultSet.getString("link_hash_id"));
                campaignDTO.setHeaderId(resultSet.getInt("campaign_header_id"));
                campaignDTO.setIsPublished(resultSet.getInt("is_published"));
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

    public CampaignDTO getCampaignInfo(String campaignId) throws SQLException, CampaignNotFoundException {
        Connection connection = null;
        Statement statement = null;
        CampaignDTO campaignDTO = new CampaignDTO();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM campaign_master where link_hash_id='" + campaignId + "'");
            ResultSet resultSet = statement.executeQuery(query.toString());
            int i = 0;
            while (resultSet.next()) {
                i++;
                campaignDTO.setId(resultSet.getInt("id"));
                campaignDTO.setIsPromoCampaign(resultSet.getInt("isPromoCampaign"));
                campaignDTO.setCampaignLocation(resultSet.getString("campaign_location"));
                campaignDTO.setStatus(resultSet.getString("status"));
                campaignDTO.setName(resultSet.getString("name"));
                campaignDTO.setDesc(resultSet.getString("description"));
                campaignDTO.setNoOfPerson(resultSet.getInt("no_of_person"));
                campaignDTO.setIsAllowOnFull(resultSet.getInt("is_allow_on_full"));
                campaignDTO.setCreatedBy(resultSet.getInt("campaign_admin"));
                campaignDTO.setCampaignOverText(resultSet.getString("campaign_over_text"));
                campaignDTO.setSlotFullText(resultSet.getString("slot_full_sms"));
                campaignDTO.setConfirmEmail(resultSet.getString("confirm_email"));
                campaignDTO.setConfirmSms(resultSet.getString("confirm_sms"));
                campaignDTO.setNotifyEmail(resultSet.getString("notify_email"));
                campaignDTO.setHeaderId(resultSet.getInt("campaign_header_id"));
                campaignDTO.setIsPublished(resultSet.getInt("is_published"));
                HeaderDetails headerDetails = HeaderDAO.getHeaderSetting(campaignDTO.getHeaderId());
                campaignDTO.setHeader(headerDetails);
                campaignDTO.setSms(SmsDAO.getSmsSetting(headerDetails.getSmsId()));
                campaignDTO.setSmtp(SmtpDAO.getSmtpSetting(headerDetails.getSmtpId()));
                campaignDTO.setEmailSubject(resultSet.getString("email_subject"));
            }
            if (i == 0) {
                throw new CampaignNotFoundException("Campaign not found invalid campaign id.");
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
            StringBuilder query = new StringBuilder("SELECT * FROM campaign_master where id=" + campaignId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {

                campaignDTO.setId(resultSet.getInt("id"));
                campaignDTO.setIsPromoCampaign(resultSet.getInt("isPromoCampaign"));
                campaignDTO.setCampaignLocation(resultSet.getString("campaign_location"));
                campaignDTO.setStatus(resultSet.getString("status"));
                campaignDTO.setName(resultSet.getString("name"));
                campaignDTO.setDesc(resultSet.getString("description"));
                campaignDTO.setNoOfPerson(resultSet.getInt("no_of_person"));
                campaignDTO.setIsAllowOnFull(resultSet.getInt("is_allow_on_full"));
                campaignDTO.setCampaignOverText(resultSet.getString("campaign_over_text"));
                campaignDTO.setSlotFullText(resultSet.getString("slot_full_sms"));
                campaignDTO.setConfirmEmail(resultSet.getString("confirm_email"));
                campaignDTO.setConfirmSms(resultSet.getString("confirm_sms"));
                campaignDTO.setNotifyEmail(resultSet.getString("notify_email"));
                campaignDTO.setHeaderId(resultSet.getInt("campaign_header_id"));
                campaignDTO.setIsPublished(resultSet.getInt("is_published"));
                HeaderDetails headerDetails = HeaderDAO.getHeaderSetting(campaignDTO.getHeaderId());
                campaignDTO.setHeader(headerDetails);
                campaignDTO.setSms(SmsDAO.getSmsSetting(headerDetails.getSmsId()));
                campaignDTO.setSmtp(SmtpDAO.getSmtpSetting(headerDetails.getSmtpId()));
                campaignDTO.setHashId(resultSet.getString("link_hash_id"));
                campaignDTO.setEmailSubject(resultSet.getString("email_subject"));
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

    public String getStatus(String campaignId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String status = "";
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM campaign_master where link_hash_id='" + campaignId+"'");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                status = resultSet.getString("status");
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
        return status;
    }

    public int getId(String campaignId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        int id = 0;
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT id FROM campaign_master where link_hash_id='" + campaignId+"'");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                id = resultSet.getInt("id");
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
        return id;
    }

    public CampaignSlotDTO getOverallSlot(int campaignId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        CampaignSlotDTO campaignSlotDTO=new CampaignSlotDTO();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("select c.status, s.capacity,s.time_slot from campaign_master c\n" +
                    "left join slots s on c.id=s.campaign_master_id\n" +
                    " where campaign_master_id="+campaignId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                campaignSlotDTO.setStatus(resultSet.getString("status"));
                if(resultSet.getInt("capacity")>0){
                    campaignSlotDTO.setAvailable(true);
                }
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
        return campaignSlotDTO;
    }

    public List<String> getDates(int campaignId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        List<String> dates = new ArrayList<String>();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT distinct date FROM slots where campaign_master_id=" + campaignId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                dates.add(resultSet.getString("date"));
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
        return dates;

    }

    public List<SlotDetails> getTimeSlots(int campaignId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        List<SlotDetails> slots = new ArrayList<SlotDetails>();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT distinct time_slot,total_count FROM slots where campaign_master_id=" + campaignId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                String time = resultSet.getString("time_slot");
                int capacity = resultSet.getInt("total_count");
                SlotDetails slot = new SlotDetails();
                slot.setTime(time);
                slot.setCapacity(capacity);
                slots.add(slot);
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
        return slots;

    }

    public Boolean updateCampaign(CampaignDTO campaignDTO) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        Boolean isProcessed;
        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection
                    .prepareStatement("UPDATE campaign_master SET campaign_location=?,campaign_header_id=?,status=?,is_published=?, name=?, description =?, no_of_person =?, " +
                            "is_allow_on_full=?, confirm_sms = ?, confirm_email=?, notify_email=?,slot_full_sms=?,campaign_over_text=?,email_subject=? WHERE id =?");

            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getCampaignLocation());
            preparedStatement.setInt(parameterIndex++,
                    campaignDTO.getHeaderId());
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getStatus());
            preparedStatement.setInt(parameterIndex++,
                    campaignDTO.getIsPublished());
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
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getSlotFullText());
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getCampaignOverText());
            preparedStatement.setString(parameterIndex++,
                    campaignDTO.getEmailSubject());
            preparedStatement.setInt(parameterIndex++,
                    campaignDTO.getId());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                isProcessed = Boolean.TRUE;
                connection.commit();
            } else {
                isProcessed = Boolean.FALSE;
                connection.rollback();
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
        return isProcessed;
    }

    public void deleteDates(String date, int id) throws SQLException {
        Connection connection = new ConnectionHandler().getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        StringBuilder query = new StringBuilder("delete from slots where date = '" + date + "' and campaign_master_id=" + id);
        int i = statement.executeUpdate(query.toString());

        if (i > 0) {
            connection.commit();
        }

        statement.close();
        connection.close();
    }

    public void deleteSlots(String slot, int id) throws SQLException {
        Connection connection = new ConnectionHandler().getConnection();
        Statement statement = connection.createStatement();
        connection.setAutoCommit(false);
        StringBuilder query = new StringBuilder("delete from slots where time_slot = '" + slot + "' and campaign_master_id=" + id);
        int i = statement.executeUpdate(query.toString());

        if (i > 0) {
            connection.commit();
        } else {
            connection.rollback();
        }

        statement.close();
        connection.close();
    }
}
