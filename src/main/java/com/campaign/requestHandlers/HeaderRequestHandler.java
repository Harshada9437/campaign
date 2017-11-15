package com.campaign.requestHandlers;

import com.campaign.bo.request.HeaderDetailsBO;
import com.campaign.bo.request.HeaderUpdateRequestBO;
import com.campaign.dao.HeaderDAO;
import com.campaign.dao.SmsDAO;
import com.campaign.dao.SmtpDAO;
import com.campaign.dto.campaign.HeaderDTO;
import com.campaign.rest.request.campaign.HeaderDetails;
import com.campaign.rest.response.header.GetHeaderResponse;
import com.campaign.rest.response.header.HeaderResponse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeaderRequestHandler {
    public List<HeaderResponse> getHeaders() throws SQLException {
        HeaderDAO headerDAO = new HeaderDAO();
        List<HeaderResponse> headerResponses = new ArrayList<HeaderResponse>();
        List<HeaderDetails> headers = headerDAO.getHeaders();

        for (HeaderDetails headerDetails: headers){
            HeaderResponse headerResponse = new HeaderResponse(headerDetails.getId(),
                    SmsDAO.getSmsSetting(headerDetails.getSmsId()),
                    SmtpDAO.getSmtpSetting(headerDetails.getSmtpId()),
                    headerDetails.getName(),
                    headerDetails.getInfo(),
                    headerDetails.getLogo(),
                    headerDetails.getIcon(),
                    headerDetails.getBackground(),
                    headerDetails.getForecolor(),
                    headerDetails.getBorder(),
                    headerDetails.getDomain()
                    );
            headerResponses.add(headerResponse);
        }
        return headerResponses;
    }

    public int createHeader(HeaderDetailsBO headerDetailsBO) throws SQLException {
        HeaderDAO headerDAO = new HeaderDAO();
        SmsDAO smsDAO = new SmsDAO();
        SmtpDAO smtpDAO = new SmtpDAO();

        int smsAccId = smsDAO.createSmsSettings(headerDetailsBO.getSmsDetails());
        int emailAccId = smtpDAO.createSmtpSettings(headerDetailsBO.getSmtpDetails());

        int id = headerDAO.createHeader(buildHeaderDTOFromBO(headerDetailsBO,smsAccId,emailAccId));

        return id;
    }

    private HeaderDTO buildHeaderDTOFromBO(HeaderDetailsBO headerDetailsBO, int smsAccId, int emailAccId) {
        HeaderDTO headerDTO = new HeaderDTO();

        headerDTO.setBackground(headerDetailsBO.getBackground());
        headerDTO.setBorder(headerDetailsBO.getBorder());
        headerDTO.setDomain(headerDetailsBO.getDomain());
        headerDTO.setForecolor(headerDetailsBO.getForecolor());
        headerDTO.setIcon(headerDetailsBO.getIcon());
        headerDTO.setLogo(headerDetailsBO.getLogo());
        headerDTO.setName(headerDetailsBO.getName());
        headerDTO.setInfo(headerDetailsBO.getInfo());
        headerDTO.setEmailAccId(emailAccId);
        headerDTO.setSmsAccId(smsAccId);

        return headerDTO;
    }

    private HeaderDTO buildHeaderUpdateDTOFromBO(HeaderUpdateRequestBO headerDetailsBO/*, int smsAccId, int emailAccId*/) {
        HeaderDTO headerDTO = new HeaderDTO();

        headerDTO.setBackground(headerDetailsBO.getBackground());
        headerDTO.setBorder(headerDetailsBO.getBorder());
        headerDTO.setDomain(headerDetailsBO.getDomain());
        headerDTO.setForecolor(headerDetailsBO.getForecolor());
        headerDTO.setIcon(headerDetailsBO.getIcon());
        headerDTO.setLogo(headerDetailsBO.getLogo());
        headerDTO.setName(headerDetailsBO.getName());
        headerDTO.setInfo(headerDetailsBO.getInfo());
       headerDTO.setId(headerDetailsBO.getId());/*
        headerDTO.setSmsAccId(smsAccId);
*/
        return headerDTO;
    }

    public Boolean updateHeader(HeaderUpdateRequestBO requestBO) throws SQLException {
        HeaderDAO headerDAO = new HeaderDAO();
        SmsDAO smsDAO = new SmsDAO();
        SmtpDAO smtpDAO = new SmtpDAO();
        Boolean isProcessed=headerDAO.updateHeader(buildHeaderUpdateDTOFromBO(requestBO));
        smsDAO.updateSms(requestBO.getSmsDetails());
        smtpDAO.updateSmtp(requestBO.getSmtpDetails());
        return isProcessed;
    }

    public GetHeaderResponse getHeaderInfo(int id) throws SQLException {
        HeaderDAO headerDAO = new HeaderDAO();
        HeaderDetails headerDetails = headerDAO.getHeaderInfo(id);

        GetHeaderResponse headerResponse = new GetHeaderResponse(headerDetails.getId(),
                    SmsDAO.getSmsSetting(headerDetails.getSmsId()),
                    SmtpDAO.getSmtpSetting(headerDetails.getSmsId()),
                    headerDetails.getName(),
                    headerDetails.getInfo(),
                    headerDetails.getLogo(),
                    headerDetails.getIcon(),
                    headerDetails.getBackground(),
                    headerDetails.getForecolor(),
                    headerDetails.getBorder(),
                    headerDetails.getDomain()
            );

        return headerResponse;
    }
}
