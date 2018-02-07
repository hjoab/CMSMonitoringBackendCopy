package controller.reemoApi;

import com.google.gson.Gson;
import controller.User;

public class ReemoAPI {
    public User getUser(String id){
        /*String name = "Jonathon Jones Claypool";
        String contactInformation = "address:\"4241 44th ave s \", phone:\"651-280-5141\"";
        String battery = "40";
        String latitude = "44.9716";
        String longitude = "-93.2863";
        String healthInformation = "battery: \"" + battery + "\", latitude: \"" + latitude + "\", longitude: " + longitude + "\"";*/

        /*return "{" +
                "name: \"" + name+ "\"," +
                "contact: \""+contactInformation+"\"," +
                "health: \"" + healthInformation + "\"" +
                "}";*/

        User user = new User();
        user.name = "Jonathon Jones Claypool";
        user.lastAddress = "4241 44th ave s, Minneapolis, MN, 55406";
        user.battery = "50";
        user.latitude = 44.9716;
        user.longitude = -93.2863;
        user.updateTime = "03/13/1994 4:25 pm";
        user.battery = "54";
        user.phoneNumber = "6512805141";

        user.ahjs =  "{\"ahjs\":[{\"ahjType\":\"ems\",\"ahjId\":\"1215.00000000000\",\"agency\":\"San Jose Police and Fire\",\"phone\":\"408-277-8991\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-277-8911\"},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},{\"ahjType\":\"fire\",\"ahjId\":\"1185.00000000000\",\"agency\":\"San Jose Police and Fire\",\"phone\":\"408-277-8991\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-277-8911\"},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},{\"ahjType\":\"police\",\"ahjId\":\"1179.00000000000\",\"agency\":\"Santa Clara Communications\",\"phone\":\"408-289-9530\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-977-3272\"},\"mailingAddress\":{\"formattedAddress\":\"2700 Carol Drive San Jose CA 95125\",\"mainAddressLine\":\"2700 Carol Drive\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95125\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}}],\"psap\":{\"fccId\":\"935\",\"type\":\"Enhanced\",\"agency\":\"San Jose Police Department\",\"phone\":\"408-277-8911\",\"county\":{\"name\":\"Santa Clara\",\"fips\":\"06085\"},\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"comments\":\"None\",\"exceptions\":\"None\"},\"contactPerson\":{\"title\":\"Communications Division Manager\",\"prefix\":\"Ms.\",\"firstName\":\"Joey\",\"lastName\":\"McDonald\",\"phone\":\"408-277-4212\",\"fax\":\"408-294-6911\",\"email\":\"N/A\",\"additionalDetails\":\"None\"},\"siteDetails\":{\"phone\":\"408-277-8911\",\"fax\":\"408-294-6911\",\"address\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}}}";

        return user;
    }
}
