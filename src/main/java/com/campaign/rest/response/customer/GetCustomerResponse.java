package com.campaign.rest.response.customer;

import com.campaign.rest.response.util.GenericResponse;

public class GetCustomerResponse implements GenericResponse {
        private int id;
        private String fullName;
        private String message;
        private String messageType;
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

        public String getMessage() {
            return message;
        }

        @Override
        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessageType() {
            return messageType;
        }

        @Override
        public void setMessageType(String messageType) {
            this.messageType = messageType;
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
                    ", fullName='" + fullName + '\'' +
                    ", message='" + message + '\'' +
                    ", messageType='" + messageType + '\'' +
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
                    '}';
        }
}
