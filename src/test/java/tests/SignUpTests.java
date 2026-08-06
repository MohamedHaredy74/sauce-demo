package tests;


import models.LoginData;
import models.RegisterData;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SignUpPage;
import utils.JsonReader;


public class SignUpTests extends TestCase {


    @Test
    void validateSuccessRegisterWithValidData() {
        RegisterData registerData =
                JsonReader.read(
                        "validRegister.json",
                        RegisterData.class);

        new LoginPage(bot)
                .navigate()
                .preSignUp(registerData.preSignUp())
                .validateThatRegisterFormIsOpen();
        new SignUpPage(bot)
                .fillAccountInfo(registerData.account())
                .fillAddressInfoAndSubmit(registerData.address());
    }


}













