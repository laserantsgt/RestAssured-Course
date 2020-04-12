package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utils.GlobalVariables;

public class RestAssuredListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        String hostValue = System.getProperty("host");
        GlobalVariables.host = hostValue;
        GlobalVariables.buildUrls();
    }
}
