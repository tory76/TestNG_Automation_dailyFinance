package Test;

import Config.Setup;
import Pages.LoginPage;
import Pages.ResetPassword;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class ResetPasswordTest extends Setup {

    /*
    NEGATIVE TEST CASE 1
    Invalid Email Format
     */
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


    /*
    NEGATIVE TEST CASE 2
    Empty Email Field
     */
    @Test(priority = 2, description = "reset password with empty email")
    public void resetPasswordEmptyEmail() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickResetPassword();

        ResetPassword resetPass = new ResetPassword(driver);

        // Click button without entering email
        resetPass.resetPassword("");

        SoftAssert softAssert = new SoftAssert();

        // Locate email field
        WebElement emailField =
                driver.findElement(By.cssSelector("input[type='email']"));

        // Get HTML5 validation message
        String validationMessage =
                emailField.getAttribute("validationMessage");

        // Assertion
        softAssert.assertTrue(
                validationMessage.contains("Please fill in this field"),
                "Required field validation not displayed"
        );

        softAssert.assertAll();
    }

    @Test(priority = 3, description = "reset password using valid registered email")
    public void resetPasswordValidEmail(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickResetPassword();

        ResetPassword resetPass = new ResetPassword(driver);

        // ✅ Input VALID registered Gmail
        resetPass.resetPassword("testermeherin456Ione@gmail.com");

        SoftAssert softAssert = new SoftAssert();

        String successMessage =
                driver.findElement(By.tagName("p")).getText();

        softAssert.assertTrue(
                successMessage.contains("Password reset link sent to your email"),
                "Reset link success message not displayed"
        );

        softAssert.assertAll();
    }
}