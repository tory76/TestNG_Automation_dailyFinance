package Test;

import Config.Setup;
import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.NewPasswordPage;
import Pages.ResetPassword;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import services.GmailService;
import utils.Utils;

import java.time.Duration;


public class ResetPasswordTest extends Setup {

//    /*
//    NEGATIVE TEST CASE 1
//    Invalid Email Format
//     */
    @Test(priority = 1, description = "reset password using invalid email")
    public void resetPasswordInvalidEmail(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickResetPassword();

        SoftAssert softAssert = new SoftAssert();

        ResetPassword resetPass = new ResetPassword(driver);
        resetPass.resetPassword("abc123@gmail.com");

        String validationActual = driver.findElement(By.tagName("p")).getText();

        softAssert.assertTrue(validationActual.contains("Your email is not registered"),"Error message not found");

        softAssert.assertAll();

    }
//
//
//    /*
//    NEGATIVE TEST CASE 2
//    Empty Email Field
//     */
    @Test(priority = 2, description = "reset password with empty email")
    public void resetPasswordEmptyEmail() throws InterruptedException {

//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.clickResetPassword();

        driver.get("https://dailyfinance.roadtocareer.net/forgot-password");

        ResetPassword resetPass = new ResetPassword(driver);

        // Click button without entering email
        resetPass.resetPassword("");
        Thread.sleep(5000);

        SoftAssert softAssert = new SoftAssert();

        // Locate email field
        WebElement emailField =
                driver.findElement(By.cssSelector("input[type='email']"));

        // Get HTML5 validation message
        String validationMessage =
                emailField.getAttribute("validationMessage");

        System.out.println("Validation Message = [" + validationMessage + "]");

        // Assertion
        softAssert.assertTrue(
                validationMessage.contains("Please fill out this field"),
                "Required field validation not displayed"
        );

        softAssert.assertAll();
    }



    @Test(priority = 3, description = "reset password using valid registered email")
    public void resetPasswordValidEmail(){

//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.clickResetPassword();

        driver.get("https://dailyfinance.roadtocareer.net/forgot-password");

        ResetPassword resetPass = new ResetPassword(driver);

        // ✅ Input VALID registered Gmail
        resetPass.resetPassword("testermeherin456+Ozell@gmail.com");

        SoftAssert softAssert = new SoftAssert();

        String successMessage =
                driver.findElement(By.tagName("p")).getText();

        softAssert.assertTrue(
                successMessage.contains("Password reset link sent to your email"),
                "Reset link success message not displayed"
        );

        softAssert.assertAll();


    }


@Test(priority = 4, description = "Retrieve password reset email and set new password")
public void setNewPassword() throws Exception {

//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.clickResetPassword();

        driver.get("https://dailyfinance.roadtocareer.net/forgot-password");

        ResetPassword resetPassword = new ResetPassword(driver);

        resetPassword.resetPassword("testermeherin456+Wade@gmail.com");

        Thread.sleep(5000);

        GmailService gmailService = new GmailService();

        String resetLink = gmailService.getResetLink();

        System.out.println(resetLink);

        driver.get(resetLink);

        NewPasswordPage newPasswordPage =
                new NewPasswordPage(driver);

        // Generate password ONLY ONCE
        String newPassword = "Pass@" + Utils.generateRandomNumber(1000,9999);

// Reset password
        newPasswordPage.setNewPassword(newPassword);

// Open Login page
        driver.get("https://dailyfinance.roadtocareer.net/");

        LoginPage loginPage = new LoginPage(driver);

        String email = "testermeherin456+Wade@gmail.com";

// Login using SAME password
        loginPage.doLogin(email, newPassword);
        Thread.sleep(5000);

// Wait Dashboard
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("add-cost-button")
        ));

        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(
                dashboardPage.isAddCostDisplayed(),
                "Login failed using the new password"
        );

        System.out.println("Login successful with the new password.");
    }
    }
