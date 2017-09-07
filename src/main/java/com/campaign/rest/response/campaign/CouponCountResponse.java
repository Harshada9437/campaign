package com.campaign.rest.response.campaign;

public class CouponCountResponse {
    private int occupied;
    private int max;
    private int isAvailable;

    public CouponCountResponse(int occupied, int max, int isAvailable) {
        this.occupied = occupied;
        this.max = max;
        this.isAvailable = isAvailable;
    }

    public int getOccupied() {
        return occupied;
    }

    public int getMax() {
        return max;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return "CouponCountResponse{" +
                "occupied=" + occupied +
                ", max=" + max +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
