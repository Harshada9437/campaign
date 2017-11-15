package com.campaign.dao;

import com.campaign.dao.UtilClasses.ConnectionHandler;
import com.campaign.rest.request.campaign.SmsDetails;
import com.campaign.rest.request.campaign.SmtpDetails;

import java.sql.*;

public class SmtpDAO {
    public static SmtpDetails getSmtpSetting(int id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        SmtpDetails smtpDetails=new SmtpDetails();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM email_smtp_main where id=" + id );
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                smtpDetails.setId(resultSet.getInt("id"));
                smtpDetails.setHost(resultSet.getString("smtp_host"));
                smtpDetails.setEmail(resultSet.getString("smtp_email_address"));
                smtpDetails.setPassword(resultSet.getString("smtp_password"));
                smtpDetails.setPort(resultSet.getInt("smtp_port"));
                smtpDetails.setFrom(resultSet.getString("smtp_from_name"));
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
        return smtpDetails;
    }

    public void updateSmtp(SmtpDetails smtpDetails) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("UPDATE email_smtp_main SET smtp_host=?,smtp_email_address=?,smtp_from_name=?,smtp_password=?,smtp_port=? WHERE id =?");

            preparedStatement.setString(parameterIndex++, smtpDetails.getHost());
            preparedStatement.setString(parameterIndex++, smtpDetails.getEmail());
            preparedStatement.setString(parameterIndex++, smtpDetails.getFrom());
            preparedStatement.setString(parameterIndex++, smtpDetails.getPassword());
            preparedStatement.setInt(parameterIndex++, smtpDetails.getPort());
            preparedStatement.setInt(parameterIndex++, smtpDetails.getId());

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

    public int createSmtpSettings(SmtpDetails smtpDetails) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        StringBuilder query = new StringBuilder("INSERT INTO email_smtp_main(smtp_host,smtp_from_name,smtp_port,smtp_password,smtp_email_address) values (?,?,?,?,?)");
        Integer id = 0;
        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement(query.toString());
            preparedStatement.setString(parameterIndex++, smtpDetails.getHost());
            preparedStatement.setString(parameterIndex++, smtpDetails.getFrom());
            preparedStatement.setInt(parameterIndex++, smtpDetails.getPort());
            preparedStatement.setString(parameterIndex++, smtpDetails.getPassword());
            preparedStatement.setString(parameterIndex++, smtpDetails.getEmail());

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
}
