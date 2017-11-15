package com.campaign.api;

import com.campaign.bo.request.HeaderDetailsBO;
import com.campaign.bo.request.HeaderUpdateRequestBO;
import com.campaign.requestHandlers.HeaderRequestHandler;
import com.campaign.rest.request.campaign.HeaderDetails;
import com.campaign.rest.request.campaign.HeaderDetailsRequest;
import com.campaign.rest.request.campaign.HeaderUpdateRequest;
import com.campaign.rest.response.header.GetHeaderResponse;
import com.campaign.rest.response.header.HeaderListResponse;
import com.campaign.rest.response.header.HeaderResponse;
import com.campaign.rest.response.util.MessageResponse;
import com.campaign.rest.response.util.ResponseGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/header")
public class HeaderService {
    @GET
    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHeaderList() throws Exception {
        HeaderRequestHandler headerRequestHandler = new HeaderRequestHandler();
        HeaderListResponse headerListResponse = new HeaderListResponse();
        MessageResponse messageResponse = new MessageResponse();
        try {
            headerListResponse.setHeaders(headerRequestHandler.getHeaders());
            return ResponseGenerator.generateSuccessResponse(headerListResponse, "List of  headers.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve.");
        }
    }

    @GET
    @Path("/info/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHeaderInfo(@PathParam("id") int id) {
        HeaderRequestHandler headerRequestHandler = new HeaderRequestHandler();
        GetHeaderResponse headerListResponse;
        MessageResponse messageResponse = new MessageResponse();
        try {
            headerListResponse = headerRequestHandler.getHeaderInfo(id);
            return ResponseGenerator.generateSuccessResponse(headerListResponse, "Details of  headers.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve.");
        }
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHeader(HeaderDetailsRequest headerDetails) throws Exception {
        HeaderDetailsBO headerDetailsBO = new HeaderDetailsBO();
        HeaderRequestHandler headerRequestHandler = new HeaderRequestHandler();
        MessageResponse messageResponse = new MessageResponse();

        headerDetailsBO.setBackground(headerDetails.getBackground());
        headerDetailsBO.setLogo(headerDetails.getLogo());
        headerDetailsBO.setIcon(headerDetails.getIcon());
        headerDetailsBO.setDomain(headerDetails.getDomain());
        headerDetailsBO.setBorder(headerDetails.getBorder());
        headerDetailsBO.setForecolor(headerDetails.getForecolor());
        headerDetailsBO.setInfo(headerDetails.getInfo());
        headerDetailsBO.setName(headerDetails.getName());
        headerDetailsBO.setSmsDetails(headerDetails.getSmsDetails());
        headerDetailsBO.setSmtpDetails(headerDetails.getSmtpDetails());

        try {
            int id = headerRequestHandler.createHeader(headerDetailsBO);
            return ResponseGenerator.generateSuccessResponse(messageResponse, String.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to create.");
        }
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHeader(HeaderUpdateRequest headerUpdateRequest) throws Exception {
        HeaderUpdateRequestBO requestBO = new HeaderUpdateRequestBO();
        HeaderRequestHandler headerRequestHandler = new HeaderRequestHandler();
        MessageResponse messageResponse = new MessageResponse();

        requestBO.setId(headerUpdateRequest.getId());
        requestBO.setBackground(headerUpdateRequest.getBackground());
        requestBO.setLogo(headerUpdateRequest.getLogo());
        requestBO.setIcon(headerUpdateRequest.getIcon());
        requestBO.setDomain(headerUpdateRequest.getDomain());
        requestBO.setBorder(headerUpdateRequest.getBorder());
        requestBO.setForecolor(headerUpdateRequest.getForecolor());
        requestBO.setInfo(headerUpdateRequest.getInfo());
        requestBO.setName(headerUpdateRequest.getName());
        requestBO.setSmsDetails(headerUpdateRequest.getSmsDetails());
        requestBO.setSmtpDetails(headerUpdateRequest.getSmtpDetails());


        try {
            headerRequestHandler.updateHeader(requestBO);
            return ResponseGenerator.generateSuccessResponse(messageResponse, "Header updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to create.");
        }
    }
}
