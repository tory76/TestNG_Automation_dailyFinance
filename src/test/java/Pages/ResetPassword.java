package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPassword {

    WebDriver driver;

    // Email field
    @FindBy(xpath = "//input[@type='email']")
    WebElement txtEmail;

    // Send Reset Link button
    @FindBy(xpath = "//button[normalize-space()='Send Reset Link']")
    WebElement btnSendResetLink;

    public ResetPassword(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

   // public void enterEmail(String email){
        //txtEmail.clear();
       // txtEmail.sendKeys(email);
    //}

    //public void clickSendResetLink(){

        //btnSendResetLink.click();
    //}

    public void resetPassword(String email){

        txtEmail.clear();
        txtEmail.sendKeys(email);
        btnSendResetLink.click();
        //enterEmail(email);
        //clickSendResetLink();
    }
}
