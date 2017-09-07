package com.campaign.rest.response.header;

import com.campaign.rest.request.campaign.SmsDetails;
import com.campaign.rest.request.campaign.SmtpDetails;

public class HeaderResponse {
    private int id;
    private SmsDetails sms;
    private SmtpDetails smtp;
    private String name;
    private String info;
    private String logo;
    private String icon;
    private String background;
    private String forecolor;
    private String border;
    private String domain;

    public HeaderResponse(int id, SmsDetails sms, SmtpDetails smtp, String name, String info, String logo, String icon, String background, String forecolor, String border, String domain) {
        this.id = id;
        this.sms = sms;
        this.smtp = smtp;
        this.name = name;
        this.info = info;
        this.logo = logo;
        this.icon = icon;
        this.background = background;
        this.forecolor = forecolor;
        this.border = border;
        this.domain = domain;
    }

    public int getId() {
        return id;
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

    public String getInfo() {
        return info;
    }

    public String getLogo() {
        return logo;
    }

    public String getIcon() {
        return icon;
    }

    public String getBackground() {
        return background;
    }

    public String getForecolor() {
        return forecolor;
    }

    public String getBorder() {
        return border;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public String toString() {
        return "HeaderResponse{" +
                "id=" + id +
                ", sms=" + sms +
                ", smtp=" + smtp +
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
