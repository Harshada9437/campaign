package com.campaign.rest.response.campaign;

import com.campaign.rest.response.util.GenericResponse;

import java.util.List;

public class CampaignListResponse implements GenericResponse {
    private List<CampaignResponse> campaigns;
    private String message;
    private String messageType;

    public List<CampaignResponse> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignResponse> campaigns) {
        this.campaigns = campaigns;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    @Override
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "CampaignListResponse{" +
                "campaigns=" + campaigns +
                ", message='" + message + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
