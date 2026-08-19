//package Config;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//
//import java.time.Duration;
//
//public class Setup {
//
//    public WebDriver driver;
//
//    @BeforeMethod
//    public void setup() {
//
//        driver = new ChromeDriver();
//
//        driver.manage().window().maximize();
//
//        driver.manage().timeouts()
//                .pageLoadTimeout(Duration.ofSeconds(60));
//
//        driver.manage().timeouts()
//                .implicitlyWait(Duration.ofSeconds(10));
//
//        driver.get("https://dailyfinance.roadtocareer.net/");
//    }
//
//    @AfterMethod
//    public void teardown() {
//
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }
//}


package Config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class Setup {

    public WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(60)
        );

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(10)
        );

        driver.get(
                "https://dailyfinance.roadtocareer.net/"
        );
    }


    @AfterMethod(alwaysRun = true)
    public void teardown() {

        if (driver != null) {
            driver.quit();
        }
    }
}