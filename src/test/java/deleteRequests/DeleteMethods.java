package deleteRequests;

import io.restassured.response.Response;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import postRequest.CreateNewUser;
import utils.CreateBodyContent;
import utils.CreateNewUserProcess;
import utils.GlobalVariables;
import utils.RequestMakers;

public class DeleteMethods {

    @Test(groups = {"all","deleteByUserName","deleteMethos"}, description = "Borra el usuario por username")
    public void deleteByUserName(){
        JSONObject userToCreate = CreateBodyContent.createNewUserBodyContent();
        String userName = userToCreate.getString("UserName");
        Response createResponse = CreateNewUserProcess.createUser(GlobalVariables.postRequestUrl, userToCreate);
        Assert.assertTrue(200 == createResponse.getStatusCode(), "No se pudo crear el usuario.");
        String deleteUrl = String.format(GlobalVariables.removeByUserName,userName);
        System.out.println("Url Delete: " + deleteUrl);
        Response deleteResponse = RequestMakers.makeDeleteRequest(deleteUrl);
        System.out.println("Response Delete: " + deleteResponse.getBody().asString());
        Assert.assertTrue(200 == deleteResponse.getStatusCode(), "No se pudo borrar el usuario.");
        JSONObject deleteResponseJson = new JSONObject(deleteResponse.getBody().asString());
        Assert.assertTrue(deleteResponseJson.getInt("status") == 1, "El usuario no se logro eliminar con el url: " + deleteUrl);
    }


    @Test(groups = {"all","deleteById","deleteMethos"}, description = "Borra el usuario por userid")
    public void deleteById(){
        JSONObject userToCreate = CreateBodyContent.createNewUserBodyContent();
        Response createResponse = CreateNewUserProcess.createUser(GlobalVariables.postRequestUrl, userToCreate);
        Assert.assertTrue(200 == createResponse.getStatusCode(), "No se pudo crear el usuario.");
        JSONObject createResponseJson = new JSONObject(createResponse.getBody().asString());
        int userId = createResponseJson.getInt("id");
        String deleteUrl = String.format(GlobalVariables.removeByUserId,userId);
        System.out.println("Url Delete: " + deleteUrl);
        Response deleteResponse = RequestMakers.makeDeleteRequest(deleteUrl);
        System.out.println("Response Delete: " + deleteResponse.getBody().asString());
        Assert.assertTrue(200 == deleteResponse.getStatusCode(), "No se pudo borrar el usuario.");
        JSONObject deleteResponseJson = new JSONObject(deleteResponse.getBody().asString());
        Assert.assertTrue(deleteResponseJson.getInt("status") == 1, "El usuario no se logro eliminar con el url: " + deleteUrl);
    }
}
