package com.campaign.rest.response.campaign;

public class Slots {
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
        return "Slots{" +
                "time='" + time + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
