package com.campaign.dao;

import com.campaign.dao.UtilClasses.ConnectionHandler;
import com.campaign.rest.request.campaign.SmtpDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
