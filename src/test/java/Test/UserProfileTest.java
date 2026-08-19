//
//package Test;
//
//import Config.Setup;
//import Pages.LoginPage;
//import Pages.UserProfilePage;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.ParseException;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import utils.Utils;
//
//import java.io.IOException;
//
//public class UserProfileTest extends Setup {
//    @Test
//    public void updateEmailAndVerifyLogin()
//            throws IOException, ParseException, InterruptedException {
//
//        LoginPage loginPage = new LoginPage(driver);
//        UserProfilePage profile = new UserProfilePage(driver);
//
//        // Read existing user data
//        JSONObject user =
//                Utils.readJSONdata("./src/test/resources/Users.json");
//
//        String oldEmail =
//                user.get("Email").toString();
//
//        String password =
//                user.get("Password").toString();
//
//
//        // =========================
//        // STEP 1: Login
//        // =========================
//
//        loginPage.doLogin(
//                oldEmail,
//                password
//        );
//
//        Assert.assertTrue(
//                loginPage.isLoginSuccessful(),
//                "Login failed."
//        );
//
//
//        // =========================
//        // STEP 2: Generate New Email
//        // =========================
//
//        String newEmail =
//                "testermeherin"
//                        + Utils.generateRandomNumber(1000, 9999)
//                        + "@gmail.com";
//
//
//        // =========================
//        // STEP 3: Update Email
//        // =========================
//
//        profile.updateUserEmail(newEmail);
//
//
//        // =========================
//        // STEP 4: Verify Email Updated
//        // =========================
//
//        Assert.assertEquals(
//                profile.getCurrentEmail(),
//                newEmail,
//                "Email was not updated successfully."
//        );
//
//
//        // =========================
//        // STEP 5: Update JSON
//        // =========================
//
//        Utils.updateEmailInJSON(newEmail);
//
//
//        // =========================
//        // STEP 6: Logout
//        // =========================
//
//        loginPage.logout();
//
//
//        // =========================
//        // STEP 7: Login with New Email
//        // =========================
//
//        loginPage.doLogin(
//                newEmail,
//                password
//        );
//
//        Assert.assertTrue(
//                loginPage.isLoginSuccessful(),
//                "Login with updated email failed."
//        );
//
//
//        // =========================
//        // STEP 8: Logout Again
//        // =========================
//
//        loginPage.logout();
//
//
//        // =========================
//        // STEP 9: Try Old Email
//        // =========================
//
//        loginPage.doLogin(
//                oldEmail,
//                password
//        );
//
//
//        // =========================
//        // STEP 10: Verify Old Email Cannot Login
//        // =========================
//
//        Assert.assertTrue(
//                loginPage.isLoginFailed(),
//                "Old email should not be able to login."
//        );
//    }
//}


package Test;

import Config.Setup;
import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.UserProfilePage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class UserProfileTest extends Setup {

    @Test(
            description = "Update user email and verify login with new email"
    )
    public void updateEmailAndVerifyLogin()
            throws IOException, ParseException {

        LoginPage loginPage =
                new LoginPage(driver);

        UserProfilePage profile =
                new UserProfilePage(driver);


        // =========================
        // Read User Data
        // =========================

        JSONObject user =
                Utils.readJSONdata(
                        "./src/test/resources/Users.json"
                );

        String oldEmail =
                user.get("Email").toString();

        String password =
                user.get("Password").toString();


        // =========================
        // STEP 1 - Login
        // =========================

        loginPage.doLogin(
                oldEmail,
                password
        );

        DashboardPage dashboard =
                new DashboardPage(driver);

        Assert.assertTrue(
                dashboard.isAddCostDisplayed(),
                "Login failed. Dashboard did not load."
        );


        // =========================
        // STEP 2 - Generate New Email
        // =========================

        String newEmail =
                "testermeherin"
                        + Utils.generateRandomNumber(1000, 9999)
                        + "@gmail.com";


        System.out.println(
                "Old Email: " + oldEmail
        );

        System.out.println(
                "New Email: " + newEmail
        );


        // =========================
        // STEP 3 - Update Email
        // =========================

        profile.updateUserEmail(
                newEmail
        );


        // =========================
        // STEP 4 - Verify Email
        // =========================

        Assert.assertEquals(
                profile.getCurrentEmail(),
                newEmail,
                "Email was not updated successfully."
        );


        // =========================
        // STEP 5 - Logout
        // =========================

        loginPage.logout();


        // =========================
        // STEP 6 - Login With New Email
        // =========================

        loginPage.doLogin(
                newEmail,
                password
        );

        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login with updated email failed."
        );


        // =========================
        // STEP 7 - Logout
        // =========================

        loginPage.logout();


        // =========================
        // STEP 8 - Login With Old Email
        // =========================

        loginPage.doLogin(
                oldEmail,
                password
        );


        // =========================
        // STEP 9 - Verify Old Email
        // =========================

        Assert.assertTrue(
                loginPage.isLoginFailed(),
                "Old email should not be able to login."
        );
    }
}