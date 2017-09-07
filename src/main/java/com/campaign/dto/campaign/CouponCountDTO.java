package com.campaign.dto.campaign;

public class CouponCountDTO {
    private int occupied;
    private int max;
    private int isAvailable;

    public int getOccupied() {
        return occupied;
    }

    public int getMax() {
        return max;
    }

    public int getIsAvailable() {
        return isAvailable;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setIsAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "CouponCountDTO{" +
                "occupied=" + occupied +
                ", max=" + max +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
