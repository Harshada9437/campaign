package com.bbqcampaign.spring.controller;

import java.util.Date;
        import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.ResponseBody;

        import com.bbqcampaign.spring.model.Campaign;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class CampaignController {

    private static final Logger logger = LoggerFactory.getLogger(CampaignController.class);

    //Map to store employees, ideally we should use database
    Map<Integer, Campaign> campaignHashMap = new HashMap<Integer, Campaign>();

    @RequestMapping(value = CampaignRestURIConstants.CREATE_CAMPAIGN, method = RequestMethod.POST)
    public @ResponseBody Campaign createEmployee(@RequestBody Campaign campaign) {
        logger.info("Start createCampaign.");
        campaignHashMap.put(campaign.getNoOfPersons(), campaign);
        campaignHashMap.put(campaign.getId(), campaign);
        return campaign;
    }
}