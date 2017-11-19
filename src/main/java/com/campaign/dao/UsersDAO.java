package com.campaign.dao;

import com.campaign.dao.UtilClasses.ConnectionHandler;

import java.sql.*;

public class UsersDAO {
    public Boolean getValidUserBySessionIdPasswordUsername(String userName, String password, String sessionId)
            throws SQLException, ClassNotFoundException {
        Boolean isVerify = Boolean.FALSE;
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbeqdb", "root", "n0zo3Tel");
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM user_details where user_name =\"" + userName + "\" and password=\"" + password + "\" and session_id LIKE \"%|" +
                            sessionId + "|%\"");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                isVerify = Boolean.TRUE;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isVerify;
    }

}
