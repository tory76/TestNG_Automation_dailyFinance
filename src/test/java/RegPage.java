import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegPage {

    @FindBy(id="firstName")
    WebElement FirstName;

    @FindBy(id="email")
    WebElement Email;


    @FindBy(id="password")
    WebElement Password;

    @FindBy(id="phoneNumber")
    WebElement phoneNumber;

    @FindBy(css="[type=radio]")
    List<WebElement> rbGender;

    @FindBy(css= "[type=checkbox]")
    WebElement checkAgrmnt;

    @FindBy(id="register")
    WebElement Registerbtn;


    public RegPage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }


    public void doReg(String firstName, String email, String password, String phoneNum ) {



        FirstName.sendKeys(firstName);
        Email.sendKeys(email);
        Password.sendKeys(password);
        phoneNumber.sendKeys(phoneNum);
        rbGender.get(0).click();
        checkAgrmnt.click();
        Registerbtn.click();
    }
}

