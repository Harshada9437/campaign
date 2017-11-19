package com.campaign.requestHandlers;

import com.campaign.Exception.CampaignNotFoundException;
import com.campaign.Exception.CustomerNotFoundException;
import com.campaign.bo.request.*;
import com.campaign.dao.CampaignDAO;
import com.campaign.dao.CustomerDAO;
import com.campaign.dao.HeaderDAO;
import com.campaign.dao.SmsDAO;
import com.campaign.dto.campaign.CampaignDTO;
import com.campaign.dto.customer.CustomerDTO;
import com.campaign.dto.customer.RegisterDto;
import com.campaign.dto.customer.UpdateSlotDTO;
import com.campaign.dto.customer.VerifyOtpDTO;
import com.campaign.rest.request.campaign.HeaderDetails;
import com.campaign.rest.request.campaign.SmsDetails;
import com.campaign.rest.response.campaign.CouponResponse;
import com.campaign.rest.response.campaign.Slots;
import com.campaign.rest.response.customer.CustomerResponse;
import com.campaign.rest.response.campaign.DateResponse;
import com.campaign.rest.response.customer.GetCustomerResponse;
import com.campaign.util.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by System-3 on 11/30/2016.
 */
public class CustomerRequestHandler {

    public Boolean verifyEmail(String email, int id) throws SQLException {

        Boolean isProcessed;
        CustomerDAO customerDAO = new CustomerDAO();
        try {
            if (!email.trim().equals("")) {
                isProcessed = customerDAO.getValidationForEmail(email, id);
            } else {
                return false;
            }
        } catch (SQLException sq) {
            isProcessed = false;
        }
        return isProcessed;
    }

    public Boolean verifyPhoneNumber(String mobile, int id) throws SQLException {
        Boolean isProcessed;
        CustomerDAO customerDAO = new CustomerDAO();
        isProcessed = customerDAO.getValidationForPhoneNumber(mobile, id);
        return isProcessed;
    }

    public Boolean register(RegisterBo registerBo) throws CustomerNotFoundException, SQLException, CampaignNotFoundException {
        Boolean isProcessed;
        CustomerDAO customerDAO = new CustomerDAO();
        CampaignDAO campaignDAO = new CampaignDAO();

        CampaignDTO campaignDTO = campaignDAO.getCampaignInfo(registerBo.getCampaignId());
        if (campaignDTO.getIsPublished() == 1) {
            CustomerDTO customerDTO = customerDAO.getCustomerById(registerBo.getCustomerId());
            isProcessed = customerDAO.confirmRegistration(registerBo.getCustomerId());
            if (isProcessed) {
                customerDAO.updateCount(customerDTO.getNoOfPerson(), customerDTO.getTimeSlot(), customerDTO.getDate(), campaignDTO.getId());
                sendConfirmation(customerDTO, campaignDTO);
            }
        } else {
            isProcessed = Boolean.TRUE;
            SendSms sendSms = new SendSms();
            sendSms.newRegistration(registerBo.getFullName(), registerBo.getDate(), registerBo.getTimeSlot(), registerBo.getMobile(), registerBo.getNoOfPerson(), campaignDTO.getSms(), campaignDTO.getConfirmSms(), "(Unpublished Campaign) ");
            EmailService.newRegistration(campaignDTO.getEmailSubject(), campaignDTO.getConfirmEmail(), registerBo.getEmail(), registerBo.getFullName(), registerBo.getDate(), registerBo.getTimeSlot(), registerBo.getNoOfPerson(), campaignDTO.getSmtp(), "(Unpublished Campaign) ");
        }

        return isProcessed;
    }

    public void sendConfirmation(CustomerDTO customerDTO, CampaignDTO campaignDTO) {
        SendSms sendSms = new SendSms();
        sendSms.newRegistration(customerDTO.getFullName(), customerDTO.getDate(), customerDTO.getTimeSlot(), customerDTO.getMobile(), customerDTO.getNoOfPerson(), campaignDTO.getSms(), campaignDTO.getConfirmSms(), "");
        EmailService.newRegistration(campaignDTO.getEmailSubject(), campaignDTO.getConfirmEmail(), customerDTO.getEmail(), customerDTO.getFullName(), customerDTO.getDate(), customerDTO.getTimeSlot(), customerDTO.getNoOfPerson(), campaignDTO.getSmtp(), "");
    }

