package com.campaign.dto.customer;

/**
 * Created by Shubham on 6/29/2017.
 */
public class OtpDTO {
    private String emailId;
    private String mobileNo;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OtpDTO otpDTO = (OtpDTO) o;

        if (!emailId.equals(otpDTO.emailId)) return false;
        return mobileNo.equals(otpDTO.mobileNo);
    }

    @Override
    public int hashCode() {
        int result = emailId.hashCode();
        result = 31 * result + mobileNo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OtpDTO{" +
                "emailId='" + emailId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }
}

