package com.campaign.dao;

import com.campaign.ConnectionPool;
import com.campaign.dao.UtilClasses.ConnectionHandler;
import com.campaign.dto.customer.CustomerDTO;
import com.campaign.dto.customer.RegisterDto;
import com.campaign.dto.customer.UpdateSlotDTO;
import com.campaign.dto.customer.VerifyOtpDTO;
import com.campaign.rest.response.campaign.CouponResponse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by System-3 on 11/30/2016.
 */
public class CustomerDAO {

    public Boolean updateSlot(UpdateSlotDTO updateSlotDTO) throws SQLException {
        boolean isCreated = false;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            int parameterIndex = 1;
            connection = new ConnectionPool().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("UPDATE customer_details SET date =? , time_slot =? WHERE id =?");

            preparedStatement.setString(parameterIndex++, updateSlotDTO.getDate());
            preparedStatement.setString(parameterIndex++, updateSlotDTO.getTime());
            preparedStatement.setInt(parameterIndex++, updateSlotDTO.getId());

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                connection.commit();
                updateCount(updateSlotDTO.getTime(),updateSlotDTO.getDate());
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
    public static Boolean getValidationForEmail(String email) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        Boolean isProcessed = Boolean.FALSE;
        try {
            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT email FROM customer_details where email = \"")
                    .append(email).append("\"");
            ResultSet resultSet = statement.executeQuery(query.toString());

            while (resultSet.next()) {

                isProcessed = true;

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isProcessed;
    }

    public static Boolean getValidationForPhoneNumber(String mobile) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        Boolean isProcessed = Boolean.FALSE;
        try {

            connection = new ConnectionPool().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT mobile FROM customer_details where mobile = \"")
                    .append(mobile).append("\"");
            ResultSet resultSet = statement.executeQuery(query.toString());

            while (resultSet.next()) {

                isProcessed = true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isProcessed;
    }


    public Integer insertUser(RegisterDto appUserDTO) throws Exception {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        StringBuilder query = new StringBuilder("INSERT INTO customer_details(name,date,time_slot,resource,no_of_person,remark,gender,dob,locality,email,mobile) values (?,?,?,?,?,?,?,?,?,?,?)");
        Integer id = 0;
        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement(query.toString());
            preparedStatement.setString(parameterIndex++,
                    appUserDTO.getFullName());
            preparedStatement.setString(parameterIndex++,
                    appUserDTO.getDate());
            preparedStatement.setString(parameterIndex++,
                    appUserDTO.getTimeSlot());
            preparedStatement.setString(parameterIndex++,
                    appUserDTO.getResource());
            preparedStatement.setInt(parameterIndex++,
                    appUserDTO.getNoOfPerson());
            preparedStatement.setString(parameterIndex++,
                    appUserDTO.getRemark());
            preparedStatement.setString(parameterIndex++,
                    appUserDTO.getGender());
            preparedStatement.setString(parameterIndex++,
                    appUserDTO.getDob());
            preparedStatement.setString(parameterIndex++,
                    appUserDTO.getLocality());
            preparedStatement.setString(parameterIndex++,
                    appUserDTO.getEmail());
            preparedStatement.setString(parameterIndex++,
                    appUserDTO.getMobile());

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
                    throw new SQLException(
                            "Creating customer failed, no ID obtained.");
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

    public CustomerDTO getCustomerById(int userId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        CustomerDTO customerDTO = new CustomerDTO();
        try {
            connection = new ConnectionHandler().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM customer_details where id = ")
                    .append(userId);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                customerDTO.setId(resultSet.getInt("id"));
                customerDTO.setDate(resultSet.getString("date"));
                customerDTO.setGender(resultSet.getString("gender"));
                customerDTO.setTimeSlot(resultSet.getString("time_slot"));
                customerDTO.setRemark(resultSet.getString("remark"));
                customerDTO.setFullName(resultSet.getString("name"));
                customerDTO.setEmail(resultSet.getString("email"));
                customerDTO.setMobile(resultSet.getString("mobile"));
                customerDTO.setDob(resultSet.getString("dob"));
                customerDTO.setResource(resultSet.getString("resource"));
                customerDTO.setLocality(resultSet.getString("locality"));
                customerDTO.setNoOfPerson(resultSet.getInt("no_of_person"));
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
        return customerDTO;
    }

    public List<CustomerDTO> getCustomerList( ) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
        try {
            connection = new ConnectionHandler().getConnection();
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder("SELECT * FROM customer_details");
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setId(resultSet.getInt("id"));
                customerDTO.setDate(resultSet.getString("date"));
                customerDTO.setGender(resultSet.getString("gender"));
                customerDTO.setTimeSlot(resultSet.getString("time_slot"));
                customerDTO.setRemark(resultSet.getString("remark"));
                customerDTO.setFullName(resultSet.getString("name"));
                customerDTO.setEmail(resultSet.getString("email"));
                customerDTO.setMobile(resultSet.getString("mobile"));
                customerDTO.setDob(resultSet.getString("dob"));
                customerDTO.setResource(resultSet.getString("resource"));
                customerDTO.setLocality(resultSet.getString("locality"));
                customerDTO.setNoOfPerson(resultSet.getInt("no_of_person"));
                customers.add(customerDTO);
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
        return customers;
    }


    public String verifyOtp(VerifyOtpDTO verifyOtpDTO) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String where = "", token = "";
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            if (!verifyOtpDTO.getOtp().equals("0")) {
                where = " and otp=" + verifyOtpDTO.getOtp();
            }

            StringBuilder query = new StringBuilder(
                    "SELECT isExpired,otp,token FROM user_otp where mobile =\"").append(verifyOtpDTO.getMobile()).append("\"").append(where);
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                if (resultSet.getString("isExpired").equals("NO")) {
                    token = resultSet.getString("token");
                    expireOtp(verifyOtpDTO.getOtp(), verifyOtpDTO.getMobile(), connection);
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
        return token;
    }

    public Boolean setOtp(String id, int otp) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        Boolean isCreate = Boolean.FALSE;

        try {
            int parameterIndex = 1;
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);


            statement = connection.prepareStatement("INSERT INTO user_otp(mobile,otp,isExpired,token) values(?,?,?,?)");

            statement.setString(parameterIndex++, id);
            statement.setInt(parameterIndex++, otp);
            statement.setString(parameterIndex++, "NO");
            String token = UUID.randomUUID().toString();
            statement.setString(parameterIndex++, token);


            int i = statement.executeUpdate();

            if (i > 0) {
                connection.commit();
                isCreate = Boolean.TRUE;


            } else {
                connection.rollback();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isCreate;
    }

    private Boolean expireOtp(String otp, String userId, Connection connection) throws SQLException {
        String where = "";
        Boolean isCreate = Boolean.FALSE;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE user_otp SET isExpired=\"" + "YES" + "\" WHERE otp='" + otp + "' and mobile=\""
                            + userId + "\"" + where);


            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                connection.commit();
                isCreate = Boolean.TRUE;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw sqlException;
        }
        return isCreate;
    }

    public static String getToken(String mobile) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String token="";
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT token FROM user_otp where mobile='" + mobile + "'" );
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
               token=resultSet.getString("token");
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
        return token;
    }

