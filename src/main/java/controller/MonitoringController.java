package controller;

import controller.reemoApi.ReemoAPI;
import controller.reemoApi.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Component
public class MonitoringController {
    PBAPI PBAPI = new PBAPI();
    ReemoAPI reemoAPI = new ReemoAPI();
    @CrossOrigin(origins = "*")
    @RequestMapping("/")
    public @ResponseBody String home(){
        return "hello wordl";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/psap", method = RequestMethod.GET)
    public @ResponseBody
    String psapInformation(@RequestParam(value="latitude",required = true) String latitude,
                      @RequestParam(value="longitude",required = true) String longitude){

        //return PBAPI.getLocationIntel(latitude,longitude);
        return "{\"ahjs\":[{\"ahjType\":\"ems\",\"ahjId\":\"1215.00000000000\",\"agency\":\"San Jose Police and Fire\",\"phone\":\"408-277-8991\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-277-8911\"},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},{\"ahjType\":\"fire\",\"ahjId\":\"1185.00000000000\",\"agency\":\"San Jose Police and Fire\",\"phone\":\"408-277-8991\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-277-8911\"},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},{\"ahjType\":\"police\",\"ahjId\":\"1179.00000000000\",\"agency\":\"Santa Clara Communications\",\"phone\":\"408-289-9530\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-977-3272\"},\"mailingAddress\":{\"formattedAddress\":\"2700 Carol Drive San Jose CA 95125\",\"mainAddressLine\":\"2700 Carol Drive\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95125\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}}],\"psap\":{\"fccId\":\"935\",\"type\":\"Enhanced\",\"agency\":\"San Jose Police Department\",\"phone\":\"408-277-8911\",\"county\":{\"name\":\"Santa Clara\",\"fips\":\"06085\"},\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"comments\":\"None\",\"exceptions\":\"None\"},\"contactPerson\":{\"title\":\"Communications Division Manager\",\"prefix\":\"Ms.\",\"firstName\":\"Joey\",\"lastName\":\"McDonald\",\"phone\":\"408-277-4212\",\"fax\":\"408-294-6911\",\"email\":\"N/A\",\"additionalDetails\":\"None\"},\"siteDetails\":{\"phone\":\"408-277-8911\",\"fax\":\"408-294-6911\",\"address\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}}}";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/userInformation", method = RequestMethod.GET)
    public @ResponseBody String getUserInformation(@RequestParam(value="id",required=true)String id) {
        User user = reemoAPI.getUser(id);
        String latitude = user.latitude;
        String longitude = user.longitude;
        String closestAddress = PBAPI.getAddressByLocation(latitude,longitude);
        user.address = closestAddress;

        String ahj = "{\"ahjs\":[{\"ahjType\":\"ems\",\"ahjId\":\"1215.00000000000\",\"agency\":\"San Jose Police and Fire\",\"phone\":\"408-277-8991\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-277-8911\"},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},{\"ahjType\":\"fire\",\"ahjId\":\"1185.00000000000\",\"agency\":\"San Jose Police and Fire\",\"phone\":\"408-277-8991\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-277-8911\"},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},{\"ahjType\":\"police\",\"ahjId\":\"1179.00000000000\",\"agency\":\"Santa Clara Communications\",\"phone\":\"408-289-9530\",\"comments\":\"\",\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"exceptions\":\"None\"},\"contactPerson\":{\"phone\":\"408-977-3272\"},\"mailingAddress\":{\"formattedAddress\":\"2700 Carol Drive San Jose CA 95125\",\"mainAddressLine\":\"2700 Carol Drive\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95125\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}}],\"psap\":{\"fccId\":\"935\",\"type\":\"Enhanced\",\"agency\":\"San Jose Police Department\",\"phone\":\"408-277-8911\",\"county\":{\"name\":\"Santa Clara\",\"fips\":\"06085\"},\"coverage\":{\"area\":\"Portion of Santa Clara(CA) County\",\"comments\":\"None\",\"exceptions\":\"None\"},\"contactPerson\":{\"title\":\"Communications Division Manager\",\"prefix\":\"Ms.\",\"firstName\":\"Joey\",\"lastName\":\"McDonald\",\"phone\":\"408-277-4212\",\"fax\":\"408-294-6911\",\"email\":\"N/A\",\"additionalDetails\":\"None\"},\"siteDetails\":{\"phone\":\"408-277-8911\",\"fax\":\"408-294-6911\",\"address\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}},\"mailingAddress\":{\"formattedAddress\":\"201 West Mission Street San Jose CA 95110\",\"mainAddressLine\":\"201 West Mission Street\",\"addressLastLine\":\"\",\"placeName\":\"\",\"areaName1\":\"CA\",\"areaName2\":\"\",\"areaName3\":\"San Jose\",\"areaName4\":\"\",\"postCode\":\"95110\",\"postCodeExt\":\"\",\"country\":\"USA\",\"addressNumber\":\"\",\"streetName\":\"\",\"unitType\":\"\",\"unitValue\":\"\"}}}";
        //String ahj = PBAPI.getLocationIntel(latitude,longitude);

        String response = "{" +
                "latitude: \"" + latitude +"\"" +
                "longitude: \"" + longitude +"\"" +
                "user: \"" + user.toString()+"\"" +
                "}";
        return "hello";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/authentication")
    public @ResponseBody String authenticate(){
        return "Congrats! You are successfully authenticated!";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/diagnostic" , method = RequestMethod.GET)
    public @ResponseBody String diagnosticInformation(){
        return "allGood";
    }

}
