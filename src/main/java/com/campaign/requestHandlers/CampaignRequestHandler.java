package com.campaign.requestHandlers;

import com.campaign.Exception.CampaignNotFoundException;
import com.campaign.bo.request.CampaignCreateRequestBO;
import com.campaign.bo.request.CampaignUpdateRequestBO;
import com.campaign.dao.CampaignDAO;
import com.campaign.dao.UtilClasses.ConnectionHandler;
import com.campaign.dto.campaign.CampaignDTO;
import com.campaign.rest.request.campaign.SlotDetails;
import com.campaign.rest.response.campaign.CampaignInfoResponse;
import com.campaign.rest.response.campaign.CampaignResponse;
import com.campaign.rest.response.campaign.GetCampaignResponse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CampaignRequestHandler {
    public String createCampaign(CampaignCreateRequestBO campaignCreateRequestBO) throws SQLException {
        CampaignDAO campaignDAO = new CampaignDAO();
        String id = campaignDAO.createCampaign(buildCampagnDTOFromBO(campaignCreateRequestBO));
        return id;
    }

    private CampaignDTO buildCampagnDTOFromBO(CampaignCreateRequestBO campaignCreateRequestBO) {
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setName(campaignCreateRequestBO.getName());
        campaignDTO.setEmailSubject(campaignCreateRequestBO.getEmailSubject());
        campaignDTO.setIsPublished(campaignCreateRequestBO.getIsPublished());
        campaignDTO.setDesc(campaignCreateRequestBO.getDesc());
        campaignDTO.setDates(campaignCreateRequestBO.getDates());
        campaignDTO.setNoOfPerson(campaignCreateRequestBO.getNoOfPerson());
        campaignDTO.setSlots(campaignCreateRequestBO.getSlots());
        campaignDTO.setCampaignOverText(campaignCreateRequestBO.getCampaignOverText());
        campaignDTO.setConfirmEmail(campaignCreateRequestBO.getConfirmEmail());
        campaignDTO.setConfirmSms(campaignCreateRequestBO.getConfirmSms());
        campaignDTO.setNotifyEmail(campaignCreateRequestBO.getNotifyEmail());
        campaignDTO.setSlotFullText(campaignCreateRequestBO.getSlotFullText());
        campaignDTO.setIsAllowOnFull(campaignCreateRequestBO.getIsallowOnFull());
        campaignDTO.setCreatedBy(campaignCreateRequestBO.getCreatedBy());
        campaignDTO.setHeaderId(campaignCreateRequestBO.getHeaderId());
        campaignDTO.setCampaignLocation(campaignCreateRequestBO.getCampaignLocation());
        campaignDTO.setIsPromoCampaign(campaignCreateRequestBO.getIsPromoCampaign());
        return campaignDTO;
    }

    public List<CampaignResponse> getCampaigns(int campaignAdmin) throws SQLException {
        CampaignDAO campaignDAO = new CampaignDAO();
        List<CampaignResponse> campaignResponses = new ArrayList<CampaignResponse>();
        List<CampaignDTO> campaignDTOS = campaignDAO.getCampaigns(campaignAdmin);

        for (CampaignDTO campaignDTO : campaignDTOS) {
            CampaignResponse campaignResponse = new CampaignResponse(campaignDTO.getCampaignLocation(),
                    campaignDTO.getStatus(),
                    campaignDTO.getIsPromoCampaign(),
                    campaignDTO.getIsPublished(),
                    campaignDTO.getEmailSubject(),
                    campaignDTO.getLinkHashId(),
                    campaignDTO.getId(),
                    campaignDTO.getCreatedBy(),
                    campaignDTO.getNoOfPerson(),
                    campaignDTO.getIsAllowOnFull(),
                    campaignDTO.getHeader(),
                    campaignDTO.getSms(),
                    campaignDTO.getSmtp(),
                    campaignDTO.getName(),
                    campaignDTO.getDesc(),
                    campaignDTO.getConfirmSms(),
                    campaignDTO.getConfirmEmail(),
                    campaignDTO.getNotifyEmail(),
                    campaignDTO.getCampaignOverText(),
                    campaignDTO.getSlotFullText()
            );
            campaignResponse.setDates(campaignDAO.getDates(campaignDTO.getId()));
            campaignResponse.setSlots(campaignDAO.getTimeSlots(campaignDTO.getId()));
            campaignResponses.add(campaignResponse);
        }
        return campaignResponses;
    }

    public CampaignInfoResponse getCampaignInfo(String campaignId) throws SQLException, CampaignNotFoundException {
        CampaignDAO campaignDAO = new CampaignDAO();
        CampaignDTO campaignDTO = campaignDAO.getCampaignInfo(campaignId);
        CampaignInfoResponse getCampaignResponse = new CampaignInfoResponse(campaignDTO.getCampaignLocation(),
                campaignDTO.getIsPromoCampaign(),
                campaignDTO.getStatus(),
                campaignDTO.getLinkHashId(),
                campaignDTO.getEmailSubject(),
                campaignDTO.getId(),
                campaignDTO.getIsPublished(),
                campaignDTO.getCreatedBy(),
                campaignDTO.getNoOfPerson(),
                campaignDTO.getIsAllowOnFull(),
                campaignDTO.getHeader(),
                campaignDTO.getName(),
                campaignDTO.getDesc(),
                campaignDTO.getConfirmSms(),
                campaignDTO.getConfirmEmail(),
                campaignDTO.getNotifyEmail(),
                campaignDTO.getCampaignOverText(),
                campaignDTO.getSlotFullText()
        );
        getCampaignResponse.setDates(campaignDAO.getDates(campaignDTO.getId()));
        getCampaignResponse.setSlots(campaignDAO.getTimeSlots(campaignDTO.getId()));
        return getCampaignResponse;
    }

    public Boolean updateCampaign(CampaignUpdateRequestBO campaignUpdateRequestBO) throws SQLException {
        CampaignDAO campaignDAO = new CampaignDAO();
        Boolean isProcessed = campaignDAO.updateCampaign(buildUpdateDtoFromBO(campaignUpdateRequestBO));
       /* List<String> newDates = new ArrayList<String>();
        List<String> dates = campaignDAO.getDates(campaignUpdateRequestBO.getId());
        List<String> removedDates = new ArrayList<String>();
        List<SlotDetails> newSlots = new ArrayList<SlotDetails>();
        List<SlotDetails> slots = campaignDAO.getTimeSlots(campaignUpdateRequestBO.getId());
        List<SlotDetails> removedSlots = new ArrayList<SlotDetails>();
        int newDateSize=campaignUpdateRequestBO.getDates().size();
        int newSlotsSize=campaignUpdateRequestBO.getSlots().size();

        for (int i=0;  i<Math.max(Math.max(newDateSize,dates.size()),Math.max(newSlotsSize,slots.size())); i++){
            if (dates.size() > i && !campaignUpdateRequestBO.getDates().contains(dates.get(i))){
                removedDates.add(dates.get(i));
            }
            if(newDateSize>i && !dates.contains(campaignUpdateRequestBO.getDates().get(i))){
                newDates.add(campaignUpdateRequestBO.getDates().get(i));
                dates.add(campaignUpdateRequestBO.getDates().get(i));
            }
            if(newSlotsSize>i && !slots.contains(campaignUpdateRequestBO.getSlots().get(i))){
                newSlots.add(campaignUpdateRequestBO.getSlots().get(i));
                slots.add(campaignUpdateRequestBO.getSlots().get(i));
            }
            if(slots.size()>i && !campaignUpdateRequestBO.getSlots().contains(slots.get(i))){
                removedSlots.add(slots.get(i));
            }
        }

        if(removedDates.size()>0 && removedSlots.size()>0){
            for(int i=0;i<Math.max(removedDates.size(),removedSlots.size());i++){
                if(removedDates.size()>i){
                    dates.remove(removedDates.get(i));
                    campaignDAO.deleteDates(removedDates.get(i), campaignUpdateRequestBO.getId());
                }
                if(removedSlots.size()>i){
                    slots.remove(removedSlots.get(i));
                    campaignDAO.deleteSlots(removedSlots.get(i).getTime(), campaignUpdateRequestBO.getId());
                }
            }
        }else{
            if (removedDates.size() > 0) {
                for (String date : removedDates) {
                    dates.remove(date);
                    campaignDAO.deleteDates(date, campaignUpdateRequestBO.getId());
                }
            }else{
                for (SlotDetails slot : removedSlots) {
                    slots.remove(slot);
                    campaignDAO.deleteSlots(slot.getTime(), campaignUpdateRequestBO.getId());
                }
            }
        }

        Connection connection = new ConnectionHandler().getConnection();
        try {
            if (newSlots.size() > 0) {

                for (SlotDetails slot : newSlots) {
                    for (String date1 : dates) {
                        campaignDAO.insertSlots(date1, slot, campaignUpdateRequestBO.getId(), connection);
                    }
                }
        }
        if (newDates.size() > 0 && newSlots.size()==0) {
            for (String date1 : newDates) {
                for (SlotDetails slot : slots) {
                    campaignDAO.insertSlots(date1, slot, campaignUpdateRequestBO.getId(),connection);
                }
            }
        }
        } catch(SQLException e){
            e.printStackTrace();
            throw e;
        } finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
*/

        return isProcessed;
    }

    private CampaignDTO buildUpdateDtoFromBO(CampaignUpdateRequestBO campaignUpdateRequestBO) {
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setId(campaignUpdateRequestBO.getId());
        campaignDTO.setIsPromoCampaign(campaignUpdateRequestBO.getIsPromoCampaign());
        campaignDTO.setCampaignLocation(campaignUpdateRequestBO.getCampaignLocation());
        campaignDTO.setHeaderId(campaignUpdateRequestBO.getHeaderId());
        campaignDTO.setIsPublished(campaignUpdateRequestBO.getIsPublished());
        campaignDTO.setName(campaignUpdateRequestBO.getName());
        campaignDTO.setDesc(campaignUpdateRequestBO.getDesc());
        campaignDTO.setNoOfPerson(campaignUpdateRequestBO.getNoOfPerson());
        campaignDTO.setCampaignOverText(campaignUpdateRequestBO.getCampaignOverText());
        campaignDTO.setConfirmEmail(campaignUpdateRequestBO.getConfirmEmail());
        campaignDTO.setConfirmSms(campaignUpdateRequestBO.getConfirmSms());
        campaignDTO.setNotifyEmail(campaignUpdateRequestBO.getNotifyEmail());
        campaignDTO.setSlotFullText(campaignUpdateRequestBO.getSlotFullText());
        campaignDTO.setIsAllowOnFull(campaignUpdateRequestBO.getIsallowOnFull());
        campaignDTO.setEmailSubject(campaignUpdateRequestBO.getEmailSubject());
        campaignDTO.setStatus(campaignUpdateRequestBO.getStatus());
        return campaignDTO;
    }

    public GetCampaignResponse getCampaign(int id, String campaignId) throws SQLException, CampaignNotFoundException {
        CampaignDAO campaignDAO = new CampaignDAO();
        CampaignDTO campaignDTO;
        if(id!=0) {
             campaignDTO = campaignDAO.getCampaignInfoById(id);
        }else{
            campaignDTO= campaignDAO.getCampaignInfo(campaignId);
        }
        GetCampaignResponse getCampaignResponse = new GetCampaignResponse(campaignDTO.getCampaignLocation(),
                campaignDTO.getIsPromoCampaign(),
                campaignDTO.getStatus(),
                campaignDTO.getLinkHashId(),
                campaignDTO.getEmailSubject(),
                campaignDTO.getId(),
                campaignDTO.getIsPublished(),
                campaignDTO.getCreatedBy(),
                campaignDTO.getNoOfPerson(),
                campaignDTO.getIsAllowOnFull(),
                campaignDTO.getHeader(),
                campaignDTO.getSms(),
                campaignDTO.getSmtp(),
                campaignDTO.getName(),
                campaignDTO.getDesc(),
                campaignDTO.getConfirmSms(),
                campaignDTO.getConfirmEmail(),
                campaignDTO.getNotifyEmail(),
                campaignDTO.getCampaignOverText(),
                campaignDTO.getSlotFullText()
        );
        getCampaignResponse.setDates(campaignDAO.getDates(campaignDTO.getId()));
        getCampaignResponse.setSlots(campaignDAO.getTimeSlots(campaignDTO.getId()));
        return getCampaignResponse;
    }
}
