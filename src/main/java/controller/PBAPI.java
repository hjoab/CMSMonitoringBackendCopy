package controller;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.apache.log4j.Logger;

import java.util.Base64;

public class PBAPI {
    public PBAPI(){}

    Logger logger = Logger.getLogger(PBAPI.class);

    private static final String KEY = "hqquQTn9AA4GWOAKyqCpppnFQ0Z3nuHM";
    private static final String SECRET = "W7zp2yH0tTi6LGAR";


    private String encode(String normalString){
        byte[] encodedBytes = Base64.getEncoder().encode(normalString.getBytes());
        //System.out.println("encodedBytes " + new String(encodedBytes));
        return new String(encodedBytes);
        //byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        //System.out.println("decodedBytes " + new String(decodedBytes));
    }
    private String authenticate(){
        String baseEncodingFormat = KEY + ":" + SECRET;
        String encodedFormat = encode(baseEncodingFormat);

        try {
            HttpResponse<JsonNode> response = Unirest.post("https://api.pitneybowes.com/oauth/token")
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .header("Authorization","Basic " + encodedFormat)
                    .body("grant_type=client_credentials")
                    .asJson();
            //System.out.println(response.getCode());
            //System.out.println(response.getBody());
            String accessToken = "access_token";
            String token = response.getBody().getObject().getString(accessToken);

            logger.info("successfully authenticated with access token: " + token);

            return token;

        }catch (Exception e){
            logger.error("authentication error: \n" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public String getLocationIntel(String latitude, String longitude){

        String token = authenticate();
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://api.pitneybowes.com/location-intelligence/geo911/v1/ahj-psap/bylocation?" +
                    "latitude="+latitude+
                    "&" +
                    "longitude="+longitude+"\n")
                    .header("Authorization","Bearer " + token)
                    .asJson();
            //System.out.println(response.getCode());
            //System.out.println(response.getBody());
            logger.info("ahj info for lat: " + latitude + " long: " + longitude + " :\n" + response.getBody().toString());
            String ahj = response.getBody().getObject().getJSONObject("ahjs").toString();
            return ahj;

        }catch (Exception e){
            logger.error("Error getting psap info for lat: " + latitude + " long: " + longitude + "\n" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public String getAddressByLocation(String latitude, String longitude){
        String token = authenticate();
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://api.pitneybowes.com/location-intelligence/geoenrich/v1/address/bylocation?" +
                    "latitude="+latitude +
                    "&longitude=" +longitude+
                    "&searchRadius=2640&searchRadiusUnit=feet" ).header("Authorization", "Bearer " + token).asJson();
            // https://api.pitneybowes.com/location-intelligence/geoenrich/v1/address/bylocation?latitude=35.0118&longitude=-81.9571&searchRadius=2640&searchRadiusUnit=feet
            String formattedAddress = response.getBody().getObject().getJSONArray("location").getJSONObject(0).getJSONObject("address").getString("formattedAddress");


            return formattedAddress;

        }catch (Exception e){
            logger.error("Error getting location info for lat: " + latitude + " long: " + longitude + "\n" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
