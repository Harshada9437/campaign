package com.campaign.util;

/**
 * Created by System-2 on 1/23/2017.
 */

import com.campaign.config.ConfigProperties;
import com.campaign.dao.SmsDAO;
import com.campaign.rest.request.campaign.SmsDetails;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;

public class SendSms {

    private final String campaign = ConfigProperties.campaign;
    //define route
    private final String route = "4";

    //Prepare Url
    private URLConnection myURLConnection = null;
    private URL myURL = null;
    private BufferedReader reader = null;

    //Send SMS API
    private String mainUrl = "https://control.msg91.com/api/sendhttp.php?";

    //Prepare parameter string
    private final StringBuilder sbPostData = new StringBuilder(mainUrl);

    public Boolean sendSMS(String mobiles, String message, SmsDetails smsDetails) {
        if (mobiles == null || mobiles.equals("")) {
            return false;
        }
        Boolean isProcessed = Boolean.FALSE;

        //encoding message
        String encoded_message = URLEncoder.encode(message);
        encoded_message = URLEncoder.encode(encoded_message);

        sbPostData.append("authkey=" + smsDetails.getApikey());
        sbPostData.append("&mobiles=" + mobiles);
        sbPostData.append("&message=" + encoded_message);
        sbPostData.append("&route=" + route);
        sbPostData.append("&sender=" + smsDetails.getShortCode());
        sbPostData.append("&campaign=" + campaign);
        try {
            //final string
            mainUrl = sbPostData.toString();
            //prepare connection
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();

            myURLConnection.connect();

            reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));

            String response;
            while ((response = reader.readLine()) != null) {
            }
            reader.close();

            isProcessed = Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isProcessed;
    }

    public Boolean NewUserSignup(String mobiles, int otp, SmsDetails smsDetails) {
        String message = "Your verification code is "+otp+". Enter the code to get registered for a complimentary meal.";
        return sendSMS(mobiles, message, smsDetails);
    }

    public Boolean newRegistration(String coupon,int val,String name,String date,String time,String mobile,int persons, SmsDetails smsDetails,String msg,String isPublished) {
        String person,details="";
        if(persons-1>0){
           person="+"+ (persons-1);
        }else
        {
            person="";
        }if(val==0) {
            details = name + person + ", " + DateUtil.format(DateUtil.getTimeStampFromString(date), "dd/MM/yyyy") + ", " + time;
        } String message = msg.replace("%cd%",details);
        message = message.replace("%n%",name);
        message = message.replace("%cpn%",coupon);
        return sendSMS(mobile, isPublished+message, smsDetails);
    }
}