package Test;

import Config.Setup;
import Pages.DashboardPage;
import Pages.LoginPage;
import jdk.jshell.execution.Util;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class DashboardTest extends Setup {

    @BeforeTest
    public void userLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONObject  userobj= Utils.readJSONdata("./src/test/resources/Users.json");
        loginPage.doLogin(userobj.get("Email").toString(), userobj.get("Password").toString());
        //userobj
        }


    @Test(priority= 1, description = "Verify that item add only mandatory fields")
    public  void mandatoryaddItem() throws InterruptedException {


        DashboardPage dashboardpg = new DashboardPage(driver);
        dashboardpg. addItems ( "item"+Utils.generateRandomNumber(1,100), String.valueOf(Utils.generateRandomNumber(30,300)));
    }


}
