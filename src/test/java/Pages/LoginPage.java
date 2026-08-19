//package Pages;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import utils.Utils;
//
//import java.time.Duration;
//
//public class LoginPage {
//
//
//    @FindBy(id="email")
//    public WebElement Email;
//
//    @FindBy(id="password")
//    public WebElement Pass;
//    @FindBy (css ="[type=submit]")
//    WebElement loginbtn;
//
//    @FindBy(css="svg[data-testid='AccountCircleIcon']")
//    WebElement profileIcon;
//
//    @FindBy(xpath="//li[normalize-space()='Logout']")
//    WebElement logoutBtn;
//
//    @FindBy(xpath = "//h2[normalize-space()='User Daily Costs']")
//    WebElement dashboardHeading;
//
//    @FindBy(xpath="//p[contains(text(),'Invalid email or password')]")
//    WebElement invalidMsg;
//
//    @FindBy(xpath = "//a[contains(text(),'Reset it here')]")
//    WebElement resetPasswordLink;
//
//    public WebDriver driver;
//
//   public LoginPage(WebDriver driver){
//       this.driver = driver;
//       PageFactory.initElements(driver,this);
//
//   }
//
////    public void doLogin(String email, String password){
////
////        WebDriverWait wait =
////                new WebDriverWait(driver, Duration.ofSeconds(20));
////
////        wait.until(ExpectedConditions.visibilityOf(Email));
////
////        Email.clear();
////        Pass.clear();
////
////        Email.sendKeys(email);
////        Pass.sendKeys(password);
////
////        loginbtn.click();
////    }
//
//
//    public void doLogin(String emailValue, String passwordValue) {
//
//        WebDriverWait wait =
//                new WebDriverWait(driver, Duration.ofSeconds(20));
//
//        wait.until(ExpectedConditions.visibilityOf(Email));
//
//        Email.clear();
//        Pass.clear();
//
//        Email.sendKeys(emailValue);
//        Pass.sendKeys(passwordValue);
//
//        wait.until(ExpectedConditions.elementToBeClickable(loginbtn))
//                .click();
//    }
//
//    public void logout(){
//
//        WebDriverWait wait =
//                new WebDriverWait(driver, Duration.ofSeconds(20));
//
//        wait.until(ExpectedConditions.elementToBeClickable(profileIcon))
//                .click();
//
//        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn))
//                .click();
//
//        wait.until(ExpectedConditions.urlContains("/login"));
//    }
//
//
//    public boolean isLoginSuccessful()  {
//
//        WebDriverWait wait =
//                new WebDriverWait(driver, Duration.ofSeconds(20));
//
//        return wait.until(
//                        ExpectedConditions.visibilityOf(dashboardHeading))
//                .isDisplayed();
//
//    }
//
//    public boolean isLoginFailed(){
//
//        WebDriverWait wait =
//                new WebDriverWait(driver, Duration.ofSeconds(20));
//
//        return wait.until(
//                        ExpectedConditions.visibilityOf(invalidMsg))
//                .isDisplayed();
//    }
//    public void clickResetPassword(){
//
//        WebDriverWait wait =
//                new WebDriverWait(driver, Duration.ofSeconds(30));
//
//        wait.until(ExpectedConditions.elementToBeClickable(resetPasswordLink))
//                .click();
//    }
//
//    public void clearLoginFields(){
//        Email.clear();
//        Pass.clear();
//    }
//}
//
//


package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // =========================
    // Login Page Locators
    // =========================

    @FindBy(id = "email")
    public WebElement Email;

    @FindBy(id = "password")
    public WebElement Pass;

    @FindBy(css = "[type='submit']")
    private WebElement loginbtn;

    // =========================
    // Dashboard Locators
    // =========================

    @FindBy(css = "svg[data-testid='AccountCircleIcon']")
    private WebElement profileIcon;

    @FindBy(xpath = "//li[normalize-space()='Logout']")
    private WebElement logoutBtn;

    @FindBy(xpath = "//h2[normalize-space()='User Daily Costs']")
    private WebElement dashboardHeading;

    @FindBy(className = "search-input")
    private WebElement searchInput;

    // =========================
    // Login Error
    // =========================

    @FindBy(xpath = "//p[contains(text(),'Invalid email or password')]")
    private WebElement invalidMsg;

    // =========================
    // Reset Password
    // =========================

    @FindBy(xpath = "//a[contains(text(),'Reset it here')]")
    private WebElement resetPasswordLink;


    // =========================
    // Constructor
    // =========================

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);

        this.wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );
    }


    // =========================
    // Login
    // =========================

    public void doLogin(
            String emailValue,
            String passwordValue
    ) {

        wait.until(
                ExpectedConditions.visibilityOf(Email)
        );

        Email.clear();
        Pass.clear();

        Email.sendKeys(emailValue);
        Pass.sendKeys(passwordValue);

        wait.until(
                ExpectedConditions.elementToBeClickable(loginbtn)
        ).click();
    }


    // =========================
    // Verify Login Successful
    // =========================

    public boolean isLoginSuccessful() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("add-cost-button")
                )
        ).isDisplayed();
    }


    // =========================
    // Verify Login Failed
    // =========================

    public boolean isLoginFailed() {

        return wait.until(
                ExpectedConditions.visibilityOf(invalidMsg)
        ).isDisplayed();
    }


    // =========================
    // Get Dashboard Heading
    // =========================

    public String getDashboardHeading() {

        return wait.until(
                ExpectedConditions.visibilityOf(dashboardHeading)
        ).getText();
    }


    // =========================
    // Verify Search Input
    // =========================

    public boolean isSearchInputDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(searchInput)
        ).isDisplayed();
    }


    // =========================
    // Logout
    // =========================

    public void logout() {

        wait.until(
                ExpectedConditions.elementToBeClickable(profileIcon)
        ).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(logoutBtn)
        ).click();

        wait.until(
                ExpectedConditions.urlContains("/login")
        );
    }


    // =========================
    // Reset Password
    // =========================

    public void clickResetPassword() {

        wait.until(
                ExpectedConditions.elementToBeClickable(resetPasswordLink)
        ).click();
    }


    // =========================
    // Clear Login Fields
    // =========================

    public void clearLoginFields() {

        Email.clear();
        Pass.clear();
    }
}