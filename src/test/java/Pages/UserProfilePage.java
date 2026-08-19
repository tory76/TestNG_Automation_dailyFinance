////package Pages;
////
////import org.openqa.selenium.Alert;
////import org.openqa.selenium.WebDriver;
////import org.openqa.selenium.WebElement;
////import org.openqa.selenium.support.FindBy;
////import org.openqa.selenium.support.PageFactory;
////import org.openqa.selenium.support.ui.ExpectedConditions;
////import org.openqa.selenium.support.ui.WebDriverWait;
////
////import java.time.Duration;
////
////public class UserProfilePage {
////
////    private WebDriver driver;
////    private WebDriverWait wait;
////
////    // Constructor
////    public UserProfilePage(WebDriver driver) {
////        this.driver = driver;
////        PageFactory.initElements(driver, this);
////
////        this.wait = new WebDriverWait(
////                driver,
////                Duration.ofSeconds(20)
////        );
////    }
////
////    // =========================
////    // Locators
////    // =========================
////
////    // Profile icon
////    @FindBy(css = "svg[data-testid='AccountCircleIcon']")
////    private WebElement profileIcon;
////
////    // Profile menu item
////    @FindBy(xpath = "//li[normalize-space()='Profile']")
////    private WebElement profileMenu;
////
////    // Edit button
////    @FindBy(xpath = "//button[normalize-space()='Edit']")
////    private WebElement btnEdit;
////
////    // Email input field
////    @FindBy(name = "email")
////    private WebElement txtEmail;
////
////    // Update button
////    @FindBy(xpath = "//button[normalize-space()='Update']")
////    private WebElement btnUpdate;
////
////
////    // =========================
////    // Methods
////    // =========================
////
////    /**
////     * Update user's email address
////     */
////    public void updateUserEmail(String newEmail) {
////
////        // Step 1: Click Profile Icon
////        wait.until(
////                ExpectedConditions.elementToBeClickable(profileIcon)
////        ).click();
////
////
////        // Step 2: Click Profile Menu
////        wait.until(
////                ExpectedConditions.elementToBeClickable(profileMenu)
////        ).click();
////
////
////        // Step 3: Click Edit Button
////        wait.until(
////                ExpectedConditions.elementToBeClickable(btnEdit)
////        ).click();
////
////
////        // Step 4: Wait for Email Field
////        wait.until(
////                ExpectedConditions.visibilityOf(txtEmail)
////        );
////
////
////        // Step 5: Clear Existing Email
////        txtEmail.clear();
////
////
////        // Step 6: Enter New Email
////        txtEmail.sendKeys(newEmail);
////
////
////        // Step 7: Click Update Button
////        wait.until(
////                ExpectedConditions.elementToBeClickable(btnUpdate)
////        ).click();
////
////
////        // Step 8: Wait for Alert
////        Alert alert = wait.until(
////                ExpectedConditions.alertIsPresent()
////        );
////
////
////        // Step 9: Print Alert Message
////        System.out.println(
////                "Update Alert: " + alert.getText()
////        );
////
////
////        // Step 10: Accept Alert
////        alert.accept();
////
////
////        // Step 11: Verify Email Updated
////        wait.until(
////                ExpectedConditions.attributeToBe(
////                        txtEmail,
////                        "value",
////                        newEmail
////                )
////        );
////    }
////
////
////    /**
////     * Get the current email from the profile page
////     */
////    public String getCurrentEmail() {
////
////        wait.until(
////                ExpectedConditions.visibilityOf(txtEmail)
////        );
////
////        return txtEmail.getAttribute("value");
////    }
////
////
////    /**
////     * Check if profile email field is displayed
////     */
////    public boolean isEmailFieldDisplayed() {
////
////        return wait.until(
////                ExpectedConditions.visibilityOf(txtEmail)
////        ).isDisplayed();
////    }
////}
//
//package Pages;
//
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class UserProfilePage {
//
//    private WebDriver driver;
//    private WebDriverWait wait;
//
//
//    public UserProfilePage(WebDriver driver) {
//
//        this.driver = driver;
//
//        this.wait = new WebDriverWait(
//                driver,
//                Duration.ofSeconds(20)
//        );
//
//        PageFactory.initElements(driver, this);
//    }
//
//
//    // Profile icon
//    @FindBy(css = "svg[data-testid='AccountCircleIcon']")
//    private WebElement profileIcon;
//
//
//    // Profile menu
//    @FindBy(xpath = "//li[normalize-space()='Profile']")
//    private WebElement profileMenu;
//
//
//    // Edit button
//    @FindBy(xpath = "//button[normalize-space()='Edit']")
//    private WebElement btnEdit;
//
//
//    // Email field
//    @FindBy(name = "email")
//    private WebElement txtEmail;
//
//
//    // Update button
//    @FindBy(xpath = "//button[normalize-space()='Update']")
//    private WebElement btnUpdate;
//
//
//    public void updateUserEmail(String newEmail) {
//
//        // 1. Open profile menu
//        wait.until(
//                ExpectedConditions.elementToBeClickable(profileIcon)
//        ).click();
//
//
//        // 2. Click Profile
//        wait.until(
//                ExpectedConditions.elementToBeClickable(profileMenu)
//        ).click();
//
//
//        // 3. Click Edit
//        wait.until(
//                ExpectedConditions.elementToBeClickable(btnEdit)
//        ).click();
//
//
//        // 4. Wait for email field
//        wait.until(
//                ExpectedConditions.visibilityOf(txtEmail)
//        );
//
//
//        // 5. Clear old email
//        txtEmail.sendKeys(
//                Keys.CONTROL,
//                "a"
//        );
//
//        txtEmail.sendKeys(Keys.BACK_SPACE);
//
//
//        // 6. Enter new email
//        txtEmail.sendKeys(newEmail);
//
//
//        // 7. Click Update
//        wait.until(
//                ExpectedConditions.elementToBeClickable(btnUpdate)
//        ).click();
//
//
//        // 8. Handle alert if application displays one
//        try {
//
//            Alert alert = new WebDriverWait(
//                    driver,
//                    Duration.ofSeconds(5)
//            ).until(
//                    ExpectedConditions.alertIsPresent()
//            );
//
//            System.out.println(
//                    "Update Alert: " + alert.getText()
//            );
//
//            alert.accept();
//
//        } catch (Exception e) {
//
//            System.out.println(
//                    "No alert appeared after email update."
//            );
//        }
//
//
//        // 9. Verify new email value
//        wait.until(
//                ExpectedConditions.attributeToBe(
//                        txtEmail,
//                        "value",
//                        newEmail
//                )
//        );
//    }
//
//
//    public String getCurrentEmail() {
//
//        return wait.until(
//                ExpectedConditions.visibilityOf(txtEmail)
//        ).getAttribute("value");
//    }
//
//
//    public boolean isEmailFieldDisplayed() {
//
//        return wait.until(
//                ExpectedConditions.visibilityOf(txtEmail)
//        ).isDisplayed();
//    }
//}


package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserProfilePage {

    private WebDriver driver;
    private WebDriverWait wait;


    // =========================
    // Constructor
    // =========================

    public UserProfilePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        PageFactory.initElements(driver, this);
    }


    // =========================
    // Locators
    // =========================

    @FindBy(css = "svg[data-testid='AccountCircleIcon']")
    private WebElement profileIcon;

    @FindBy(xpath = "//li[normalize-space()='Profile']")
    private WebElement profileMenu;

    @FindBy(xpath = "//button[normalize-space()='Edit']")
    private WebElement btnEdit;

    @FindBy(name = "email")
    private WebElement txtEmail;

    @FindBy(xpath = "//button[normalize-space()='Update']")
    private WebElement btnUpdate;


    // =========================
    // Open Profile
    // =========================

    public void openProfile() {

        wait.until(
                ExpectedConditions.elementToBeClickable(profileIcon)
        ).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(profileMenu)
        ).click();
    }


    // =========================
    // Click Edit
    // =========================

    public void clickEdit() {

        wait.until(
                ExpectedConditions.elementToBeClickable(btnEdit)
        ).click();
    }


    // =========================
    // Update Email
    // =========================

    public void updateUserEmail(String newEmail) {

        // Open Profile
        openProfile();

        // Click Edit
        clickEdit();

        // Wait for email field
        wait.until(
                ExpectedConditions.visibilityOf(txtEmail)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(txtEmail)
        );


        // Clear old email
        txtEmail.click();

        txtEmail.sendKeys(
                Keys.CONTROL + "a"
        );

        txtEmail.sendKeys(
                Keys.BACK_SPACE
        );


        // Enter new email
        txtEmail.sendKeys(newEmail);


        // Click Update
        wait.until(
                ExpectedConditions.elementToBeClickable(btnUpdate)
        ).click();


        // Handle browser alert
        Alert alert = wait.until(
                ExpectedConditions.alertIsPresent()
        );

        System.out.println(
                "Update Alert: " + alert.getText()
        );

        alert.accept();


        // Verify updated email
        wait.until(
                ExpectedConditions.attributeToBe(
                        txtEmail,
                        "value",
                        newEmail
                )
        );
    }


    // =========================
    // Get Current Email
    // =========================

    public String getCurrentEmail() {

        return wait.until(
                ExpectedConditions.visibilityOf(txtEmail)
        ).getAttribute("value");
    }


    // =========================
    // Verify Email Field
    // =========================

    public boolean isEmailFieldDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOf(txtEmail)
        ).isDisplayed();
    }
}