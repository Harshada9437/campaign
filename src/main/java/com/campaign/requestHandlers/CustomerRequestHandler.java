package com.campaign.requestHandlers;

import com.campaign.bo.request.*;
import com.campaign.dao.CustomerDAO;
import com.campaign.dto.customer.CustomerDTO;
import com.campaign.dto.customer.RegisterDto;
import com.campaign.dto.customer.UpdateSlotDTO;
import com.campaign.dto.customer.VerifyOtpDTO;
import com.campaign.rest.response.campaign.CouponResponse;
import com.campaign.rest.response.customer.CustomerResponse;
import com.campaign.rest.response.campaign.DateResponse;
import com.campaign.rest.response.customer.GetCustomerResponse;
import com.campaign.util.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by System-3 on 11/30/2016.
 */
public class CustomerRequestHandler {

    public static String random(int size) {

        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            // Generate 20 integers 0..20
            for (int i = 0; i < size; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedToken.toString();
    }

    public Boolean verifyEmail(String email) throws SQLException {

        Boolean isProcessed;
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            if (!email.trim().equals("")) {
                isProcessed = customerDAO.getValidationForEmail(email);
            } else {
                return false;
            }
        } catch (SQLException sq) {
            isProcessed = false;
        }
        return isProcessed;
    }

    public Boolean verifyPhoneNumber(String mobile) throws SQLException {
        Boolean isProcessed;
        CustomerDAO customerDAO = new CustomerDAO();
        isProcessed = customerDAO.getValidationForPhoneNumber(mobile);
        return isProcessed;
    }

    public int register(RegisterBo registerBo) throws Exception {
        int userId = 0;
        CustomerDAO customerDAO = new CustomerDAO();

        userId = customerDAO.insertUser(buildUserDTOFromBO(registerBo));

        if (userId > 0 && !registerBo.getTimeSlot().equals("")) {
            customerDAO.updateCount(registerBo.getTimeSlot(), registerBo.getDate());
            //SendSms sendSms = new SendSms();
            //sendSms.newRegistration(registerBo.getFullName(),registerBo.getDate(),registerBo.getTimeSlot(),registerBo.getMobile(), registerBo.getNoOfPerson());
            //EmailService.newRegistration(registerBo.getEmail(), registerBo.getFullName(),registerBo.getDate(),registerBo.getTimeSlot(), registerBo.getNoOfPerson());
        }

        //CustomerResponse response = buildResponseFromDto(customerDAO.getCustomerById(userId), userId);

        return userId;
    }

    private GetCustomerResponse buildResponseFromDto(CustomerDTO customerDTO) throws SQLException {
        GetCustomerResponse customerResponse = new GetCustomerResponse();
        customerResponse.setId(customerDTO.getId());
        customerResponse.setFullName(customerDTO.getFullName());
        customerResponse.setEmail(customerDTO.getEmail());
        customerResponse.setMobile(customerDTO.getMobile());
        customerResponse.setDob(customerDTO.getDob());
        customerResponse.setGender(customerDTO.getGender());
        customerResponse.setLocality(customerDTO.getLocality());
        customerResponse.setNoOfPerson(customerDTO.getNoOfPerson());
        customerResponse.setResource(customerDTO.getResource());
        customerResponse.setDate(customerDTO.getDate());
        customerResponse.setTimeSlot(customerDTO.getTimeSlot());
        customerResponse.setRemark(customerDTO.getRemark());
        return customerResponse;
    }

    private RegisterDto buildUserDTOFromBO(RegisterBo userRequestBO) {
        RegisterDto appuserDTO = new RegisterDto();
        appuserDTO.setFullName(userRequestBO.getFullName());
        appuserDTO.setEmail(userRequestBO.getEmail());
        appuserDTO.setMobile(userRequestBO.getMobile());
        appuserDTO.setTimeSlot(userRequestBO.getTimeSlot());
        appuserDTO.setDate(userRequestBO.getDate());
        appuserDTO.setRemark(userRequestBO.getRemark());
        appuserDTO.setResource(userRequestBO.getResource());
        appuserDTO.setDob(userRequestBO.getDob());
        appuserDTO.setNoOfPerson(userRequestBO.getNoOfPerson());
        appuserDTO.setGender(userRequestBO.getGender());
        appuserDTO.setLocality(userRequestBO.getLocality());
        return appuserDTO;
    }

    public Boolean updateSlot(UpdateSlotRequestBO updateSlotRequestBO) throws SQLException {

        Boolean isProcessed;
        CustomerDAO customerDAO = new CustomerDAO();

        isProcessed = customerDAO.updateSlot(buildAppUserDTOFromBO(updateSlotRequestBO));
        CustomerDTO customerDTO = customerDAO.getCustomerById(updateSlotRequestBO.getId());
        if(isProcessed){
            //SendSms sendSms = new SendSms();
           // sendSms.newRegistration(customerDTO.getFullName(),customerDTO.getDate(),customerDTO.getTimeSlot(),customerDTO.getMobile(), customerDTO.getNoOfPerson());
            //EmailService.newRegistration(customerDTO.getEmail(), customerDTO.getFullName(),customerDTO.getDate(),customerDTO.getTimeSlot(), customerDTO.getNoOfPerson());

        }

        return isProcessed;
    }

