package controller.reemoApi;

import com.google.gson.Gson;
import controller.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
        //user.name = "Pete Obringer";
        //user.phoneNumber = id;

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

    public int getBasicUserInfo(String phoneNumber, User user) {

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
            PreparedStatement preparedStatement = connection.prepareStatement("{call dbo.Wearer_GetProfileData2(?)}");
            preparedStatement.setString(1, phoneNumber);
            Boolean isResult = preparedStatement.execute();

            ResultSet rs;
            ResultSetMetaData md;
            while(isResult){
                rs = preparedStatement.getResultSet();
                rs.next();
                md = rs.getMetaData();
                for (int column = 1; column <= md.getColumnCount(); column++) {
                    String columnName = md.getColumnLabel(column);
                    if (columnName.equals("full_name")){
                        user.name = getResultOrNull(rs,"full_name");
                    }
                    if (columnName.equals("address_line_1_text")){

                        user.lastAddress = getResultOrNull(rs,"address_line_1_text");
                    }
                    if (columnName.equals("telephone_number_text")){
                        user.phoneNumber = getResultOrNull(rs,"telephone_number_text");
                    }
                }
                rs.close();
                isResult = preparedStatement.getMoreResults();
            }
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

    private String getResultOrNull(ResultSet rs, String columnValue){
        try {
            return rs.getString(columnValue);
        }catch (Exception e){
            System.out.println("no value for column " + columnValue);
        }
        return null;
    }


    // returns true if the first date is later than the second, or false otherwise
    public boolean isLater(String dateString1, String dateString2){
        //DateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss-mmmmmmm");
        //2018-01-29 18:18:23.0000000 +00:00

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnnnnnn ZZZZZ", Locale.ENGLISH);
        LocalDate date1 = LocalDate.parse(dateString1, formatter);
        System.out.println(date1.toString());
        LocalDate date2 = LocalDate.parse(dateString2, formatter);
        System.out.println(date2.toString());

        return date1.isAfter(date2);

    }

}
