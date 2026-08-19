package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AdminPage {

    WebDriver driver;

    public AdminPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "input.search-box")
    WebElement txtSearch;

    // <-- Update this locator after inspecting the email column
    @FindBy(xpath = "//tbody/tr[1]/td[3]")
    WebElement userEmail;

    public void searchUser(String email){

        txtSearch.clear();
        txtSearch.sendKeys(email);
        txtSearch.sendKeys(Keys.ENTER);
    }

    public void clearSearch() {

        txtSearch.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txtSearch.sendKeys(Keys.DELETE);
        txtSearch.sendKeys(Keys.ENTER);
    }


    public String getUserEmail(){

        return userEmail.getText().trim();
    }


    public List<String> getAllUsers() {

        List<String> users = new ArrayList<>();

        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));

        for (WebElement row : rows) {

            List<WebElement> cols = row.findElements(By.tagName("td"));

            String data =
                    cols.get(0).getText() + " | " +
                            cols.get(1).getText() + " | " +
                            cols.get(2).getText() + " | " +
                            cols.get(3).getText() + " | " +
                            cols.get(4).getText() + " | " +
                            cols.get(5).getText() + " | " +
                            cols.get(6).getText();

            users.add(data);
        }

        return users;
    }
}