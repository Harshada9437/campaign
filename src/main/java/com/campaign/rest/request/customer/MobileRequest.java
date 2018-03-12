package com.campaign.rest.request.customer;

import java.util.Objects;

public class MobileRequest {
    private String mobile;
    private String campaignId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MobileRequest that = (MobileRequest) o;

        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        return campaignId != null ? campaignId.equals(that.campaignId) : that.campaignId == null;
    }

    @Override
    public int hashCode() {
        int result = mobile != null ? mobile.hashCode() : 0;
        result = 31 * result + (campaignId != null ? campaignId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MobileRequest{" +
                "mobile='" + mobile + '\'' +
                ", campaignId='" + campaignId + '\'' +
                '}';
    }
}
