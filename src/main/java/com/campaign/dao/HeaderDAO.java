package com.campaign.dao;

import com.campaign.dao.UtilClasses.ConnectionHandler;
import com.campaign.dto.campaign.HeaderDTO;
import com.campaign.rest.request.campaign.HeaderDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeaderDAO {
    public static HeaderDetails getHeaderSetting(int id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        HeaderDetails headerDetails = new HeaderDetails();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM campaign_header where id=" + id);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                headerDetails.setId(resultSet.getInt("id"));
                headerDetails.setName(resultSet.getString("name"));
                headerDetails.setInfo(resultSet.getString("info_desc"));
                headerDetails.setLogo(resultSet.getString("logo"));
                headerDetails.setIcon(resultSet.getString("icon"));
                headerDetails.setBackground(resultSet.getString("background"));
                headerDetails.setForecolor(resultSet.getString("forecolor"));
                headerDetails.setBorder(resultSet.getString("border"));
                headerDetails.setDomain(resultSet.getString("domain"));
                headerDetails.setSmsId(resultSet.getInt("sms_acc_id"));
                headerDetails.setSmtpId(resultSet.getInt("email_smtp_id"));
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
        return headerDetails;
    }

    public List<HeaderDetails> getHeaders() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        List<HeaderDetails> headers = new ArrayList<HeaderDetails>();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM campaign_header");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                HeaderDetails headerDetails = new HeaderDetails();
                headerDetails.setId(resultSet.getInt("id"));
                headerDetails.setName(resultSet.getString("name"));
                headerDetails.setInfo(resultSet.getString("info_desc"));
                headerDetails.setLogo(resultSet.getString("logo"));
                headerDetails.setIcon(resultSet.getString("icon"));
                headerDetails.setBackground(resultSet.getString("background"));
                headerDetails.setForecolor(resultSet.getString("forecolor"));
                headerDetails.setBorder(resultSet.getString("border"));
                headerDetails.setDomain(resultSet.getString("domain"));
                headerDetails.setSmsId(resultSet.getInt("sms_acc_id"));
                headerDetails.setSmtpId(resultSet.getInt("email_smtp_id"));
                headers.add(headerDetails);
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
        return headers;
    }

    public int createHeader(HeaderDTO headerDTO) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        StringBuilder query = new StringBuilder("INSERT INTO campaign_header(name,info_desc,sms_acc_id,email_smtp_id,logo,icon,background,border,forecolor,domain) values (?,?,?,?,?,?,?,?,?,?)");
        int id = 0;
        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement(query.toString());
            preparedStatement.setString(parameterIndex++, headerDTO.getName());
            preparedStatement.setString(parameterIndex++, headerDTO.getInfo());
            preparedStatement.setInt(parameterIndex++, headerDTO.getSmsAccId());
            preparedStatement.setInt(parameterIndex++, headerDTO.getEmailAccId());
            preparedStatement.setString(parameterIndex++, headerDTO.getLogo());
            preparedStatement.setString(parameterIndex++, headerDTO.getIcon());
            preparedStatement.setString(parameterIndex++, headerDTO.getBackground());
            preparedStatement.setString(parameterIndex++, headerDTO.getBorder());
            preparedStatement.setString(parameterIndex++, headerDTO.getForecolor());
            preparedStatement.setString(parameterIndex++, headerDTO.getDomain());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }
            try {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                    connection.commit();
                } else {
                    throw new SQLException("Creating smtp failed, no ID obtained.");
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

    public Boolean updateHeader(HeaderDTO headerDTO) throws SQLException {
        boolean isCreated = false;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("UPDATE campaign_header SET name =? ,info_desc =? ,background =? ,border =? ,domain =? ,icon =? ,logo =? , forecolor =? WHERE id =?");

            preparedStatement.setString(parameterIndex++, headerDTO.getName());
            preparedStatement.setString(parameterIndex++, headerDTO.getInfo());
            preparedStatement.setString(parameterIndex++, headerDTO.getBackground());
            preparedStatement.setString(parameterIndex++, headerDTO.getBorder());
            preparedStatement.setString(parameterIndex++, headerDTO.getDomain());
            preparedStatement.setString(parameterIndex++, headerDTO.getIcon());
            preparedStatement.setString(parameterIndex++, headerDTO.getLogo());
            preparedStatement.setString(parameterIndex++, headerDTO.getForecolor());
            preparedStatement.setInt(parameterIndex++, headerDTO.getId());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                connection.commit();
                isCreated = Boolean.TRUE;
            } else {
                connection.rollback();
            }
        } catch (SQLException sqlException) {
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
        return isCreated;
    }

    public HeaderDetails getHeaderInfo(int id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        HeaderDetails headerDetails = new HeaderDetails();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM campaign_header where id="+id);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                headerDetails.setId(resultSet.getInt("id"));
                headerDetails.setName(resultSet.getString("name"));
                headerDetails.setInfo(resultSet.getString("info_desc"));
                headerDetails.setLogo(resultSet.getString("logo"));
                headerDetails.setIcon(resultSet.getString("icon"));
                headerDetails.setBackground(resultSet.getString("background"));
                headerDetails.setForecolor(resultSet.getString("forecolor"));
                headerDetails.setBorder(resultSet.getString("border"));
                headerDetails.setDomain(resultSet.getString("domain"));
                headerDetails.setSmsId(resultSet.getInt("sms_acc_id"));
                headerDetails.setSmtpId(resultSet.getInt("email_smtp_id"));
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
        return headerDetails;
    }
}
