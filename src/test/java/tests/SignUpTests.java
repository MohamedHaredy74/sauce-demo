package tests;


import org.testng.annotations.Test;
import pages.HomePage;

public class SignUpTests extends TestCase {


    @Test
    void validateSuccessRegisterWithValidData() {
        new HomePage(bot)
                .navigate()
                .clickLoginLink();
    }
}













