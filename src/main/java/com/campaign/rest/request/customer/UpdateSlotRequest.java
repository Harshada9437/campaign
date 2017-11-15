package com.campaign.rest.request.customer;

/**
 * Created by System-3 on 12/21/2016.
 */
public class UpdateSlotRequest {
    private int id;
    private int campaignId;
    private String time;
    private String date;

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateSlotRequest that = (UpdateSlotRequest) o;

        if (id != that.id) return false;
        if (campaignId != that.campaignId) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + campaignId;
        return result;
    }

    @Override
    public String toString() {
        return "UpdateSlotRequest{" +
                "id=" + id +
                ", campaignId=" + campaignId +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
