package tests;


import org.testng.annotations.Test;
import pages.HomePage;

public class SignUpTests extends TestCase {


    @Test
    void validateSuccessRegisterWithValidData() {
        new HomePage(bot)
                .navigate()
                .clickLoginLink()
                .preSignUp("mohammeeddd", "mohammedddd@test.com")
                .fillAccountInfo("Mr", "P@ssw0rd", "1", "October", "1999")
                .fillAddressInfoAndSubmit("mohammmedd33", "ashraff", "TOT", "Cairo", "Nasr City", "Singapore", "cairo", "giza", "123456", "0123456789")
                .assertPageTitle();
    }

}