    public String getOtp(String mob) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        String token="";
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT otp FROM user_otp where mobile='" + mob + "' and isExpired='NO'" );
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                token=resultSet.getString("otp");
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
        return token;
    }

    public int getConut(String time, String date) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        int count=0;
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT count from coupon_availability where date='" + date + "' and time_slot='"+time+"'" );
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()){
                count=resultSet.getInt("count");
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
        return count;
    }

    public List<CouponResponse> getSlots(int id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        List<CouponResponse> responses = new ArrayList<CouponResponse>();
        try {
            connection = new ConnectionHandler().getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            StringBuilder query = new StringBuilder(
                    "SELECT capacity,date,time_slot FROM slots where campaign_id="+id );
            ResultSet resultSet = statement.executeQuery(query.toString());
            while (resultSet.next()) {
                CouponResponse couponCountDTO = new CouponResponse();
                couponCountDTO.setDate(resultSet.getString("date"));
                couponCountDTO.setTime(resultSet.getString("time_slot"));
                if(resultSet.getInt("capacity")>0){
                    couponCountDTO.setIsAvailable(1);
                }
                responses.add(couponCountDTO);
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
        return responses;
    }

    public void updateCount(String timeSlot, String date) throws SQLException{
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            int parameterIndex = 1;
            connection = new ConnectionPool().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection
                    .prepareStatement("UPDATE coupon_availability SET count =?  WHERE date =? and time_slot =?");

            preparedStatement.setInt(parameterIndex++, getConut(timeSlot,date)-1);
            preparedStatement.setString(parameterIndex++, date);
            preparedStatement.setString(parameterIndex++, timeSlot);

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
