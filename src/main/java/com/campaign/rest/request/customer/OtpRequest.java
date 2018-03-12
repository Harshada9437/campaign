package com.campaign.rest.request.customer;

/**
 * Created by Shubham on 6/29/2017.
 */
public class OtpRequest {
    private String fullName;
    private String email;
    private String ageGroup;
    private String date;
    private String timeSlot;
    private String mobile;
    private String locality;
    private String gender;
    private String remark;
    private String dob;
    private String resource;
    private int noOfPerson;
    private int rating;
    private int isSpecialCampaign;
    private String campaignId;

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIsSpecialCampaign() {
        return isSpecialCampaign;
    }

    public void setIsSpecialCampaign(int isSpecialCampaign) {
        this.isSpecialCampaign = isSpecialCampaign;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getNoOfPerson() {
        return noOfPerson;
    }

    public void setNoOfPerson(int noOfPerson) {
        this.noOfPerson = noOfPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OtpRequest that = (OtpRequest) o;

        if (noOfPerson != that.noOfPerson) return false;
        if (rating != that.rating) return false;
        if (isSpecialCampaign != that.isSpecialCampaign) return false;
        if (ageGroup != null ? !ageGroup.equals(that.ageGroup) : that.ageGroup != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (timeSlot != null ? !timeSlot.equals(that.timeSlot) : that.timeSlot != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (locality != null ? !locality.equals(that.locality) : that.locality != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (dob != null ? !dob.equals(that.dob) : that.dob != null) return false;
        if (resource != null ? !resource.equals(that.resource) : that.resource != null) return false;
        return campaignId != null ? campaignId.equals(that.campaignId) : that.campaignId == null;
    }

    @Override
    public int hashCode() {
        int result = fullName != null ? fullName.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (ageGroup != null ? ageGroup.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (timeSlot != null ? timeSlot.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (resource != null ? resource.hashCode() : 0);
        result = 31 * result + noOfPerson;
        result = 31 * result + rating;
        result = 31 * result + isSpecialCampaign;
        result = 31 * result + (campaignId != null ? campaignId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OtpRequest{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", ageGroup='" + ageGroup + '\'' +
                ", date='" + date + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", mobile='" + mobile + '\'' +
                ", locality='" + locality + '\'' +
                ", gender='" + gender + '\'' +
                ", remark='" + remark + '\'' +
                ", dob='" + dob + '\'' +
                ", resource='" + resource + '\'' +
                ", noOfPerson=" + noOfPerson +
                ", rating=" + rating +
                ", isSpecialCampaign=" + isSpecialCampaign +
                ", campaignId='" + campaignId + '\'' +
                '}';
    }
}
