import org.testng.annotations.Test;

public class Logintest extends Setup{
    @Test
    public void userLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("testermeherin456+555rt@gmail.com","1234");


    }




}
