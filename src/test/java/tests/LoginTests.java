package tests;

import models.LoginData;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.JsonReader;

public class LoginTests extends TestCase {



    @Test
     void loginWithValidData()
    {
        LoginData loginData =
                JsonReader.read(
                        "validLogin.json",
                        LoginData.class);

        new LoginPage(bot)
                .navigate()
                .login(loginData);



    }


}
