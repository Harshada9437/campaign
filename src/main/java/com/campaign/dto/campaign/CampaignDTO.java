package com.campaign.dto.campaign;

import com.campaign.rest.request.campaign.HeaderDetails;
import com.campaign.rest.request.campaign.SlotDetails;
import com.campaign.rest.request.campaign.SmsDetails;
import com.campaign.rest.request.campaign.SmtpDetails;

import java.util.List;

public class CampaignDTO {
    private int  id;
    private int  createdBy;
    private int  isPublished;
    private int noOfPerson;
    private int isAllowOnFull;
    private int headerId;
    private int isPromoCampaign;
    private String status;
    private String campaignLocation;
    private HeaderDetails header;
    private SmsDetails sms;
    private SmtpDetails smtp;
    private String name;
    private String linkHashId;
    private String emailSubject;
    private String hashId;
    private String desc;
    private String confirmSms;
    private String confirmEmail;
    private String notifyEmail;
    private String campaignOverText;
    private String slotFullText;
    private List<SlotDetails> slots;
    private List<String> dates;

    public int getIsPromoCampaign() {
        return isPromoCampaign;
    }

    public void setIsPromoCampaign(int isPromoCampaign) {
        this.isPromoCampaign = isPromoCampaign;
    }

    public String getCampaignLocation() {
        return campaignLocation;
    }

    public void setCampaignLocation(String campaignLocation) {
        this.campaignLocation = campaignLocation;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(int isPublished) {
        this.isPublished = isPublished;
    }

    public String getLinkHashId() {
        return linkHashId;
    }

    public void setLinkHashId(String linkHashId) {
        this.linkHashId = linkHashId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoOfPerson() {
        return noOfPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNoOfPerson(int noOfPerson) {
        this.noOfPerson = noOfPerson;
    }

    public int getIsAllowOnFull() {
        return isAllowOnFull;
    }

    public void setIsAllowOnFull(int isAllowOnFull) {
        this.isAllowOnFull = isAllowOnFull;
    }

    public int getHeaderId() {
        return headerId;
    }

    public void setHeaderId(int headerId) {
        this.headerId = headerId;
    }

    public HeaderDetails getHeader() {
        return header;
    }

    public void setHeader(HeaderDetails header) {
        this.header = header;
    }

    public SmsDetails getSms() {
        return sms;
    }

    public void setSms(SmsDetails sms) {
        this.sms = sms;
    }

    public SmtpDetails getSmtp() {
        return smtp;
    }

    public void setSmtp(SmtpDetails smtp) {
        this.smtp = smtp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getConfirmSms() {
        return confirmSms;
    }

    public void setConfirmSms(String confirmSms) {
        this.confirmSms = confirmSms;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public String getCampaignOverText() {
        return campaignOverText;
    }

    public void setCampaignOverText(String campaignOverText) {
        this.campaignOverText = campaignOverText;
    }

    public String getSlotFullText() {
        return slotFullText;
    }

    public void setSlotFullText(String slotFullText) {
        this.slotFullText = slotFullText;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CampaignDTO that = (CampaignDTO) o;

        if (id != that.id) return false;
        if (createdBy != that.createdBy) return false;
        if (isPublished != that.isPublished) return false;
        if (noOfPerson != that.noOfPerson) return false;
        if (isAllowOnFull != that.isAllowOnFull) return false;
        if (headerId != that.headerId) return false;
        if (isPromoCampaign != that.isPromoCampaign) return false;
        if (header != null ? !header.equals(that.header) : that.header != null) return false;
        if (sms != null ? !sms.equals(that.sms) : that.sms != null) return false;
        if (smtp != null ? !smtp.equals(that.smtp) : that.smtp != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (linkHashId != null ? !linkHashId.equals(that.linkHashId) : that.linkHashId != null) return false;
        if (emailSubject != null ? !emailSubject.equals(that.emailSubject) : that.emailSubject != null) return false;
        if (hashId != null ? !hashId.equals(that.hashId) : that.hashId != null) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
        if (confirmSms != null ? !confirmSms.equals(that.confirmSms) : that.confirmSms != null) return false;
        if (confirmEmail != null ? !confirmEmail.equals(that.confirmEmail) : that.confirmEmail != null) return false;
        if (notifyEmail != null ? !notifyEmail.equals(that.notifyEmail) : that.notifyEmail != null) return false;
        if (campaignOverText != null ? !campaignOverText.equals(that.campaignOverText) : that.campaignOverText != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (campaignLocation != null ? !campaignLocation.equals(that.campaignLocation) : that.campaignLocation != null) return false;
        if (slotFullText != null ? !slotFullText.equals(that.slotFullText) : that.slotFullText != null) return false;
        if (slots != null ? !slots.equals(that.slots) : that.slots != null) return false;
        return dates != null ? dates.equals(that.dates) : that.dates == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + createdBy;
        result = 31 * result + isPublished;
        result = 31 * result + isPromoCampaign;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + noOfPerson;
        result = 31 * result + isAllowOnFull;
        result = 31 * result + headerId;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (sms != null ? sms.hashCode() : 0);
        result = 31 * result + (smtp != null ? smtp.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (linkHashId != null ? linkHashId.hashCode() : 0);
        result = 31 * result + (emailSubject != null ? emailSubject.hashCode() : 0);
        result = 31 * result + (campaignLocation != null ? campaignLocation.hashCode() : 0);
        result = 31 * result + (hashId != null ? hashId.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (confirmSms != null ? confirmSms.hashCode() : 0);
        result = 31 * result + (confirmEmail != null ? confirmEmail.hashCode() : 0);
        result = 31 * result + (notifyEmail != null ? notifyEmail.hashCode() : 0);
        result = 31 * result + (campaignOverText != null ? campaignOverText.hashCode() : 0);
        result = 31 * result + (slotFullText != null ? slotFullText.hashCode() : 0);
        result = 31 * result + (slots != null ? slots.hashCode() : 0);
        result = 31 * result + (dates != null ? dates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CampaignDTO{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", isPublished=" + isPublished +
                ", isPromoCampaign=" + isPromoCampaign +
                ", noOfPerson=" + noOfPerson +
                ", isAllowOnFull=" + isAllowOnFull +
                ", headerId=" + headerId +
                ", header=" + header +
                ", sms=" + sms +
                ", smtp=" + smtp +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", linkHashId='" + linkHashId + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", campaignLocation='" + campaignLocation + '\'' +
                ", hashId='" + hashId + '\'' +
                ", desc='" + desc + '\'' +
                ", confirmSms='" + confirmSms + '\'' +
                ", confirmEmail='" + confirmEmail + '\'' +
                ", notifyEmail='" + notifyEmail + '\'' +
                ", campaignOverText='" + campaignOverText + '\'' +
                ", slotFullText='" + slotFullText + '\'' +
                ", slots=" + slots +
                ", dates=" + dates +
                '}';
    }
}
