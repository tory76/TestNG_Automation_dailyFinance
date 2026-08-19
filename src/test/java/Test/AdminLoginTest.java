package Test;

import Config.Setup;
import Pages.AdminPage;
import Pages.LoginPage;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Utils;

import java.time.Duration;
import java.util.List;

public class AdminLoginTest extends Setup{


    private static final Logger log = LoggerFactory.getLogger(AdminLoginTest.class);

    @Test
    public void adminlogin() throws Exception {

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.doLogin(
                System.getProperty("email"),
                System.getProperty("password")
        );

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("total-count")));

        JSONObject user =
                Utils.readJSONdata("./src/test/resources/Users.json");

        String updatedEmail =
                user.get("Email").toString();

        AdminPage adminPage =
                new AdminPage(driver);

        Thread.sleep(2000);
        adminPage.searchUser(updatedEmail);
        Thread.sleep(2000);

        Assert.assertEquals(
                adminPage.getUserEmail(),
                updatedEmail
        );

        adminPage.clearSearch();

        Thread.sleep(3000);

        driver.navigate().refresh();

        Thread.sleep(3000);

        List<String> users = adminPage.getAllUsers();

        Utils.saveUsersToTextFile(users);

        System.out.println("Total Users = " + users.size());

        for (String userData : users) {
            System.out.println(userData);
        }
    }
}
