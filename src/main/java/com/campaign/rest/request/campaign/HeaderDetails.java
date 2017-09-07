package com.campaign.rest.request.campaign;

public class HeaderDetails {
    private int id;
    private int smsId;
    private int smtpId;
    private String name;
    private String info;
    private String logo;
    private String icon;
    private String background;
    private String forecolor;
    private String border;
    private String domain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }

    public int getSmtpId() {
        return smtpId;
    }

    public void setSmtpId(int smtpId) {
        this.smtpId = smtpId;
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
    public String toString() {
        return "HeaderDetails{" +
                "id=" + id +
                ", smsId=" + smsId +
                ", smtpId=" + smtpId +
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
