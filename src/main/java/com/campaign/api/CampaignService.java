package com.campaign.api;

import com.campaign.bo.request.CampaignCreateRequestBO;
import com.campaign.requestHandlers.CampaignRequestHandler;
import com.campaign.requestHandlers.CustomerRequestHandler;
import com.campaign.rest.request.campaign.CampaignCreateRequest;
import com.campaign.rest.response.campaign.AvailableSlotResponse;
import com.campaign.rest.response.campaign.CampaignListResponse;
import com.campaign.rest.response.campaign.GetCampaignResponse;
import com.campaign.rest.response.util.MessageResponse;
import com.campaign.rest.response.util.ResponseGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/campaign")
public class CampaignService {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createAppUser(CampaignCreateRequest campaignCreateRequest) throws Exception {
        CampaignCreateRequestBO campaignCreateRequestBO = new CampaignCreateRequestBO();

        campaignCreateRequestBO.setName(campaignCreateRequest.getName());
        campaignCreateRequestBO.setDesc(campaignCreateRequest.getDesc());
        campaignCreateRequestBO.setDates(campaignCreateRequest.getDates());
        campaignCreateRequestBO.setNoOfPerson(campaignCreateRequest.getNoOfPerson());
        campaignCreateRequestBO.setSlots(campaignCreateRequest.getSlots());
        campaignCreateRequestBO.setCampaignOverText(campaignCreateRequest.getCampaignOverText());
        campaignCreateRequestBO.setConfirmEmail(campaignCreateRequest.getConfirmEmail());
        campaignCreateRequestBO.setConfirmSms(campaignCreateRequest.getConfirmSms());
        campaignCreateRequestBO.setSlotFullText(campaignCreateRequest.getSlotFullText());
        campaignCreateRequestBO.setNotifyEmail(campaignCreateRequest.getNotifyEmail());
        campaignCreateRequestBO.setHeaderId(campaignCreateRequest.getHeaderId());
        campaignCreateRequestBO.setCreatedBy(campaignCreateRequest.getCreatedBy());
        campaignCreateRequestBO.setIsallowOnFull(campaignCreateRequest.getIsallowOnFull());

        MessageResponse messageResponse = new MessageResponse();
        CampaignRequestHandler campaignRequestHandler = new CampaignRequestHandler();
        String id;
        try {
              id = campaignRequestHandler.createCampaign(campaignCreateRequestBO);
              return ResponseGenerator.generateSuccessResponse(messageResponse,id);
        } catch (Exception e1) {
            e1.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "New customer registration failed");
        }
    }

    @GET
    @Path("/slots/{campaign_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getsSlots(@PathParam("campaign_id") int id) throws Exception {

        CustomerRequestHandler customerRequestHandler = new CustomerRequestHandler();
        AvailableSlotResponse availableSlotResponse = new AvailableSlotResponse();
        MessageResponse messageResponse = new MessageResponse();
        try {
            availableSlotResponse.setDates(customerRequestHandler.getSlots(id));
            return ResponseGenerator.generateSuccessResponse(availableSlotResponse, "Slots are available.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve. ");
        }
    }

    @GET
    @Path("/info/{campaign_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCampaignInfo(@PathParam("campaign_id") String campaignId) throws Exception {

        CampaignRequestHandler campaignRequestHandler = new CampaignRequestHandler();
        GetCampaignResponse getCampaignResponse;
        MessageResponse messageResponse = new MessageResponse();
        try {
            getCampaignResponse=campaignRequestHandler.getCampaignInfo(campaignId);
            return ResponseGenerator.generateSuccessResponse(getCampaignResponse, "Campaign details");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve. ");
        }
    }


    @GET
    @Path("/list/{campaign_admin}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerList(@PathParam("campaign_admin") int campaignAdmin) throws Exception {
        CampaignRequestHandler campaignRequestHandler = new CampaignRequestHandler();
        CampaignListResponse campaignListResponse = new CampaignListResponse();
        MessageResponse messageResponse = new MessageResponse();
        try {
            campaignListResponse.setCampaigns(campaignRequestHandler.getCampaigns(campaignAdmin));
            return ResponseGenerator.generateSuccessResponse(campaignListResponse, "List of  campaigns.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve. ");
        }
    }
}
