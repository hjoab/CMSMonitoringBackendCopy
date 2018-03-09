package reemo;

import com.google.gson.Gson;
import controller.User;
import controller.reemoApi.ReemoAPI;
import org.junit.Test;

public class APITests {
    @Test
    public void testConnection(){
        ReemoAPI api = new ReemoAPI();
        api.connect();
        /*api.getLocation("9522287193");
        api.getBattery("9522287193");*/
        api.disconnect();
    }

    @Test
    public void testGetUserInfo(){
        String phoneNumber = "9522287193";

        ReemoAPI api = new ReemoAPI();
        User user = api.getUser(phoneNumber);
        System.out.println(new Gson().toJson(user).toString());
    }
    @Test
    public void testBadUser(){
        String phoneNumber = "Jorge";

        ReemoAPI api = new ReemoAPI();
        User user = api.getUser(phoneNumber);
        System.out.println(new Gson().toJson(user).toString());
    }

    @Test
    public void testBasicUserInfo(){
        String phoneNUmber = "9522287193";
        User user = new User();
        ReemoAPI api = new ReemoAPI();
        api.connect();
        api.getBasicUserInfo(phoneNUmber, user);
        System.out.println(new Gson().toJson(user).toString());
    }

    @Test
    public void testDateComparitor(){
        ReemoAPI api = new ReemoAPI();
        //User user = api.getUser("9522287193");
        User user = new User();

        user.updateTime = "2018-03-09T14:10:20Z";
        String fakeEarlyDate = "2018-02-09T14:10:20Z";
        assert api.isLater(user.updateTime, fakeEarlyDate);
    }
    @Test
    public void testIsPersEvent(){
        ReemoAPI api = new ReemoAPI();
        // this test will only be true if we have a pers event going....
        Boolean isPers = api.isPersEvent("9522287193");
        System.out.println("is pers event: " + isPers);
        //assert
    }

}
