package com.campaign.api;

import com.campaign.Exception.CampaignNotFoundException;
import com.campaign.bo.request.CampaignCreateRequestBO;
import com.campaign.bo.request.CampaignUpdateRequestBO;
import com.campaign.requestHandlers.CampaignRequestHandler;
import com.campaign.requestHandlers.CustomerRequestHandler;
import com.campaign.rest.request.campaign.CampaignCreateRequest;
import com.campaign.rest.request.campaign.CampaignUpdateRequest;
import com.campaign.rest.response.campaign.AvailableSlotResponse;
import com.campaign.rest.response.campaign.CampaignInfoResponse;
import com.campaign.rest.response.campaign.CampaignListResponse;
import com.campaign.rest.response.campaign.GetCampaignResponse;
import com.campaign.rest.response.util.MessageResponse;
import com.campaign.rest.response.util.ResponseGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/campaign")
public class CampaignService {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createCampaign(CampaignCreateRequest campaignCreateRequest) throws Exception {
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
        campaignCreateRequestBO.setIsPublished(campaignCreateRequest.getIsPublished());
        campaignCreateRequestBO.setEmailSubject(campaignCreateRequest.getEmailSubject());

        MessageResponse messageResponse = new MessageResponse();
        CampaignRequestHandler campaignRequestHandler = new CampaignRequestHandler();
        String id;
        try {
              id = campaignRequestHandler.createCampaign(campaignCreateRequestBO);
              return ResponseGenerator.generateSuccessResponse(messageResponse,id);
        } catch (Exception e1) {
            e1.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "New campaign registration failed");
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateCampaign(CampaignUpdateRequest campaignUpdateRequest) throws Exception {
        CampaignUpdateRequestBO campaignUpdateRequestBO = new CampaignUpdateRequestBO();

        campaignUpdateRequestBO.setId(campaignUpdateRequest.getId());
        campaignUpdateRequestBO.setHeaderId(campaignUpdateRequest.getHeaderId());
        campaignUpdateRequestBO.setStatus(campaignUpdateRequest.getStatus());
        campaignUpdateRequestBO.setIsPublished(campaignUpdateRequest.getIsPublished());
        campaignUpdateRequestBO.setName(campaignUpdateRequest.getName());
        campaignUpdateRequestBO.setDesc(campaignUpdateRequest.getDesc());
        campaignUpdateRequestBO.setDates(campaignUpdateRequest.getDates());
        campaignUpdateRequestBO.setNoOfPerson(campaignUpdateRequest.getNoOfPerson());
         campaignUpdateRequestBO.setSlots(campaignUpdateRequest.getSlots());
        campaignUpdateRequestBO.setCampaignOverText(campaignUpdateRequest.getCampaignOverText());
        campaignUpdateRequestBO.setConfirmEmail(campaignUpdateRequest.getConfirmEmail());
        campaignUpdateRequestBO.setConfirmSms(campaignUpdateRequest.getConfirmSms());
        campaignUpdateRequestBO.setSlotFullText(campaignUpdateRequest.getSlotFullText());
        campaignUpdateRequestBO.setNotifyEmail(campaignUpdateRequest.getNotifyEmail());
        campaignUpdateRequestBO.setIsallowOnFull(campaignUpdateRequest.getIsallowOnFull());
        campaignUpdateRequestBO.setEmailSubject(campaignUpdateRequest.getEmailSubject());

        MessageResponse messageResponse = new MessageResponse();
        CampaignRequestHandler campaignRequestHandler = new CampaignRequestHandler();
        Boolean isProcessed = campaignRequestHandler.updateCampaign(campaignUpdateRequestBO);
        try {
            if(isProcessed) {
                return ResponseGenerator.generateSuccessResponse(messageResponse, "Campaign details updated successfully.");
            }else{
                return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to update the campaign details.");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to update the campaign details.");
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
    public Response getCampaignInfo(@HeaderParam("sessionId") String sessionId,@PathParam("campaign_id") String campaignId) throws Exception {

        CampaignRequestHandler campaignRequestHandler = new CampaignRequestHandler();
        CampaignInfoResponse getCampaignResponse;
        GetCampaignResponse getCampaignResp;
        MessageResponse messageResponse = new MessageResponse();
        try {
            if(sessionId==null) {
                getCampaignResponse = campaignRequestHandler.getCampaignInfo(campaignId);
                return ResponseGenerator.generateSuccessResponse(getCampaignResponse, "Campaign details");
            }else{
                getCampaignResp=campaignRequestHandler.getCampaign(0,campaignId);
                return ResponseGenerator.generateSuccessResponse(getCampaignResp, "Campaign details");
            }

        } catch (CampaignNotFoundException e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Campaign information found invalid campaign id");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve. ");
        }
    }

    @GET
    @Path("/details/{campaign_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCampaignInfo(@PathParam("campaign_id") int campaignId) throws Exception {

        CampaignRequestHandler campaignRequestHandler = new CampaignRequestHandler();
        GetCampaignResponse getCampaignResponse;
        MessageResponse messageResponse = new MessageResponse();
        try {
            getCampaignResponse=campaignRequestHandler.getCampaign(campaignId, "");
            return ResponseGenerator.generateSuccessResponse(getCampaignResponse, "Campaign details");
        } catch (CampaignNotFoundException e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Campaign information found invalid campaign id");
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve. ");
        }
    }


    @GET
    @Path("/list/{campaign_admin}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCampaignList(@PathParam("campaign_admin") int campaignAdmin) throws Exception {
        CampaignRequestHandler campaignRequestHandler = new CampaignRequestHandler();
        CampaignListResponse campaignListResponse = new CampaignListResponse();
        MessageResponse messageResponse = new MessageResponse();
        try {
            campaignListResponse.setCampaigns(campaignRequestHandler.getCampaigns(campaignAdmin));
            return ResponseGenerator.generateSuccessResponse(campaignListResponse, "List of  campaigns.");
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve.");
        }
    }
}
