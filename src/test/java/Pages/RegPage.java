package Pages;

import Config.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v144.autofill.model.Address;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

public class RegPage {

    @FindBy(id="firstName")
    WebElement FirstName;

    @FindBy(id="lastName")
    WebElement LastName;


    @FindBy(id="email")
    WebElement Email;


    @FindBy(id="password")
    WebElement Password;

    @FindBy(id="phoneNumber")
    WebElement phoneNumber;

    @FindBy(id="address")
    WebElement address;

    @FindBy(css="[type=radio]")
    List<WebElement> rbGender;

    @FindBy(css= "[type=checkbox]")
    WebElement checkAgrmnt;

    @FindBy(id="register")
    WebElement Registerbtn;

    public WebDriver driver;

    public RegPage(WebDriver driver){

        PageFactory.initElements(driver,this);
    }


    public void doReg(UserModel userModel) {



        FirstName.sendKeys(userModel.getFirstName());
        LastName.sendKeys(userModel.getLastName()==null?"":userModel.getLastName());
        Email.sendKeys(userModel.getEmail());
        Password.sendKeys(userModel.getPassword());
        phoneNumber.sendKeys(userModel.getPhoneNum());
        address.sendKeys(userModel.getAddress()==null?"":userModel.getAddress());
        rbGender.get(0).click();
        checkAgrmnt.click();
        Utils.elementWaiter(driver,Registerbtn );
        Registerbtn.click();
    }
}

