package Test;

import Config.Setup;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class AdminLoginTest extends Setup{


    private static final Logger log = LoggerFactory.getLogger(AdminLoginTest.class);

    @Test

    public void adminlogin() throws InterruptedException {

        LoginPage loginPage=new LoginPage(driver);
        //loginPage.doLogin("email","password");

        loginPage.doLogin(System.getProperty("email"),System.getProperty("password"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("total-count")));
        System.out.println(driver.findElement(By.className("total-count")).getText());



        Thread.sleep(10000);

    }

}
