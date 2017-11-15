package com.campaign.rest.request.campaign;

public class SlotDetails {
    private String time;
    private int capacity;
    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlotDetails that = (SlotDetails) o;

        if (capacity != that.capacity) return false;
        if (totalCount != that.totalCount) return false;
        return time != null ? time.equals(that.time) : that.time == null;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + capacity;
        result = 31 * result + totalCount;
        return result;
    }

    @Override
    public String toString() {
        return "SlotDetails{" +
                "time='" + time + '\'' +
                ", capacity=" + capacity +
                ", totalCount=" + totalCount +
                '}';
    }
}
