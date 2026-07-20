package tests;



import org.testng.annotations.*;

public class LoginTests extends TestCase {




    @Test
    public void testLogin() {
        // Implement your login test logic here
        bot.navigateTo("https://sauce-demo.myshopify.com/");
    }


}
