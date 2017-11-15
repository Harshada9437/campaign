package com.campaign.dto.campaign;

public class CampaignSlotDTO {
    private String status;
    private boolean available;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CampaignSlotDTO that = (CampaignSlotDTO) o;

        if (available != that.available) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (available ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CampaignSlotDTO{" +
                "status='" + status + '\'' +
                ", available=" + available +
                '}';
    }
}
