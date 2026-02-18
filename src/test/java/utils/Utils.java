package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static int generateRandomNumber(int max,int min){

        double randomNumber = Math.random()*(max-min)+min;
        return (int)Math.round(randomNumber);

    }

//    public static void main(String [] args){
//
//        System.out.println(generateRandomNumber(10,90));
//    }

    public static void saveJSONDATA(String firstName, String email, String password, String phoneNum) throws IOException, ParseException {

        String filepath = "./src/test/resources/Users.json";

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray= (JSONArray) jsonParser.parse(new FileReader(filepath));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname",firstName);
        jsonObject.put("Email",email);
        jsonObject.put("Password",password);
        jsonObject.put("PhoneNumber",phoneNum);

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


}
