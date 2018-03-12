package com.campaign.rest.response.customer;

public class CustomerResponse {
    private int id;
    private int campaignId;
    private int rating;
    private String fullName;
    private String ageGroup;
    private String campaignName;
    private String email;
    private String date;
    private String timeSlot;
    private String mobile;
    private String locality;
    private String gender;
    private String remark;
    private String dob;
    private String resource;
    private int noOfPerson;
    private int isConfirmed;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public String toString() {
        return "CustomerResponse{" +
                "id=" + id +
                ", campaignId=" + campaignId +
                ", rating=" + rating +
                ", fullName='" + fullName + '\'' +
                ", ageGroup='" + ageGroup + '\'' +
                ", campaignName='" + campaignName + '\'' +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", mobile='" + mobile + '\'' +
                ", locality='" + locality + '\'' +
                ", gender='" + gender + '\'' +
                ", remark='" + remark + '\'' +
                ", dob='" + dob + '\'' +
                ", resource='" + resource + '\'' +
                ", noOfPerson=" + noOfPerson +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}
