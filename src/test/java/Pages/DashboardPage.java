package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

public class DashboardPage {

     @FindBy(className = "add-cost-button")
     WebElement btnaddcost;

     @FindBy(id="itemName")
     WebElement itemName;

     @FindBy(id="amount")
     WebElement amount;

     @FindBy(css = "[type=submit]")

     WebElement submitbtn;

     //@FindBy(id="itemName")

    WebDriver driver;


    public DashboardPage (WebDriver driver) {

        PageFactory.initElements(driver,this);
        this.driver=driver;
    }

    public void addItems (String item, String Amount) throws InterruptedException {

        btnaddcost.click();
        itemName.sendKeys(item);
        amount.sendKeys(Amount);
        Utils.scrollDown(driver,500);
        submitbtn.click();
        Thread.sleep(500);
        driver.switchTo().alert().accept();
    }

}
