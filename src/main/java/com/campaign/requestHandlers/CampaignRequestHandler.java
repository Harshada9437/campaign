package com.campaign.requestHandlers;

import com.campaign.bo.request.CampaignCreateRequestBO;
import com.campaign.dao.CampaignDAO;
import com.campaign.dto.campaign.CampaignDTO;
import com.campaign.rest.response.campaign.CampaignResponse;
import com.campaign.rest.response.campaign.GetCampaignResponse;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
        return campaignDTO;
    }

    public static String random(int size) {

        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            // Generate 20 integers 0..20
            for (int i = 0; i < size; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedToken.toString();
    }

    public List<CampaignResponse> getCampaigns(int campaignAdmin) throws SQLException {
        CampaignDAO campaignDAO = new CampaignDAO();
        List<CampaignResponse> campaignResponses = new ArrayList<CampaignResponse>();
        List<CampaignDTO> campaignDTOS = campaignDAO.getCampaigns(campaignAdmin);

        for (CampaignDTO campaignDTO : campaignDTOS) {
            CampaignResponse campaignResponse = new CampaignResponse(campaignDTO.getId(),
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
            campaignResponses.add(campaignResponse);
        }
        return campaignResponses;
    }

    public GetCampaignResponse getCampaignInfo(String campaignId) throws SQLException {
        CampaignDAO campaignDAO = new CampaignDAO();
        CampaignDTO campaignDTO = campaignDAO.getCampaignInfo(campaignId);
        GetCampaignResponse getCampaignResponse = new GetCampaignResponse(campaignDTO.getId(),
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
        return getCampaignResponse;
    }
}
