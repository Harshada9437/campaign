package com.campaign.rest.response.util;

public class UploadResponse implements GenericResponse {

    private String filePath;
    private String message;
    private String MessageType;

    public String getMessage() {
        return message;
    }

    public String getMessageType() {
        return MessageType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void setMessageType(String messageType)

    {
        this.MessageType = messageType;
    }

    @Override
    public void setMessage(String message)

    {
        this.message = message;
    }
}


