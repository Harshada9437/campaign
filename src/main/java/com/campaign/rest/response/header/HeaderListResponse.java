package com.campaign.rest.response.header;

import com.campaign.rest.response.util.GenericResponse;

import java.util.List;

public class HeaderListResponse implements GenericResponse {
    private List<HeaderResponse> headers;
    private String message;
    private String messageType;

    public List<HeaderResponse> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HeaderResponse> headers) {
        this.headers = headers;
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
        return "HeaderListResponse{" +
                "headers=" + headers +
                ", message='" + message + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
