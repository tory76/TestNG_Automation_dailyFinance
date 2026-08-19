//package Test;
//
//import Config.Setup;
//import Pages.LoginPage;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.testng.Assert;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//import utils.Utils;
//
//import java.io.FileReader;
//import java.io.IOException;
//
//
//public class Logintest extends Setup {
//
//
//    @Test (priority=1,description = "verify that user login with wrong creds")
//    public void loginwithwrongcreds() {
//       LoginPage loginPage = new LoginPage(driver);
//       loginPage.doLogin("meherin@gmail.com","009");
//       String validationActual = driver.findElement(By.tagName("p")).getText();
//       String validationExpected= ("Invalid email or password");
//       Assert.assertTrue(validationActual.contains(validationExpected));
//       clearData();
//
//    }
//
//    @Test(
//            priority = 2,
//            description = "verify that user login with valid creds",
//            groups = "smoke"
//    )
//    public void userLogin() throws IOException, ParseException {
//
//        LoginPage loginPage = new LoginPage(driver);
//
//        JSONObject user =
//                Utils.readJSONdata("./src/test/resources/Users.json");
//
//        // Login
//        loginPage.doLogin(
//                user.get("Email").toString(),
//                user.get("Password").toString()
//        );
//
//        // Verify Dashboard heading
//        String headertext =
//                driver.findElement(By.tagName("h2")).getText();
//
//        // Soft Assertions
//        SoftAssert softAssert = new SoftAssert();
//
//        // Verify search input is displayed
//        softAssert.assertTrue(
//                driver.findElement(By.className("search-input")).isDisplayed(),
//                "Search input is not displayed"
//        );
//
//        // Verify dashboard heading
//        softAssert.assertTrue(
//                headertext.contains("User Daily Costs"),
//                "User Daily Costs heading is not displayed"
//        );
//
//        // Execute all assertions
//        softAssert.assertAll();
//    }
//
//     public void clearData(){
//
//        LoginPage loginPage=new LoginPage(driver);
//        loginPage.Email.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
//        loginPage.Pass.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
//
//
//     }
//
//
//}

package Test;

import Config.Setup;
import Pages.LoginPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Utils;

import java.io.IOException;

public class Logintest extends Setup {

    // =========================
    // Negative Login Test
    // =========================

    @Test(
            priority = 1,
            description = "verify that user login with wrong creds"
    )
    public void loginwithwrongcreds() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.doLogin(
                "meherin@gmail.com",
                "009"
        );

        Assert.assertTrue(
                loginPage.isLoginFailed(),
                "Invalid email or password message was not displayed."
        );
    }


    // =========================
    // Positive Login Test
    // =========================

    @Test(
            priority = 2,
            description = "verify that user login with valid creds",
            groups = "smoke"
    )
    public void userLogin()
            throws IOException, ParseException {

        LoginPage loginPage = new LoginPage(driver);

        JSONObject user =
                Utils.readJSONdata(
                        "./src/test/resources/Users.json"
                );

        String email =
                user.get("Email").toString();

        String password =
                user.get("Password").toString();


        // Login
        loginPage.doLogin(
                email,
                password
        );


        // =========================
        // Soft Assertions
        // =========================

        SoftAssert softAssert = new SoftAssert();


        // Verify dashboard
        softAssert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Dashboard was not displayed after login."
        );


        // Verify search input
        softAssert.assertTrue(
                loginPage.isSearchInputDisplayed(),
                "Search input is not displayed."
        );


        // Verify dashboard heading
        softAssert.assertTrue(
                loginPage.getDashboardHeading()
                        .contains("User Daily Costs"),
                "User Daily Costs heading is not displayed."
        );


        // Execute all assertions
        softAssert.assertAll();
    }
}
