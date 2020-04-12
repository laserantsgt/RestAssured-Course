package utils;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.json.JSONObject;

public class CreateBodyContent {

    public static JSONObject createNewUserBodyContent(){
        JSONObject createNewUserContent = new JSONObject();
        Faker fakeInfo = new Faker();
        Name name = fakeInfo.name();
        createNewUserContent.put("FirstName", name.firstName())
                .put("LastName", name.lastName())
                .put("UserName", name.username().concat("-QAPurposes"));
        return createNewUserContent;
    }
}
