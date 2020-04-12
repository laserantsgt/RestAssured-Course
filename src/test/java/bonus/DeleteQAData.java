package bonus;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.GlobalVariables;
import utils.RequestMakers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DeleteQAData {

    @DataProvider(name = "getUsersCreatedByQA", parallel = true)
    private Iterator<Object[]> getUsersCreatedByQA() {
        String url = String.format(GlobalVariables.matchesWith,"QA");
        Response getQAData = RequestMakers.makeGetRequest(url);
        JSONArray qaUsers = new JSONArray(getQAData.getBody().asString());
        Collection<Object[]> data = new ArrayList<>();
        for (int i = 0; i < qaUsers.length(); i++){
            JSONObject qaUser = qaUsers.getJSONObject(i);
            data.add(new Object[] {qaUser});
        }
        return data.iterator();
    }

    @Test(groups = {"bonus", "bonus-delete"}, description = "Borra los usuarios creados por QA.", dataProvider = "getUsersCreatedByQA")
    public void deletingQAData(JSONObject qaUser){
        String userName = qaUser.getString("userName");
        String deleteUrl = String.format(GlobalVariables.removeByUserName,userName);
        Response deleteResponse = RequestMakers.makeDeleteRequest(deleteUrl);
        System.out.println("Response Delete: " + deleteResponse.getBody().asString());
        Assert.assertTrue(200 == deleteResponse.getStatusCode(), "No se pudo borrar el usuario.");
        JSONObject deleteResponseJson = new JSONObject(deleteResponse.getBody().asString());
        Assert.assertTrue(deleteResponseJson.getInt("status") == 1, "El usuario no se logro eliminar con el url: " + deleteUrl);

    }
}
