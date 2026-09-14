package tests;

import io.qameta.allure.Description;
import dataModels.LoginData;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.JsonReader;

public class LoginTests extends TestCase {

    @Description("Test to validate successful login with valid credentials")
    @Test
     void loginWithValidData()
    {
        LoginData loginData =
                JsonReader.read(
                        "validLogin.json",
                        LoginData.class);

        new LoginPage(actionsBot)
                .navigate()
                .login(loginData);



    }


}
