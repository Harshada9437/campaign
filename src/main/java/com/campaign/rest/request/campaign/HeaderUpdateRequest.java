package com.campaign.rest.request.campaign;

public class HeaderUpdateRequest {
    private int id;
    private SmsDetails smsDetails;
    private SmtpDetails smtpDetails;
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

    public SmsDetails getSmsDetails() {
        return smsDetails;
    }

    public void setSmsDetails(SmsDetails smsDetails) {
        this.smsDetails = smsDetails;
    }

    public SmtpDetails getSmtpDetails() {
        return smtpDetails;
    }

    public void setSmtpDetails(SmtpDetails smtpDetails) {
        this.smtpDetails = smtpDetails;
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

        HeaderUpdateRequest that = (HeaderUpdateRequest) o;

        if (id != that.id) return false;
        if (smsDetails != null ? !smsDetails.equals(that.smsDetails) : that.smsDetails != null) return false;
        if (smtpDetails != null ? !smtpDetails.equals(that.smtpDetails) : that.smtpDetails != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (background != null ? !background.equals(that.background) : that.background != null) return false;
        if (forecolor != null ? !forecolor.equals(that.forecolor) : that.forecolor != null) return false;
        if (border != null ? !border.equals(that.border) : that.border != null) return false;
        return domain != null ? domain.equals(that.domain) : that.domain == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (smsDetails != null ? smsDetails.hashCode() : 0);
        result = 31 * result + (smtpDetails != null ? smtpDetails.hashCode() : 0);
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
        return "HeaderUpdateRequest{" +
                "id=" + id +
                ", smsDetails=" + smsDetails +
                ", smtpDetails=" + smtpDetails +
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
