package com.campaign.rest.response.campaign;

import com.campaign.rest.request.campaign.HeaderDetails;
import com.campaign.rest.request.campaign.SmsDetails;
import com.campaign.rest.request.campaign.SmtpDetails;
import com.campaign.rest.response.util.GenericResponse;

public class GetCampaignResponse implements GenericResponse{
    private int  id;
    private int  createdBy;
    private int noOfPerson;
    private int isAllowOnFull;
    private HeaderDetails header;
    private SmsDetails sms;
    private SmtpDetails smtp;
    private String name;
    private String desc;
    private String confirmSms;
    private String confirmEmail;
    private String notifyEmail;
    private String campaignOverText;
    private String message;
    private String messageType;
    private String slotFullText;

    public GetCampaignResponse(int id, int createdBy, int noOfPerson, int isAllowOnFull, HeaderDetails header, SmsDetails sms, SmtpDetails smtp, String name, String desc, String confirmSms, String confirmEmail, String notifyEmail, String campaignOverText, String slotFullText) {
        this.id = id;
        this.createdBy = createdBy;
        this.noOfPerson = noOfPerson;
        this.isAllowOnFull = isAllowOnFull;
        this.header = header;
        this.sms = sms;
        this.smtp = smtp;
        this.name = name;
        this.desc = desc;
        this.confirmSms = confirmSms;
        this.confirmEmail = confirmEmail;
        this.notifyEmail = notifyEmail;
        this.campaignOverText = campaignOverText;
        this.slotFullText = slotFullText;
    }

    public int getId() {
        return id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getNoOfPerson() {
        return noOfPerson;
    }

    public int getIsAllowOnFull() {
        return isAllowOnFull;
    }

    public HeaderDetails getHeader() {
        return header;
    }

    public SmsDetails getSms() {
        return sms;
    }

    public SmtpDetails getSmtp() {
        return smtp;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getConfirmSms() {
        return confirmSms;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public String getCampaignOverText() {
        return campaignOverText;
    }

    public String getSlotFullText() {
        return slotFullText;
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
        return "GetCampaignResponse{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", noOfPerson=" + noOfPerson +
                ", isAllowOnFull=" + isAllowOnFull +
                ", header=" + header +
                ", sms=" + sms +
                ", smtp=" + smtp +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", confirmSms='" + confirmSms + '\'' +
                ", confirmEmail='" + confirmEmail + '\'' +
                ", notifyEmail='" + notifyEmail + '\'' +
                ", campaignOverText='" + campaignOverText + '\'' +
                ", message='" + message + '\'' +
                ", messageType='" + messageType + '\'' +
                ", slotFullText='" + slotFullText + '\'' +
                '}';
    }
}
