package com.campaign.rest.response.campaign;

import com.campaign.rest.request.campaign.HeaderDetails;
import com.campaign.rest.request.campaign.SlotDetails;
import com.campaign.rest.request.campaign.SmsDetails;
import com.campaign.rest.request.campaign.SmtpDetails;

import java.util.List;

public class CampaignResponse {
    private int  id;
    private int  createdBy;
    private int noOfPerson;
    private int isAllowOnFull;
    private int isPublished;
    private int isPromoCampaign;
    private HeaderDetails header;
    private SmsDetails sms;
    private SmtpDetails smtp;
    private String name;
    private String status;
    private String campaignLocation;
    private String desc;
    private String linkHashId;
    private String confirmSms;
    private String confirmEmail;
    private String notifyEmail;
    private String campaignOverText;
    private List<SlotDetails> slots;
    private List<String> dates;
    private String slotFullText;
    private String emailSubject;

    public CampaignResponse(String campaignLocation,String status,int isPromoCampaign,int isPublished,String emailSubject,String linkHashId,int id, int createdBy, int noOfPerson, int isAllowOnFull, HeaderDetails header, SmsDetails sms, SmtpDetails smtp, String name, String desc, String confirmSms, String confirmEmail, String notifyEmail, String campaignOverText, String slotFullText) {
        this.campaignLocation = campaignLocation;
        this.status = status;
        this.isPromoCampaign = isPromoCampaign;
        this.isPublished = isPublished;
        this.emailSubject = emailSubject;
        this.linkHashId = linkHashId;
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

    public int getIsPromoCampaign() {
        return isPromoCampaign;
    }

    public String getCampaignLocation() {
        return campaignLocation;
    }

    public String getStatus() {
        return status;
    }

    public int getIsPublished() {
        return isPublished;
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

    public String getLinkHashId() {
        return linkHashId;
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

    @Override
    public String toString() {
        return "CampaignResponse{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", noOfPerson=" + noOfPerson +
                ", isAllowOnFull=" + isAllowOnFull +
                ", isPublished=" + isPublished +
                ", isPromoCampaign=" + isPromoCampaign +
                ", header=" + header +
                ", sms=" + sms +
                ", smtp=" + smtp +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", campaignLocation='" + campaignLocation + '\'' +
                ", desc='" + desc + '\'' +
                ", linkHashId='" + linkHashId + '\'' +
                ", confirmSms='" + confirmSms + '\'' +
                ", confirmEmail='" + confirmEmail + '\'' +
                ", notifyEmail='" + notifyEmail + '\'' +
                ", campaignOverText='" + campaignOverText + '\'' +
                ", slots=" + slots +
                ", dates=" + dates +
                ", slotFullText='" + slotFullText + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                '}';
    }
}
