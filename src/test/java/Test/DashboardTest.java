//package Test;
//
//import Config.Setup;
//import Pages.DashboardPage;
//import Pages.LoginPage;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.ParseException;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import utils.Utils;
//
//import java.io.IOException;
//
//public class DashboardTest extends Setup {
//
//    @BeforeMethod
//    public void userLogin() throws IOException, ParseException {
//
//        LoginPage loginPage = new LoginPage(driver);
//
//        JSONObject user =
//                Utils.readJSONdata("./src/test/resources/Users.json");
//
//        loginPage.doLogin(
//                user.get("Email").toString(),
//                user.get("Password").toString()
//        );
//    }
//
//    @Test(priority = 1)
//    public void verifyTwoItemsAdded() throws Exception {
//
//        DashboardPage dashboard = new DashboardPage(driver);
//
//        // Wait for dashboard
//        dashboard.waitForTotalRowsToLoad();
//
//        // Generate unique item names
//        String item1 =
//                "Item" + Utils.generateRandomNumber(100, 999);
//
//        String item2 =
//                "Item" + Utils.generateRandomNumber(100, 999);
//
//        System.out.println("Item 1 = " + item1);
//        System.out.println("Item 2 = " + item2);
//
//        // Add first item
//        dashboard.addItemAllFields(
//                item1,
//                String.valueOf(
//                        Utils.generateRandomNumber(100, 500)
//                ),
//                "2026-07-10",
//                "July",
//                "Automation Test"
//        );
//
//        // Add second item
//        dashboard.addItems(
//                item2,
//                String.valueOf(
//                        Utils.generateRandomNumber(50, 300)
//                )
//        );
//
//        // Verify first item
//        Assert.assertTrue(
//                dashboard.isItemPresent(item1),
//                "First item not found: " + item1
//        );
//
//        System.out.println(
//                "1st item found: " + item1
//        );
//
//        // Verify second item
//        Assert.assertTrue(
//                dashboard.isItemPresent(item2),
//                "Second item not found: " + item2
//        );
//
//        System.out.println(
//                "2nd item found: " + item2
//        );
//
//        System.out.println(
//                "SUCCESS: Both items were added successfully."
//        );
//    }
//


package Test;

import Config.Setup;
import Pages.DashboardPage;
import Pages.LoginPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class DashboardTest extends Setup {

    @BeforeMethod(alwaysRun = true)
    public void loginBeforeTest()
            throws IOException, ParseException {

        LoginPage loginPage = new LoginPage(driver);

        JSONObject user =
                Utils.readJSONdata(
                        "./src/test/resources/Users.json"
                );

        String email =
                user.get("Email").toString();

        String password =
                user.get("Password").toString();

        System.out.println(
                "Login Email: " + email
        );

        loginPage.doLogin(
                email,
                password
        );

        // Create DashboardPage AFTER login
        DashboardPage dashboard =
                new DashboardPage(driver);

        Assert.assertTrue(
                dashboard.isAddCostDisplayed(),
                "Dashboard did not load after login."
        );
    }


    @Test(
            priority = 1,
            description = "Verify that two items can be added"
    )
    public void verifyTwoItemsAdded() throws Exception {

        DashboardPage dashboard =
                new DashboardPage(driver);

        dashboard.waitForTotalRowsToLoad();

        String item1 =
                "Item" +
                        Utils.generateRandomNumber(100, 999);

        String item2 =
                "Item" +
                        Utils.generateRandomNumber(100, 999);

        System.out.println(
                "Item 1 = " + item1
        );

        System.out.println(
                "Item 2 = " + item2
        );


        dashboard.addItemAllFields(
                item1,
                String.valueOf(
                        Utils.generateRandomNumber(100, 500)
                ),
                "2026-07-10",
                "July",
                "Automation Test"
        );


        dashboard.addItems(
                item2,
                String.valueOf(
                        Utils.generateRandomNumber(50, 300)
                )
        );


        Assert.assertTrue(
                dashboard.isItemPresent(item1),
                "First item not found: " + item1
        );


        Assert.assertTrue(
                dashboard.isItemPresent(item2),
                "Second item not found: " + item2
        );


        System.out.println(
                "SUCCESS: Both items were added successfully."
        );
    }
}