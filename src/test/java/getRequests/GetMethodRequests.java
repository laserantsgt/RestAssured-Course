package getRequests;

import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.GlobalVariables;
import utils.RequestMakers;

public class GetMethodRequests {

    @Test(groups = {"all","getAllUsers", "getMethos"}, description = "Este metodo retorna todos ususarios.")
    public void getAllUsers(){
        Response response = RequestMakers.makeGetRequest(GlobalVariables.getAllUsers);
        int statusCode = response.getStatusCode();
        Assert.assertTrue(200 == statusCode,"El request " + GlobalVariables.getAllUsers + "retorno un codigo invalido.");
        String stringReponse = response.getBody().asString();
        JSONArray responseJson = new JSONArray(stringReponse);
        Assert.assertTrue(responseJson.length() > 2, "El request no retorno la informacion esperado. " + GlobalVariables.getAllUsers + "\nResponse: " + responseJson.toString(10));
    }

    @Test(groups = {"all","getUsersByUsername", "getMethos"}, description = "Este metodo retorna el usario que haga match con esa url..")
    public void getUsersByUsername(){
        String url = String.format(GlobalVariables.getUsersByUserName,GlobalVariables.findByUserName);
        System.out.println(url);
        Response response = RequestMakers.makeGetRequest(url);
        int statusCode = response.getStatusCode();
        Assert.assertTrue(200 == statusCode,"El request " + url + "retorno un codigo invalido.");
        String stringReponse = response.getBody().asString();
        JSONObject responseJson = new JSONObject(stringReponse);
        System.out.println(responseJson.toString(2));
        Assert.assertTrue(!responseJson.isEmpty(), "El request no retorno la informacion esperado. " + url + "\nResponse: " + responseJson.toString(10));
    }

    @Test(groups = {"all","getUsersByUserId", "getMethos"}, description = "Este metodo retorna el usario que haga match con esa url por ID.")
    public void getUsersByUserId(){
        String url = String.format(GlobalVariables.getUsersByUserId,GlobalVariables.findByUserId);
        System.out.println(url);
        Response response = RequestMakers.makeGetRequest(url);
        int statusCode = response.getStatusCode();
        Assert.assertTrue(200 == statusCode,"El request " + url + "retorno un codigo invalido.");
        String stringReponse = response.getBody().asString();
        JSONObject responseJson = new JSONObject(stringReponse);
        System.out.println(responseJson.toString(2));
        Assert.assertTrue(!responseJson.isEmpty(), "El request no retorno la informacion esperado. " + url + "\nResponse: " + responseJson.toString(10));
    }
}
