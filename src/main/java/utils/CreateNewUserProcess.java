package utils;

import io.restassured.response.Response;
import org.json.JSONObject;

public class CreateNewUserProcess {

    public static Response createUser(String url, JSONObject body){
        System.out.println("Url Post: " + url);
        System.out.println("Body\n" + body.toString(2));
        Response response = RequestMakers.makePostRequest(url,body.toString());
        return response;
    }
}
