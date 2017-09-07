package com.campaign.rest.response.customer;

import com.campaign.rest.response.util.GenericResponse;

import java.util.List;

/**
 * Created by System-2 on 12/21/2016.
 */
public class CustomerListResponse implements GenericResponse{
    private List<CustomerResponse> customers;
    private String message;
    private String messageType;

    public List<CustomerResponse> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerResponse> customers) {
        this.customers = customers;
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
        return "CustomerListResponse{" +
                "customers=" + customers +
                ", message='" + message + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