    public UpdateSlotDTO buildAppUserDTOFromBO(UpdateSlotRequestBO updateSlotRequestBO) {
        UpdateSlotDTO updateSlotDTO = new UpdateSlotDTO();
        updateSlotDTO.setDate(updateSlotRequestBO.getDate());
        updateSlotDTO.setTime(updateSlotRequestBO.getTime());
        updateSlotDTO.setId(updateSlotRequestBO.getId());
        return updateSlotDTO;

    }

    public GetCustomerResponse getCustomerById(int id) throws SQLException {
        CustomerDAO appUserDAO = new CustomerDAO();
        GetCustomerResponse userResponse = buildResponseFromDto(appUserDAO
                .getCustomerById(id));
        return userResponse;
    }

    public List<CustomerResponse> getCustomers() throws SQLException {
        CustomerDAO appUserDAO = new CustomerDAO();
        List<CustomerResponse> userResponse = buildUsersResponseFromDTO(appUserDAO
                .getCustomerList());
        return userResponse;
    }

    private List<CustomerResponse> buildUsersResponseFromDTO(List<CustomerDTO> customerDTOs) {

        List<CustomerResponse> customers = new ArrayList<CustomerResponse>();
        for (CustomerDTO customerDTO : customerDTOs) {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setId(customerDTO.getId());
            customerResponse.setFullName(customerDTO.getFullName());
            customerResponse.setEmail(customerDTO.getEmail());
            customerResponse.setMobile(customerDTO.getMobile());
            customerResponse.setDob(customerDTO.getDob());
            customerResponse.setGender(customerDTO.getGender());
            customerResponse.setLocality(customerDTO.getLocality());
            customerResponse.setNoOfPerson(customerDTO.getNoOfPerson());
            customerResponse.setResource(customerDTO.getResource());
            customerResponse.setDate(customerDTO.getDate());
            customerResponse.setTimeSlot(customerDTO.getTimeSlot());
            customerResponse.setRemark(customerDTO.getRemark());
            customers.add(customerResponse);
        }
        return customers;
    }

    public String verifyOtp(VerifyOtpRequestBO verifyOtpRequestBO) throws SQLException {
        VerifyOtpDTO verifyOtpDTO = new VerifyOtpDTO();
        CustomerDAO customerDAO = new CustomerDAO();

        verifyOtpDTO.setOtp(verifyOtpRequestBO.getOtp());
        verifyOtpDTO.setMobile(verifyOtpRequestBO.getMobile());

        String token = customerDAO.verifyOtp(verifyOtpDTO);
        return token;
    }

    public Boolean generateOtp(String mob) throws SQLException {
        Boolean isProcessed;
        CustomerDAO customerDAO = new CustomerDAO();
        int otp = Integer.parseInt(random(6));
        String otp1 = customerDAO.getOtp(mob);
        if (otp1.equals("")) {
            isProcessed = customerDAO.setOtp(mob, otp);
           /* if (isProcessed) {
                SendSms sendSms = new SendSms();
                sendSms.NewUserSignup(mob, otp);
            }*/
        } else {
            isProcessed = Boolean.TRUE;
           /* SendSms sendSms = new SendSms();
            sendSms.NewUserSignup(mob, Integer.parseInt(otp1));*/
        }
        return isProcessed;
    }

    public List<DateResponse> getSlots(int id) throws SQLException {
        List<DateResponse> slots = new ArrayList<DateResponse>();
        CustomerDAO customerDAO = new CustomerDAO();
        DateResponse dateResponse = new DateResponse();
        List<CouponResponse> coupons = customerDAO.getSlots(id);
        List<String> dates = new ArrayList<String>();
        List<String> times = new ArrayList<String>();
        int i = 0;
        for (CouponResponse coupon : coupons) {
            if (coupon.getIsAvailable() == 1) {
                if (!dates.contains(coupon.getDate())) {
                    if (dateResponse.getDate() != null) {
                        if (!dateResponse.getDate().equals("")) {
                            dateResponse.setTime(times);
                            slots.add(dateResponse);
                        }
                    }
                    times = new ArrayList<String>();
                    dateResponse = new DateResponse();
                    dateResponse.setDate(DateUtil.format(DateUtil.getTimeStampFromString(coupon.getDate()), "dd-MMM-yyyy"));
                    times.add(coupon.getTime());
                    dates.add(coupon.getDate());
                } else {
                    times.add(coupon.getTime());
                }
            }else{
                slots = new ArrayList<DateResponse>();
            }
            i++;
            if (i == coupons.size()) {
                dateResponse.setTime(times);
                slots.add(dateResponse);
            }
        }
        return slots;
    }
}