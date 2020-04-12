package utils;

public class GlobalVariables {

    public static String host = "https://localhost:5001";

    private static String apiUsers = "/api/users/";

    public static String findByUserName = "TestingUser-FindByUserName";
    public static int findByUserId = 2;
    //get
    public static String getAllUsers = "";
    public static String getUsersByUserName = "";
    public static String getUsersByUserId = "";

    //post
    public static String postRequestUrl = "";

    //Delete
    public static String removeByUserName = "";
    public static String removeByUserId = "";

    //Bonus
    public static String matchesWith = "";

    public static void buildUrls(){
        getAllUsers = host.concat(apiUsers);
        getUsersByUserName = getAllUsers.concat("findByUserName/%s");
        getUsersByUserId = getAllUsers.concat("findByUserId/%d");
        postRequestUrl = getAllUsers.concat("add");
        removeByUserName = getAllUsers.concat("removeByUserName/%s");
        removeByUserId = getAllUsers.concat("removeByUserId/%d");
        matchesWith = getAllUsers.concat("usernameContains(\'%s')");
    }
}
