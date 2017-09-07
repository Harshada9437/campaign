package com.campaign.rest.request.campaign;

public class SlotDetails {
    private String time;
    private int capacity;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "SlotDetails{" +
                "time='" + time + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
