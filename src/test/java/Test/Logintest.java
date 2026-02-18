package Test;

import Config.Setup;
import Pages.LoginPage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;


public class Logintest extends Setup {


    @Test (priority=1,description = "verify that user login with wrong creds")
    public void loginwithwrongcreds() {
       LoginPage loginPage = new LoginPage(driver);
       loginPage.doLogin("meherin@gmail.com","009");
       String validationActual = driver.findElement(By.tagName("p")).getText();
       String validationExpected= ("Invalid email or password");
       Assert.assertTrue(validationActual.contains(validationExpected));
       clearData();

    }

    @Test (priority=2,description = "verify that user login with valid creds")
    public void userLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONObject userobj = Utils.readJSONdata("./src/test/resources/Users.json");
        loginPage.doLogin(userobj.get("Email").toString(),userobj.get("Password").toString());
        //loginPage.doLogin("testermeherin456+555@gmail.com","1234");

        String headertext= driver.findElement(By.tagName("h2")).getText();
        //Assert.assertTrue(headertext.contains("User Daily Costs"),"Not found the text");

        //Assert.assertTrue (driver.findElement(By.className("search-input")).isDisplayed()) ;

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue (driver.findElement(By.className("search-input")).isDisplayed(),"item not found") ;
        softAssert.assertTrue(headertext.contains("User Daily Costs"),"Not found the text");
        //softAssert.assertTrue(driver.findElements(By.tagName("span")).get(1).getText().contains("No cost"), "no cost found");

        softAssert.assertAll();


       // Soft Assertion executes all assertion , hard assertion if fails,then do not continue next assertions.
    }

     public void clearData(){

        LoginPage loginPage=new LoginPage(driver);
        loginPage.Email.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        loginPage.Pass.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);


     }



}
