package postRequest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CreateBodyContent;
import utils.CreateNewUserProcess;
import utils.GlobalVariables;
import utils.RequestMakers;

public class CreateNewUser {

    @Test(groups = {"all","createNewUser", "postRequest"})
    public void createNewUser(){
        String url = GlobalVariables.postRequestUrl;
        JSONObject bodyContent = CreateBodyContent.createNewUserBodyContent();
        Response response = CreateNewUserProcess.createUser(url,bodyContent);
        System.out.println(" Response:\n" + response.getBody().asString());
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200, "El request " + url + " Con body: " + bodyContent + " No retorno un status code 200");
        JSONObject responsePost = new JSONObject(response.getBody().asString());
        Assert.assertTrue(!responsePost.has("status"),"El request: "  + url + " Con body: " + bodyContent + " retorno infomacion incorecta\nRespose: " + responsePost);

    }
}
