package com.campaign.rest.response.campaign;

import com.campaign.rest.response.util.GenericResponse;

import java.util.List;

public class AvailableSlotResponse implements GenericResponse {
private List<DateResponse> dates;
private String message;
private String messageType;

    public List<DateResponse> getDates() {
        return dates;
    }

    public void setDates(List<DateResponse> dates) {
        this.dates = dates;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    @Override
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "AvailableSlotResponse{" +
                "dates=" + dates +
                ", message='" + message + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
