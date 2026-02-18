package Test;

import Config.Setup;
import Pages.RegPage;
import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class RegTest extends Setup {

    @Test
    public void userReg() throws IOException, ParseException {


        RegPage regPage1=new RegPage(driver);
        Faker faker= new Faker();

        String firstName = faker.name().firstName();
        String email ="testermeherin456+"+firstName+"@gmail.com";
        String password = "1234";
        String phoneNum = "0160"+ Utils.generateRandomNumber(1000000,9999999);
        driver.findElement(By.partialLinkText("Register")).click();
        regPage1.doReg(firstName,email,password,phoneNum);
        Utils.saveJSONDATA(firstName,email,password,phoneNum);
    }
}

