package com.campaign.rest.response.campaign;

import java.util.List;

public class DateResponse {
    private List<Slots> time;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Slots> getTime() {
        return time;
    }

    public void setTime(List<Slots> time) {
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
