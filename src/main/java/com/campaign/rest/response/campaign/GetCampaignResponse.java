package com.campaign.rest.response.campaign;

import com.campaign.rest.request.campaign.HeaderDetails;
import com.campaign.rest.request.campaign.SlotDetails;
import com.campaign.rest.request.campaign.SmsDetails;
import com.campaign.rest.request.campaign.SmtpDetails;
import com.campaign.rest.response.util.GenericResponse;

import java.util.List;

public class GetCampaignResponse implements GenericResponse{
    private int  id;
    private int  createdBy;
    private int noOfPerson;
    private int isAllowOnFull;
    private int isPublished;
    private HeaderDetails header;
    private SmsDetails sms;
    private SmtpDetails smtp;
    private String name;
    private String status;
    private String linkHashId;
    private String desc;
    private String confirmSms;
    private String confirmEmail;
    private String notifyEmail;
    private String emailSubject;
    private String campaignOverText;
    private String message;
    private String messageType;
    private List<SlotDetails> slots;
    private List<String> dates;
    private String slotFullText;

    public GetCampaignResponse(String status,String linkHashId,String emailSubject,int id,int isPublished, int createdBy, int noOfPerson, int isAllowOnFull, HeaderDetails header, SmsDetails sms, SmtpDetails smtp, String name, String desc, String confirmSms, String confirmEmail, String notifyEmail, String campaignOverText, String slotFullText) {
        this.status = status;
        this.linkHashId = linkHashId;
        this.emailSubject = emailSubject;
        this.id = id;
        this.isPublished = isPublished;
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

    public String getStatus() {
        return status;
    }

    public String getLinkHashId() {
        return linkHashId;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public List<SlotDetails> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotDetails> slots) {
        this.slots = slots;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public int getIsPublished() {
        return isPublished;
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
                ", isPublished=" + isPublished +
                ", header=" + header +
                ", sms=" + sms +
                ", smtp=" + smtp +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", linkHashId='" + linkHashId + '\'' +
                ", desc='" + desc + '\'' +
                ", confirmSms='" + confirmSms + '\'' +
                ", confirmEmail='" + confirmEmail + '\'' +
                ", notifyEmail='" + notifyEmail + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", campaignOverText='" + campaignOverText + '\'' +
                ", message='" + message + '\'' +
                ", messageType='" + messageType + '\'' +
                ", slots=" + slots +
                ", dates=" + dates +
                ", slotFullText='" + slotFullText + '\'' +
                '}';
    }
}
