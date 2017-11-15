package com.campaign.dto.campaign;

import com.campaign.rest.request.campaign.SmsDetails;
import com.campaign.rest.request.campaign.SmtpDetails;

public class HeaderDTO {
    private int id;
    private SmsDetails sms;
    private SmtpDetails smtp;
    private int smsAccId;
    private int emailAccId;
    private String name;
    private String info;
    private String logo;
    private String icon;
    private String background;
    private String forecolor;
    private String border;
    private String domain;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSmsAccId() {
        return smsAccId;
    }

    public void setSmsAccId(int smsAccId) {
        this.smsAccId = smsAccId;
    }

    public int getEmailAccId() {
        return emailAccId;
    }

    public void setEmailAccId(int emailAccId) {
        this.emailAccId = emailAccId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getForecolor() {
        return forecolor;
    }

    public void setForecolor(String forecolor) {
        this.forecolor = forecolor;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeaderDTO headerDTO = (HeaderDTO) o;

        if (id != headerDTO.id) return false;
        if (smsAccId != headerDTO.smsAccId) return false;
        if (emailAccId != headerDTO.emailAccId) return false;
        if (sms != null ? !sms.equals(headerDTO.sms) : headerDTO.sms != null) return false;
        if (smtp != null ? !smtp.equals(headerDTO.smtp) : headerDTO.smtp != null) return false;
        if (name != null ? !name.equals(headerDTO.name) : headerDTO.name != null) return false;
        if (info != null ? !info.equals(headerDTO.info) : headerDTO.info != null) return false;
        if (logo != null ? !logo.equals(headerDTO.logo) : headerDTO.logo != null) return false;
        if (icon != null ? !icon.equals(headerDTO.icon) : headerDTO.icon != null) return false;
        if (background != null ? !background.equals(headerDTO.background) : headerDTO.background != null) return false;
        if (forecolor != null ? !forecolor.equals(headerDTO.forecolor) : headerDTO.forecolor != null) return false;
        if (border != null ? !border.equals(headerDTO.border) : headerDTO.border != null) return false;
        return domain != null ? domain.equals(headerDTO.domain) : headerDTO.domain == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sms != null ? sms.hashCode() : 0);
        result = 31 * result + (smtp != null ? smtp.hashCode() : 0);
        result = 31 * result + smsAccId;
        result = 31 * result + emailAccId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (background != null ? background.hashCode() : 0);
        result = 31 * result + (forecolor != null ? forecolor.hashCode() : 0);
        result = 31 * result + (border != null ? border.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HeaderDTO{" +
                "id=" + id +
                ", sms=" + sms +
                ", smtp=" + smtp +
                ", smsAccId=" + smsAccId +
                ", emailAccId=" + emailAccId +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", logo='" + logo + '\'' +
                ", icon='" + icon + '\'' +
                ", background='" + background + '\'' +
                ", forecolor='" + forecolor + '\'' +
                ", border='" + border + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
