package Test;

import Config.Setup;
import Config.UserModel;
import Pages.RegPage;
import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class RegTest extends Setup {

    @Test (priority = 1, description="verify that user only fill up mandatory fields" , groups = "smoke")
    public void MandatoryuserReg() throws IOException, ParseException, InterruptedException {


        RegPage regPage1=new RegPage(driver);
        Faker faker= new Faker();

        String firstName = faker.name().firstName();
        String email ="testermeherin456+"+firstName+"@gmail.com";
        String password = "1234";
        String phoneNum = "0160"+ Utils.generateRandomNumber(1000000,9999999);
        driver.findElement(By.partialLinkText("Register")).click();

        UserModel userModel=new UserModel();
        userModel.setFirstName(firstName);
        //userModel.setLastName(firstName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNum(phoneNum);
        //userModel.setAddress(address);

        regPage1.doReg(userModel);
        Utils.saveJSONDATA(userModel);
        Thread.sleep(5000);
    }


    @Test (priority = 2, description="verify that user only fill up all fields")
    public void FulluserReg() throws IOException, ParseException, InterruptedException {


        RegPage regPage1=new RegPage(driver);
        Faker faker= new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().firstName();
        String email ="testermeherin456+"+firstName+"@gmail.com";
        String password = "1234";
        String phoneNum = "0160"+ Utils.generateRandomNumber(1000000,9999999);
        String address = "Dhaka";
        driver.findElement(By.partialLinkText("Register")).click();
        UserModel userModel=new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNum(phoneNum);
        userModel.setAddress(address);

        Thread.sleep(5000);
        regPage1.doReg(userModel);
        Thread.sleep(5000);
        Utils.saveJSONDATA(userModel);
    }


}

