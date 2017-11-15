package com.campaign.dao;

import com.campaign.dao.UtilClasses.ConnectionHandler;
import com.campaign.rest.request.campaign.SmsDetails;

import java.sql.*;

public class SmsDAO {

    public static SmsDetails getSmsSetting(int id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        SmsDetails smsDetails=new SmsDetails();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM sms_details where id=" + id );
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                smsDetails.setId(resultSet.getInt("id"));
                smsDetails.setShortCode(resultSet.getString("short_code"));
                smsDetails.setApikey(resultSet.getString("api_key"));
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
        return smsDetails;
    }

    public int createSmsSettings(SmsDetails smsDetails) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        StringBuilder query = new StringBuilder("INSERT INTO sms_details(short_code,api_key) values (?,?)");
        Integer id = 0;
        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement(query.toString());
            preparedStatement.setString(parameterIndex++, smsDetails.getShortCode());
            preparedStatement.setString(parameterIndex++, smsDetails.getApikey());

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
                    throw new SQLException("Creation failed, no ID obtained.");
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

    public void updateSms(SmsDetails smsDetails) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("UPDATE sms_details SET short_code =? , api_key =? WHERE id =?");

            preparedStatement.setString(parameterIndex++, smsDetails.getShortCode());
            preparedStatement.setString(parameterIndex++, smsDetails.getApikey());
            preparedStatement.setInt(parameterIndex++, smsDetails.getId());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                connection.commit();
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
    }
}