    public GetCustomerResponse buildResponseFromDto(CustomerDTO customerDTO) throws SQLException {
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

    private RegisterDto buildUserDTOFromBO(OtpRequestBO userRequestBO, int id) {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setFullName(userRequestBO.getFullName());
        registerDto.setEmail(userRequestBO.getEmail());
        registerDto.setMobile(userRequestBO.getMobile());
        registerDto.setTimeSlot(userRequestBO.getTimeSlot());
        registerDto.setDate(userRequestBO.getDate());
        registerDto.setRemark(userRequestBO.getRemark());
        registerDto.setResource(userRequestBO.getResource());
        registerDto.setDob(userRequestBO.getDob());
        registerDto.setNoOfPerson(userRequestBO.getNoOfPerson());
        registerDto.setGender(userRequestBO.getGender());
        registerDto.setLocality(userRequestBO.getLocality());
        registerDto.setCampaignId(id);
        return registerDto;
    }

    public Boolean updateSlot(UpdateSlotRequestBO updateSlotRequestBO) throws SQLException, CustomerNotFoundException, CampaignNotFoundException {

        Boolean isProcessed;
        CustomerDAO customerDAO = new CustomerDAO();
        CampaignDAO campaignDAO = new CampaignDAO();

        CampaignDTO campaignDTO = campaignDAO.getCampaignInfo(updateSlotRequestBO.getCampaignId());
        CustomerDTO customerDTO = customerDAO.getCustomerById(updateSlotRequestBO.getId());
        isProcessed = customerDAO.updateSlot(buildAppUserDTOFromBO(updateSlotRequestBO), campaignDTO.getId(), customerDTO.getNoOfPerson());

        if (isProcessed) {
            sendConfirmation(customerDTO, campaignDTO);
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

    public GetCustomerResponse getCustomerById(int id) throws SQLException, CustomerNotFoundException {
        CustomerDAO appUserDAO = new CustomerDAO();
        GetCustomerResponse userResponse = buildResponseFromDto(appUserDAO
                .getCustomerById(id));
        return userResponse;
    }

    public List<CustomerResponse> getCustomers(int campaignId) throws SQLException {
        CustomerDAO appUserDAO = new CustomerDAO();
        List<CustomerResponse> userResponse = buildUsersResponseFromDTO(appUserDAO
                .getCustomerList(campaignId));
        return userResponse;
    }

    private List<CustomerResponse> buildUsersResponseFromDTO(List<CustomerDTO> customerDTOs) {

        List<CustomerResponse> customers = new ArrayList<CustomerResponse>();
        for (CustomerDTO customerDTO : customerDTOs) {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setCampaignId(customerDTO.getCampaignId());
            customerResponse.setCampaignName(customerDTO.getCampaignName());
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
            customerResponse.setIsConfirmed(customerDTO.getIsConfirmed());
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

    public int generateOtp(OtpRequestBO otpRequestBO, CampaignDTO campaignDTO) throws SQLException {
        CustomerDAO customerDAO = new CustomerDAO();
        SmsDetails smsDetails = SmsDAO.getSmsSetting(campaignDTO.getSms().getId());
        int id = customerDAO.getCustomerId(otpRequestBO.getMobile(), campaignDTO.getId());
        String otp1 = customerDAO.getOtp(otpRequestBO.getMobile());
        if (id == 0) {
            otp1 = verifyOtp(otp1, otpRequestBO.getMobile());
            if (campaignDTO.getIsPublished() == 1) {
                id = customerDAO.insertUser(buildUserDTOFromBO(otpRequestBO,campaignDTO.getId()));
            }
            SendSms sendSms = new SendSms();
            sendSms.NewUserSignup(otpRequestBO.getMobile(), Integer.parseInt(otp1), smsDetails);

        } else {
            otp1 = verifyOtp(otp1, otpRequestBO.getMobile());
            SendSms sendSms = new SendSms();
            sendSms.NewUserSignup(otpRequestBO.getMobile(), Integer.parseInt(otp1), smsDetails);
        }
        return id;
    }

    private String verifyOtp(String otp1, String mobileNumber) throws SQLException {

        if (otp1 != null && otp1.equals("")) {
            CustomerDAO customerDAO = new CustomerDAO();
            Random rnd = new Random();
            int otp = 100000 + rnd.nextInt(900000);
            customerDAO.setOtp(mobileNumber, otp);
            otp1 = otp + "";
        }
        return otp1;
    }

    public List<DateResponse> getSlots(String hashId) throws SQLException, CampaignNotFoundException, ParseException {
        List<DateResponse> slots = new ArrayList<DateResponse>();
        CustomerDAO customerDAO = new CustomerDAO();
        CampaignDAO campaignDAO = new CampaignDAO();
        DateResponse dateResponse = new DateResponse();
        List<CouponResponse> coupons = new ArrayList<CouponResponse>();
        int id = campaignDAO.getId(hashId);
        if (campaignDAO.getStatus(hashId).equals("A")) {
            Timestamp today = new Timestamp(new Date().getTime());
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);
            String date = DateUtil.getDateStringFromTimeStamp(today);
            coupons = customerDAO.getSlots(id, date);
        }
        List<String> dates = new ArrayList<String>();
        List<Slots> times = new ArrayList<Slots>();
        int i = 0;

        if (coupons.size() != 0) {
            for (CouponResponse coupon : coupons) {
                if (!dates.contains(coupon.getDate())) {
                    if (dateResponse.getDate() != null) {
                        if (!dateResponse.getDate().equals("")) {
                            dateResponse.setTime(times);
                            slots.add(dateResponse);
                        }
                    }
                    times = new ArrayList<Slots>();
                    dateResponse = new DateResponse();
                    dateResponse.setDate(DateUtil.format(DateUtil.getTimeStampFromString(coupon.getDate()), "dd-MMM-yyyy"));
                    times = addSlots(coupon, times);
                    dates.add(coupon.getDate());
                } else {
                    times = addSlots(coupon, times);
                }
                i++;
            }
            if (i == coupons.size()) {
                dateResponse.setTime(times);
                slots.add(dateResponse);
            }
        } else {
            slots = new ArrayList<DateResponse>();
        }
        return slots;
    }

    private List<Slots> addSlots(CouponResponse coupon, List<Slots> times) {
        Slots slots1 = new Slots();
        slots1.setTime(coupon.getTime());
        slots1.setCapacity(coupon.getCapacity());
        times.add(slots1);
        return times;
    }
}