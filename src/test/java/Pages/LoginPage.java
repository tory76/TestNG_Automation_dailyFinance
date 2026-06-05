package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;

public class LoginPage {


    @FindBy(id="email")
    public WebElement Email;

    @FindBy(id="password")
    public WebElement Pass;
    @FindBy (css ="[type=submit]")
    WebElement loginbtn;

    @FindBy(xpath = "//a[contains(text(),'Reset it here')]")
    WebElement resetPasswordLink;

    public WebDriver driver;

   public LoginPage(WebDriver driver){
       this.driver = driver;
       PageFactory.initElements(driver,this);

   }

   public void doLogin(String email,String password)

   {
       Email.sendKeys(email);
       Pass.sendKeys(password);
       loginbtn.click();
   }


    public void clickResetPassword(){



        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement resetBtn =
                wait.until(ExpectedConditions.elementToBeClickable(
                        resetPasswordLink));

        resetPasswordLink.click();
    }

    public void clearLoginFields(){
        Email.clear();
        Pass.clear();
    }
}
