package com.campaign.rest.response.campaign;

public class CouponResponse {
    private String date;
    private String time;
    private int isAvailable;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "CouponResponse{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
