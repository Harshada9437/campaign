package com.bbqcampaign.spring.model;

import java.io.Serializable;
        import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
        import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class Campaign implements Serializable{

    private static final long serialVersionUID = -7788619177798333712L;

    private int id;
    private int noOfPersons;
    private int createdById;
    private int isAllowOnFull;
    private String name;
    private String fromDate;
    private String toDate;
    private String desc;
    private String confirmSms;
    private String confirmEmail;
    private String slotFullText;
    private String campaignOverText;
    private String notifyEmail;
    private String createdDate;
    private String shortCode;
    private String apikey;
    private String smtpHost;
    private String smtpEmail;
    private String smtpPassword;
    private String smtpFrom;
    private String headerName;
    private String headerLogo;
    private String headerIcon;
    private String headerBackground;
    private String headerForecolor;
    private String headerBorder;
    private String headerDomain;
    private String headerInfo;
    private List<SlotDetails> slots;
    private int SmtpPort;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfPersons() {
        return noOfPersons;
    }

    public void setNoOfPersons(int noOfPersons) {
        this.noOfPersons = noOfPersons;
    }

    public int getCreatedById() {
        return createdById;
    }

    public void setCreatedById(int createdById) {
        this.createdById = createdById;
    }

    public int getIsAllowOnFull() {
        return isAllowOnFull;
    }

    public void setIsAllowOnFull(int isAllowOnFull) {
        this.isAllowOnFull = isAllowOnFull;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
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

    public String getSlotFullText() {
        return slotFullText;
    }

    public void setSlotFullText(String slotFullText) {
        this.slotFullText = slotFullText;
    }

    public String getCampaignOverText() {
        return campaignOverText;
    }

    public void setCampaignOverText(String campaignOverText) {
        this.campaignOverText = campaignOverText;
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpEmail() {
        return smtpEmail;
    }

    public void setSmtpEmail(String smtpEmail) {
        this.smtpEmail = smtpEmail;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public String getSmtpFrom() {
        return smtpFrom;
    }

    public void setSmtpFrom(String smtpFrom) {
        this.smtpFrom = smtpFrom;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderLogo() {
        return headerLogo;
    }

    public void setHeaderLogo(String headerLogo) {
        this.headerLogo = headerLogo;
    }

    public String getHeaderIcon() {
        return headerIcon;
    }

    public void setHeaderIcon(String headerIcon) {
        this.headerIcon = headerIcon;
    }

    public String getHeaderBackground() {
        return headerBackground;
    }

    public void setHeaderBackground(String headerBackground) {
        this.headerBackground = headerBackground;
    }

    public String getHeaderForecolor() {
        return headerForecolor;
    }

    public void setHeaderForecolor(String headerForecolor) {
        this.headerForecolor = headerForecolor;
    }

    public String getHeaderBorder() {
        return headerBorder;
    }

    public void setHeaderBorder(String headerBorder) {
        this.headerBorder = headerBorder;
    }

    public String getHeaderDomain() {
        return headerDomain;
    }

    public void setHeaderDomain(String headerDomain) {
        this.headerDomain = headerDomain;
    }

    public String getHeaderInfo() {
        return headerInfo;
    }

    public void setHeaderInfo(String headerInfo) {
        this.headerInfo = headerInfo;
    }

    public List<SlotDetails> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotDetails> slots) {
        this.slots = slots;
    }

    public int getSmtpPort() {
        return SmtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        SmtpPort = smtpPort;
    }


}
