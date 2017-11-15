package com.campaign.api;

import com.campaign.Exception.CustomerNotFoundException;
import com.campaign.bo.request.OtpRequestBO;
import com.campaign.bo.request.RegisterBo;
import com.campaign.bo.request.UpdateSlotRequestBO;
import com.campaign.bo.request.VerifyOtpRequestBO;
import com.campaign.dao.CampaignDAO;
import com.campaign.dao.CustomerDAO;
import com.campaign.dto.campaign.CampaignDTO;
import com.campaign.dto.campaign.CampaignSlotDTO;
import com.campaign.dto.customer.CustomerDTO;
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
@Path("/campaignCustomer")
public class CustomerService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response createAppUser(RegisterRequest registerRequest) throws Exception {
        RegisterBo registerBO = new RegisterBo();

        registerBO.setToken(registerRequest.getToken());
        registerBO.setFullName(registerRequest.getFullName());
        registerBO.setCampaignId(registerRequest.getCampaignId());
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
        registerBO.setCustomerId(registerRequest.getCustomerId());

        MessageResponse messageResponse = new MessageResponse();
        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            CampaignDAO campaignDAO = new CampaignDAO();
            CampaignSlotDTO campaignSlotDTO= campaignDAO.getOverallSlot(registerBO.getCampaignId());
            int currentCapacity = customerDAO.getConut(registerBO.getTimeSlot(), registerBO.getDate(), registerBO.getCampaignId());
           String token = CustomerDAO.getToken(registerBO.getMobile());
            if (campaignSlotDTO.getStatus().equals("A")) {
           if (token.equals(registerBO.getToken())) {
            if (campaignSlotDTO.isAvailable()) {
                if (currentCapacity > 0) {
                    if (registerBO.getNoOfPerson() <= currentCapacity) {
                        customerRequestHandler.register(registerBO);
                        return ResponseGenerator.generateSuccessResponse(messageResponse, "Your registration has been confirmed.");
                    } else {
                        if(currentCapacity==1){
                        return ResponseGenerator.generateFailureResponse(messageResponse, "Only 1 person capacity left for this time slot");
                    }else{
                            return ResponseGenerator.generateFailureResponse(messageResponse, "Only "+currentCapacity+" persons capacity left for this time slot");
                        }
                    }
                    } else {
                        return ResponseGenerator.generateFailureResponse(messageResponse, "SLOTISFULL-" + registerBO.getCustomerId());
                    }
                } else {
                    return ResponseGenerator.generateFailureResponse(messageResponse, "WaitListed");
                }
            } else {
                return ResponseGenerator.generateFailureResponse(messageResponse, "Invalid customer.");
            }} else {
                return ResponseGenerator.generateFailureResponse(messageResponse, "InactiveCampaign");
            }

            } catch(SQLException sqlException){
                sqlException.printStackTrace();
                return ResponseGenerator.generateFailureResponse(messageResponse, "New customer registration failed");
            } catch(MessagingException e){
                return ResponseGenerator.generateSuccessResponse(messageResponse, "Email sending failed.");
            }
        }

        @POST
        @Path("/update")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response updateUser (UpdateSlotRequest slotRequest) throws SQLException {

            UpdateSlotRequestBO slotRequestBO = new UpdateSlotRequestBO();

            slotRequestBO.setDate(slotRequest.getDate());
            slotRequestBO.setTime(slotRequest.getTime());
            slotRequestBO.setId(slotRequest.getId());
            slotRequestBO.setCampaignId(slotRequest.getCampaignId());

        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        MessageResponse updateResponse = new MessageResponse();
        CustomerDAO customerDAO = new CustomerDAO();
        CampaignDAO campaignDAO = new CampaignDAO();
        try {

            CampaignSlotDTO campaignSlotDTO = campaignDAO.getOverallSlot(slotRequestBO.getCampaignId());
            if(campaignSlotDTO.getStatus().equals("A")) {
                if (campaignSlotDTO.isAvailable()) {
                    if (customerDAO.getConut(slotRequestBO.getTime(), slotRequestBO.getDate(), slotRequestBO.getCampaignId()) > 0) {

                        customerRequestHandler.updateSlot(slotRequestBO);
                        return ResponseGenerator.generateSuccessResponse(updateResponse, "Slot updated successfully");
                    } else {
                        return ResponseGenerator.generateFailureResponse(updateResponse, "This slot is full try for another one.");
                    }
                } else {
                    return ResponseGenerator.generateFailureResponse(updateResponse, "WaitListed");
                }
            }else{
                return ResponseGenerator.generateFailureResponse(updateResponse, "This campaign has been closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(updateResponse, "failed to update the slot");
        }
    }

    @GET
    @Path("/customerInfo/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppUserById ( @PathParam("id") int id, @HeaderParam("Auth") String auth) throws Exception {

        CustomerRequestHandler appUserRequestHandler = new CustomerRequestHandler();
        MessageResponse messageResponse = new MessageResponse();
        try {
            GetCustomerResponse appuserResponse = appUserRequestHandler.getCustomerById(id);
            return ResponseGenerator.generateSuccessResponse(appuserResponse, "SUCCESS");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve customer details.");
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Invalid customer id.");
        }
    }

    @GET
    @Path("/list/{campaign_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerList ( @PathParam("campaign_id") int campaignId) throws Exception {
        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        CustomerListResponse customerListResponse = new CustomerListResponse();
        MessageResponse messageResponse = new MessageResponse();
        try {
            customerListResponse.setCustomers(customerRequestHandler.getCustomers(campaignId));
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
    public Response verifyOtp (VerifyOtpRequest verifyOtpRequest){

        VerifyOtpRequestBO verifyOtpRequestBO = new VerifyOtpRequestBO();
        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        MessageResponse messageResponse = new MessageResponse();

        verifyOtpRequestBO.setMobile(verifyOtpRequest.getMobile());
        verifyOtpRequestBO.setOtp(verifyOtpRequest.getOtp());

        try {
            CampaignDAO campaignDAO = new CampaignDAO();
            if (campaignDAO.getStatus(verifyOtpRequest.getCampaignId()).equals("A")) {
                String token = customerRequestHandler.verifyOtp(verifyOtpRequestBO);
                if (token.equals("")) {
                    return ResponseGenerator.generateFailureResponse(messageResponse, "Invalid otp.");
                } else {
                    return ResponseGenerator.generateSuccessResponse(messageResponse, token);
                }
            } else {
                return ResponseGenerator.generateFailureResponse(messageResponse, "InactiveCampaign");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Verification failed.");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getOtp")
    public synchronized Response gteOtp (OtpRequest registerRequest){

        OtpRequestBO registerBO = new OtpRequestBO();
        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        MessageResponse messageResponse = new MessageResponse();

        try {
            registerBO.setFullName(registerRequest.getFullName());
            registerBO.setCampaignId(registerRequest.getCampaignId());
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

            CampaignDAO campaignDAO = new CampaignDAO();
            CustomerDAO customerDAO = new CustomerDAO();
            CampaignDTO campaignDTO = campaignDAO.getCampaign(registerBO.getCampaignId());
            if (campaignDAO.getStatus(registerBO.getCampaignId()).equals("A")) {
                if (!customerRequestHandler.verifyEmail(registerBO.getEmail(), registerBO.getCampaignId())) {
                    if (!customerRequestHandler.verifyPhoneNumber(registerBO.getMobile(), registerBO.getCampaignId())) {
                        int currentCapacity = customerDAO.getConut(registerBO.getTimeSlot(), registerBO.getDate(), registerBO.getCampaignId());
                        if (registerBO.getNoOfPerson() <= currentCapacity) {
                            int id = customerRequestHandler.generateOtp(registerBO, campaignDTO);
                            return ResponseGenerator.generateSuccessResponse(messageResponse, String.valueOf(id));
                        } else {
                            if(currentCapacity==0 && campaignDTO.getIsAllowOnFull()==1){
                                int id = customerRequestHandler.generateOtp(registerBO, campaignDTO);
                                return ResponseGenerator.generateSuccessResponse(messageResponse, String.valueOf(id));
                            }
                            if(currentCapacity==1){
                                return ResponseGenerator.generateFailureResponse(messageResponse, "Only 1 person capacity left for this time slot");
                            }else{
                                return ResponseGenerator.generateFailureResponse(messageResponse, "Only "+currentCapacity+" persons capacity left for this time slot");
                            }
                        }
                    } else {
                        return ResponseGenerator.generateFailureResponse(messageResponse, "Mobile number already exists.");
                    }
                } else {
                    return ResponseGenerator.generateFailureResponse(messageResponse, "Email address already exists.");
                }
            } else {
                return ResponseGenerator.generateFailureResponse(messageResponse, "InactiveCampaign");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Something went wrong while processing your request.");
        }
    }
}












