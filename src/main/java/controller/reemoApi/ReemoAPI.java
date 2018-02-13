package controller.reemoApi;

import com.google.gson.Gson;
import controller.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReemoAPI {

    Logger logger = Logger.getLogger(this.getClass());
    Connection connection;
    static final String username = "reemoprodweb";
    static final String password = "BX$974g!lM7*hVW0";
    //static final String prodURL = "jdbc:sqlserver://prod-uc-db-01.database.windows.net:1433;database=reemoprod01;user="+username+";password="+password+";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    String devURL = "jdbc:sqlserver://sql-server-reemo-dev.database.windows.net:1433;database=reemodb;user=reemoadmin@sql-server-reemo-dev;password=Playtabas3!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";


    public ReemoAPI(){

    }

    public void connect(){
        try {
            connection = DriverManager.getConnection(devURL);
            String schema = connection.getSchema();
            logger.info("Schema: " + schema);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("error connecting to database ", e);
        }
    }
    public void disconnect(){
        try{
            connection.close();
        }catch (Exception e){
            logger.error("exception while attempting to close database connection ", e);
        }
    }


    public User getUser(String id){
        connect();

        User user = new User();
        /*user.name = "Jonathon Jones Claypool";
        user.lastAddress = "4241 44th ave s, Minneapolis, MN, 55406";
        user.battery = "50";
        user.latitude = 44.9716;
        user.longitude = -93.2863;
        user.updateTime = "03/13/1994 4:25 pm";
        user.battery = "54";
        user.phoneNumber = "6512805141";*/

        // pass in the user by reference to be modified
        getBasicUserInfo(id,user);
        getLocation(id, user);
        getBattery(id, user);

        disconnect();

        user.ahjs =  "{\"ahjs\":[{\"ahjType\":\"ems\",\"ahjId\":\"1215.00000000000\",\"agency\":\"San Jose Police and Fire\",\"phone\":\"408-277-8991\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-277-8911\"},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},{\"ahjType\":\"fire\",\"ahjId\":\"1185.00000000000\",\"agency\":\"San Jose Police and Fire\",\"phone\":\"408-277-8991\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-277-8911\"},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},{\"ahjType\":\"police\",\"ahjId\":\"1179.00000000000\",\"agency\":\"Santa Clara Communications\",\"phone\":\"408-289-9530\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-977-3272\"},\"mailingAddress\":{\"formattedAddress\":\"2700 Carol Drive San Jose CA 95125\",\"mainAddressLine\":\"2700 Carol Drive\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95125\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}}],\"psap\":{\"fccId\":\"935\",\"type\":\"Enhanced\",\"agency\":\"San Jose Police Department\",\"phone\":\"408-277-8911\",\"county\":{\"name\":\"Santa Clara\",\"fips\":\"06085\"},\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"comments\":\"None\",\"exceptions\":\"None\"},\"contactPerson\":{\"title\":\"Communications Division Manager\",\"prefix\":\"Ms.\",\"firstName\":\"Joey\",\"lastName\":\"McDonald\",\"phone\":\"408-277-4212\",\"fax\":\"408-294-6911\",\"email\":\"N/A\",\"additionalDetails\":\"None\"},\"siteDetails\":{\"phone\":\"408-277-8911\",\"fax\":\"408-294-6911\",\"address\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}}}";

        return user;
    }

    public  int getLocation(String phoneNumber, User user){
        try {
            PreparedStatement pstmt = connection.prepareStatement("{call dbo.Wearer_GetLatestLocationInfo(?)}");
            pstmt.setString(1, phoneNumber);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                double latitude = Double.parseDouble(rs.getString("latitude"));
                double longitude = Double.parseDouble(rs.getString("longitude"));
                user.latitude = latitude;
                user.longitude = longitude;
                user.updateTime = rs.getString("last_location_timestamp_utc");
            }
            rs.close();
            pstmt.close();
        }

        catch (Exception e) {
            //e.printStackTrace();
            System.out.println("no user found for phone number: " + phoneNumber);
            return 1;

        }
        return 0;
    }
    public  int getBattery(String phoneNumber, User user){
        try {
            PreparedStatement pstmt = connection.prepareStatement("{call dbo.Wearer_GetLatestBatteryInfo(?)}");
            pstmt.setString(1, phoneNumber);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                user.battery = rs.getString("battery_level");
                //rs.getString("last_location_timestamp_utc");

            }
            rs.close();
            pstmt.close();
        }

        catch (Exception e) {
            //e.printStackTrace();
            System.out.println("No user found for phone number: " + phoneNumber);
            return 1;
        }
        return 0;
    }

    public int getBasicUserInfo(String phoneNumber, User user){
        /**
         * some clooj that needs to be removed just to test dev
         */

//        try {
//            String devURL = "jdbc:sqlserver://sql-server-reemo-dev.database.windows.net:1433;database=reemodb;user=reemoadmin@sql-server-reemo-dev;password=Playtabas3!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
//
//            //this.connection.close();
//            this.connection = DriverManager.getConnection(devURL);
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }


        try {
            PreparedStatement preparedStatement = connection.prepareStatement("{call dbo.Wearer_GetProfileData(?)}");
            preparedStatement.setString(1,phoneNumber);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            // RS 1
            user.name = rs.getString("full_name");
            // RS 2

            // RS 3
            user.lastAddress = rs.getString("address_line_1_text");
            // RS 4
            // RS 5
            user.phoneNumber = rs.getString("telephone_number_text");


            /*while (rs.next()){
                // go through the result sets and grab the users data.
                rs.
            }*/
            rs.close();
            preparedStatement.close();


        }catch (Exception e){
            logger.info("no User found for phone number: " + phoneNumber);
            e.printStackTrace();
            user.name = "";
            user.lastAddress = "";
            user.phoneNumber = "";
            return 1;
        }



        return 0;
    }


}
