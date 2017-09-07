package com.campaign.rest.response.campaign;

import java.util.List;

public class DateResponse {
    private List<String> time;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "DateResponse{" +
                "time=" + time +
                ", date='" + date + '\'' +
                '}';
    }
}
