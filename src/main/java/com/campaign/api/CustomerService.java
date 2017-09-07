package com.campaign.api;

import com.campaign.bo.request.OtpRequestBO;
import com.campaign.bo.request.RegisterBo;
import com.campaign.bo.request.UpdateSlotRequestBO;
import com.campaign.bo.request.VerifyOtpRequestBO;
import com.campaign.dao.CustomerDAO;
import com.campaign.requestHandlers.CustomerRequestHandler;
import com.campaign.rest.request.VerifyOtpRequest;
import com.campaign.rest.request.customer.*;
import com.campaign.rest.response.customer.CustomerListResponse;
import com.campaign.rest.response.customer.GetCustomerResponse;
import com.campaign.rest.response.util.MessageResponse;
import com.campaign.rest.response.util.ResponseGenerator;

import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by System-3 on 11/30/2016.
 */
@Path("/customer")
public class CustomerService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response createAppUser(RegisterRequest registerRequest) throws Exception {
        RegisterBo registerBO = new RegisterBo();

        registerBO.setFullName(registerRequest.getFullName());
        registerBO.setEmail(registerRequest.getEmail());
        registerBO.setMobile(registerRequest.getMobile());
        registerBO.setLocality(registerRequest.getLocality());
        registerBO.setDate(registerRequest.getDate());
        registerBO.setDob(registerRequest.getDob());
        registerBO.setGender(registerRequest.getGender());
        registerBO.setRemark(registerRequest.getRemark());
        registerBO.setResource(registerRequest.getResource());
        registerBO.setNoOfPerson(registerRequest.getNoOfPerson());
        registerBO.setTimeSlot(registerRequest.getTimeSlot());
        registerBO.setToken(registerRequest.getToken());

        MessageResponse appcreateUserResponse = new MessageResponse();
        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        int id=0;
        try {
            if (!customerRequestHandler.verifyEmail(registerRequest.getEmail())) {
                if (!customerRequestHandler.verifyPhoneNumber(registerRequest.getMobile())) {
                    String token = CustomerDAO.getToken(registerBO.getMobile());
                    if (token.equals(registerBO.getToken())) {
                        CustomerDAO customerDAO = new CustomerDAO();
                        if(customerDAO.getConut(registerBO.getTimeSlot(),registerBO.getDate())>0) {
                            id = customerRequestHandler.register(registerBO);
                            return ResponseGenerator.generateSuccessResponse(appcreateUserResponse, String.valueOf(id));
                        }else{
                            registerBO.setDate("");
                            registerBO.setTimeSlot("");
                            id = customerRequestHandler.register(registerBO);
                            return ResponseGenerator.generateFailureResponse(appcreateUserResponse,"SLOTISFULL-"+id);
                        }
                    } else {
                        return ResponseGenerator.generateFailureResponse(appcreateUserResponse, "Invalid customer registration failed.");
                    }
                } else {
                    return ResponseGenerator.generateFailureResponse(appcreateUserResponse, "Mobile number already exists");
                }
            } else {
                return ResponseGenerator.generateFailureResponse(appcreateUserResponse, "Email already exists");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return ResponseGenerator.generateFailureResponse(appcreateUserResponse, "New customer registration failed");
        }catch (MessagingException e){
            return ResponseGenerator.generateSuccessResponse(appcreateUserResponse, String.valueOf(id));
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UpdateSlotRequest slotRequest) throws SQLException {

        UpdateSlotRequestBO slotRequestBO = new UpdateSlotRequestBO();

        slotRequestBO.setDate(slotRequest.getDate());
        slotRequestBO.setTime(slotRequest.getTime());
        slotRequestBO.setId(slotRequest.getId());

        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        MessageResponse updateResponse = new MessageResponse();
        if (customerRequestHandler.updateSlot(slotRequestBO)) {
            return ResponseGenerator.generateSuccessResponse(updateResponse, "Slot updated successfully");
        } else {
            return ResponseGenerator.generateFailureResponse(updateResponse, "Unable to update the slot");
        }
    }

    @GET
    @Path("/customerInfo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppUserById(@PathParam("id") int id, @HeaderParam("Auth") String auth) throws Exception {

            CustomerRequestHandler appUserRequestHandler = new CustomerRequestHandler();
            MessageResponse messageResponse = new MessageResponse();
            try {
                GetCustomerResponse appuserResponse = appUserRequestHandler.getCustomerById(id);
                return ResponseGenerator.generateSuccessResponse(appuserResponse, "SUCCESS");
            } catch (SQLException e) {
                e.printStackTrace();
                return ResponseGenerator.generateFailureResponse(messageResponse, "Invalid User");
            }
    }

    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerList() throws Exception {
        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        CustomerListResponse customerListResponse = new CustomerListResponse();
        MessageResponse messageResponse = new MessageResponse();
        try {
            customerListResponse.setCustomers(customerRequestHandler.getCustomers());
            return ResponseGenerator.generateSuccessResponse(customerListResponse, "List of registered customers.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve. ");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/verifyOtp")
    public Response verifyOtp(VerifyOtpRequest verifyOtpRequest) {

        VerifyOtpRequestBO verifyOtpRequestBO = new VerifyOtpRequestBO();
        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        MessageResponse messageResponse = new MessageResponse();

        verifyOtpRequestBO.setMobile(verifyOtpRequest.getMobile());
        verifyOtpRequestBO.setOtp(verifyOtpRequest.getOtp());

        try {
            String token = customerRequestHandler.verifyOtp(verifyOtpRequestBO);
            if (token.equals("")) {
                return ResponseGenerator.generateFailureResponse(messageResponse, "Invalid otp.");
            } else {
                return ResponseGenerator.generateSuccessResponse(messageResponse, token);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Verification failed.");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOtp")
    public Response gteOtp(OtpRquest otpRquest) {

        OtpRequestBO otpRequestBO = new OtpRequestBO();
        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        MessageResponse messageResponse = new MessageResponse();

        try {
            otpRequestBO.setMobileNo(otpRquest.getMobileNo());
            otpRequestBO.setEmail(otpRquest.getEmail());

            if (!customerRequestHandler.verifyEmail(otpRequestBO.getEmail())) {
                if (!customerRequestHandler.verifyPhoneNumber(otpRequestBO.getMobileNo())) {
                customerRequestHandler.generateOtp(otpRequestBO.getMobileNo());
                return ResponseGenerator.generateSuccessResponse(messageResponse, "OTP sent to your registered mobile number.");

            } else {
                return ResponseGenerator.generateFailureResponse(messageResponse, "Mobile number already exists.");
            }}else {
                return ResponseGenerator.generateFailureResponse(messageResponse, "Email address already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Invalid mobile number.");

        }
    }
}












