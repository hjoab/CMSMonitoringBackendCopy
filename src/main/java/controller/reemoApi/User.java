package controller.reemoApi;

import com.google.gson.Gson;

public class User {
    public String name;
    public String address;
    public String phone;
    public String latitude;
    public String longitude;
    public String battery;

    @Override
    public String toString() {

        Gson gson = new Gson();
        return gson.toJson(this).toString();
    }
}
