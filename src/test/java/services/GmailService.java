package services;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GmailService {



    Properties prop;

    public GmailService() throws IOException {

        prop = new Properties();
        FileInputStream fs= new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }


    public String getGmailList()  {

        RestAssured.baseURI="https://gmail.googleapis.com/";
        Response res = given().contentType("application/json").header("Authorization","Bearer "+prop.get("Gmail_token")).when().get("/gmail/v1/users/me/messages");

        JsonPath jsonPath = res.jsonPath();
        return jsonPath.get("messages[0].id").toString();
        //System.out.println(messageId);


    }

    public String readEmail() throws IOException {

        GmailService gs = new GmailService();
        String messageId = gs.getGmailList();

        RestAssured.baseURI="https://gmail.googleapis.com/";
        Response res = given().contentType("application/json").header("Authorization","Bearer "+prop.get("Gmail_token")).
                when().get("/gmail/v1/users/me/messages/"+messageId);

        JsonPath jsonPath = res.jsonPath();
        //String myMail = jsonPath.get("snippet");

        return jsonPath.get("snippet");


    }


     public static void main(String[] args) throws IOException {
        GmailService gs = new GmailService();
        gs.readEmail();
        String myMail = gs.readEmail();
        System.out.println(myMail);



    }
}
