package Test;

import Config.Setup;
import Config.UserModel;
import Pages.RegPage;
import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.GmailService;
import utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

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
//        GmailService gs = new GmailService();
//        String regEmailActual = gs.readEmail();
//        System.out.println(regEmailActual);
//        String regEmailExpected = "Welcome to our platform!";
//        Assert.assertTrue(regEmailActual.contains(regEmailExpected));
    }


    @Test (priority = 2, description="verify that user fill up all fields")
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

    @Test(priority = 3, description = "Register 3 users from CSV")
    public void registerUsersFromCSV() throws Exception {

        BufferedReader br = new BufferedReader(
                new FileReader("./src/test/resources/users.csv"));

        // Skip header
        br.readLine();

        String line;

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            UserModel user = new UserModel();

            user.setFirstName(data[0]);
            user.setLastName(data[1]);

            String uniqueEmail = data[2].split("@")[0] + Utils.generateRandomNumber(1000, 9999)
                    + "@gmail.com";

            user.setEmail(uniqueEmail);
            user.setPassword(data[3]);
            user.setPhoneNum(data[4]);
            user.setAddress(data[5]);

            driver.get("https://dailyfinance.roadtocareer.net/");
            driver.findElement(By.partialLinkText("Register")).click();

            RegPage regPage = new RegPage(driver);
            regPage.doReg(user);

            Utils.saveJSONDATA(user);

            System.out.println("Registered User: " + uniqueEmail);

            Thread.sleep(3000);

        }

        br.close();
    }


}

