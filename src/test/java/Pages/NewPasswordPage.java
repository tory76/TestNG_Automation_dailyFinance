package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;

import java.time.Duration;

public class NewPasswordPage {

    @FindBy(xpath = "//label[contains(text(),'New Password')]/following::input[1]")
    WebElement txtPassword;

    @FindBy(xpath = "//label[contains(text(),'Confirm Password')]/following::input[1]")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//button[normalize-space()='Reset Password']")
    WebElement btnSubmit;

    WebDriver driver;

    public NewPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setNewPassword(String password) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        txtPassword.sendKeys(password);
        txtConfirmPassword.sendKeys(password);
        Thread.sleep(5000);
        btnSubmit.click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("email")
        ));
    }
}





