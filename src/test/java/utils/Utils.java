package utils;

import Config.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class Utils {
    public static int generateRandomNumber(int min,int max){

        double randomNumber = Math.random()*(max-min)+min;
        return (int)Math.round(randomNumber);

    }

    public static void elementWaiter(WebDriver driver, WebElement element)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));

    }

//    public static void main(String [] args){
//
//        System.out.println(generateRandomNumber(10,90));
//    }

    public static void saveJSONDATA(UserModel userModel) throws IOException, ParseException {

        String filepath = "./src/test/resources/Users.json";

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(filepath));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname",userModel.getFirstName());
        jsonObject.put("Lastname",userModel.getLastName());
        jsonObject.put("Email",userModel.getEmail());
        jsonObject.put("Password",userModel.getPassword());
        jsonObject.put("PhoneNumber",userModel.getPhoneNum());
        jsonObject.put("Address",userModel.getAddress());

        jsonArray.add(jsonObject);

        FileWriter fileWriter= new FileWriter(filepath);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();


    }


    public static JSONObject readJSONdata(String filepath) throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(filepath));
        jsonArray.get(jsonArray.size()-1);
        return (JSONObject) jsonArray.get(jsonArray.size()-1);
    }

         public static void scrollDown(WebDriver driver, int px){

             JavascriptExecutor js = ((JavascriptExecutor) driver);
             js.executeScript("window.scrollBy(0, "+px+");");
         }


}
