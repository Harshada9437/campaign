package com.campaign.rest.request.campaign;

public class SmsDetails {
    private int id;
    private String shortCode;
    private String apikey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    @Override
    public String toString() {
        return "SmsDetails{" +
                "id=" + id +
                ", shortCode='" + shortCode + '\'' +
                ", apikey='" + apikey + '\'' +
                '}';
    }
}
