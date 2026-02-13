import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class RegTest extends Setup{

    @Test
    public void userReg(){


        RegPage regPage1=new RegPage(driver);
        String firstName = "User2";
        String email ="testermeherin456+666@gmail.com";
        String password = "1234";
        String phoneNum = "01625437809";
        driver.findElement(By.partialLinkText("Register")).click();
        regPage1.doReg(firstName,email,password,phoneNum);
    }
}

