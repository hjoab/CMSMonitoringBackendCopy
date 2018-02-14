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
        api.getBasicUserInfo(phoneNUmber, user);
        System.out.println(new Gson().toJson(user).toString());
    }

    @Test
    public void testDateComparitor(){
        ReemoAPI api = new ReemoAPI();
        //User user = api.getUser("9522287193");
        User user = new User();
        user.updateTime = "2018-01-29 18:18:23.0000000 +00:00";

        String fakeEarlyDate = "2017-01-29 15:18:21.0000000 +00:00";
        System.out.println(user.updateTime);
        assert api.isLater(user.updateTime, fakeEarlyDate);
    }
}
