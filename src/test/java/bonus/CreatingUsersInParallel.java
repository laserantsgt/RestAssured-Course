package bonus;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CreateBodyContent;
import utils.CreateNewUserProcess;
import utils.GlobalVariables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CreatingUsersInParallel {

    @DataProvider(name = "getBodyContent", parallel = true)
    private Iterator<Object[]> getBodyContent() {
        Collection<Object[]> data = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            JSONObject userToCreate = CreateBodyContent.createNewUserBodyContent();
            data.add(new Object[] {userToCreate});
        }
        return data.iterator();
    }

    @Test(groups = {"bonus-create"},description = "Creando Usuarios en paralelo.", dataProvider = "getBodyContent")
    public void createNewUser(JSONObject bodyContent){
        String url = GlobalVariables.postRequestUrl;
        Response response = CreateNewUserProcess.createUser(url,bodyContent);
        System.out.println(" Response:\n" + response.getBody().asString());
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200, "El request " + url + " Con body: " + bodyContent + " No retorno un status code 200");
        JSONObject responsePost = new JSONObject(response.getBody().asString());
        Assert.assertTrue(!responsePost.has("status"),"El request: "  + url + " Con body: " + bodyContent + " retorno infomacion incorecta\nRespose: " + responsePost);

    }
}
