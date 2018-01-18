package controller.reemoApi;

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
        user.address = "4241 44th ave s, Minneapolis, MN, 55406";
        user.battery = "50";
        user.latitude = "44.9716";
        user.longitude = "-93.2863";
        return user;
    }
}
