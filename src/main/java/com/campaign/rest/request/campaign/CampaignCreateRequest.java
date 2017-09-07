package com.campaign.rest.request.campaign;

import java.util.List;

public class CampaignCreateRequest {
   private int  createdBy;
   private int noOfPerson;
   private int isallowOnFull;
   private int headerId;
   private String name;
   private String desc;
   private String confirmSms;
   private String confirmEmail;
   private String notifyEmail;
   private String campaignOverText;
   private String slotFullText;
   private List<SlotDetails> slots;
   private List<String> dates;

   public int getHeaderId() {
      return headerId;
   }

   public void setHeaderId(int headerId) {
      this.headerId = headerId;
   }

   public List<String> getDates() {
      return dates;
   }

   public void setDates(List<String> dates) {
      this.dates = dates;
   }

   public int getCreatedBy() {
      return createdBy;
   }

   public void setCreatedBy(int createdBy) {
      this.createdBy = createdBy;
   }

   public int getNoOfPerson() {
      return noOfPerson;
   }

   public void setNoOfPerson(int noOfPerson) {
      this.noOfPerson = noOfPerson;
   }

   public int getIsallowOnFull() {
      return isallowOnFull;
   }

   public void setIsallowOnFull(int isallowOnFull) {
      this.isallowOnFull = isallowOnFull;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CampaignCreateRequest that = (CampaignCreateRequest) o;

      if (createdBy != that.createdBy) return false;
      if (noOfPerson != that.noOfPerson) return false;
      if (isallowOnFull != that.isallowOnFull) return false;
      if (headerId != that.headerId) return false;
      if (name != null ? !name.equals(that.name) : that.name != null) return false;
      if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
      if (confirmSms != null ? !confirmSms.equals(that.confirmSms) : that.confirmSms != null) return false;
      if (confirmEmail != null ? !confirmEmail.equals(that.confirmEmail) : that.confirmEmail != null) return false;
      if (notifyEmail != null ? !notifyEmail.equals(that.notifyEmail) : that.notifyEmail != null) return false;
      if (campaignOverText != null ? !campaignOverText.equals(that.campaignOverText) : that.campaignOverText != null)
         return false;
      if (slotFullText != null ? !slotFullText.equals(that.slotFullText) : that.slotFullText != null) return false;
      if (slots != null ? !slots.equals(that.slots) : that.slots != null) return false;
      return dates != null ? dates.equals(that.dates) : that.dates == null;
   }

   @Override
   public int hashCode() {
      int result = createdBy;
      result = 31 * result + noOfPerson;
      result = 31 * result + isallowOnFull;
      result = 31 * result + headerId;
      result = 31 * result + (name != null ? name.hashCode() : 0);
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
      return "CampaignCreateRequest{" +
              "createdBy=" + createdBy +
              ", noOfPerson=" + noOfPerson +
              ", isallowOnFull=" + isallowOnFull +
              ", headerId=" + headerId +
              ", name='" + name + '\'' +
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
