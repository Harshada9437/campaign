package com.campaign.requestHandlers;

import com.campaign.dao.HeaderDAO;
import com.campaign.dao.SmsDAO;
import com.campaign.dao.SmtpDAO;
import com.campaign.rest.request.campaign.HeaderDetails;
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
}
