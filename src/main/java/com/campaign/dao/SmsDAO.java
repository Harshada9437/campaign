package com.campaign.dao;

import com.campaign.dao.UtilClasses.ConnectionHandler;
import com.campaign.rest.request.campaign.SmsDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
