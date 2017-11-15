package com.campaign.rest.response.campaign;

public class CampaignSlotResponse {
    private String status;
    private boolean available;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "CampaignSlotResponse{" +
                "status='" + status + '\'' +
                ", available=" + available +
                '}';
    }
}
