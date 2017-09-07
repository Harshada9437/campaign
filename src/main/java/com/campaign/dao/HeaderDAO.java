package com.campaign.dao;

import com.campaign.dao.UtilClasses.ConnectionHandler;
import com.campaign.rest.request.campaign.HeaderDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
