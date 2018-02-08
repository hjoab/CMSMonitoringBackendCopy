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
}
