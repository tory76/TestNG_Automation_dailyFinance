package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    @FindBy(id="email")
    public WebElement Email;

    @FindBy(id="password")
    public WebElement Pass;
    @FindBy (css ="[type=submit]")
    WebElement loginbtn;


   public LoginPage(WebDriver driver){

       PageFactory.initElements(driver,this);

   }

   public void doLogin(String email,String password)

   {
       Email.sendKeys(email);
       Pass.sendKeys(password);
       loginbtn.click();
   }
}
