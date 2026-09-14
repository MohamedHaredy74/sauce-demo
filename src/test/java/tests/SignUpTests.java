package tests;


import io.qameta.allure.Description;
import dataModels.RegisterData;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SignUpPage;
import utils.JsonReader;


public class SignUpTests extends TestCase {


    @Description("Test to validate successful registration with valid data")
    @Test
    void validateSuccessRegisterWithValidData() {
        RegisterData registerData =
                JsonReader.read(
                        "validRegister.json",
                        RegisterData.class);

        new LoginPage(actionsBot)
                .navigate()
                .preSignUp(registerData.preSignUp())
                .validateThatRegisterFormIsOpen();
        new SignUpPage(actionsBot)
                .fillAccountInfo(registerData.account())
                .fillAddressInfoAndSubmit(registerData.address())
                .validateAccountCreatedSuccessMessage();
    }


}













