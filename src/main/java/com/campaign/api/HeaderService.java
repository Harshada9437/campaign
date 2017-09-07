package com.campaign.api;

import com.campaign.requestHandlers.HeaderRequestHandler;
import com.campaign.rest.response.header.HeaderListResponse;
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
    public Response getCustomerList() throws Exception {
        HeaderRequestHandler headerRequestHandler = new HeaderRequestHandler();
        HeaderListResponse headerListResponse = new HeaderListResponse();
        MessageResponse messageResponse = new MessageResponse();
        try {
            headerListResponse.setHeaders(headerRequestHandler.getHeaders());
            return ResponseGenerator.generateSuccessResponse(headerListResponse, "List of  campaigns.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseGenerator.generateFailureResponse(messageResponse, "Failed to retrieve. ");
        }
    }
}
