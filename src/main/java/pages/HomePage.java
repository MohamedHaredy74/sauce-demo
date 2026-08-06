package pages;

import engin.ActionsBot;
import org.openqa.selenium.By;

public class HomePage {

    private String url="https://automationexercise.com/";
    ActionsBot bot;

    //locators
    By loginLink= By.xpath("//a[@href='/login']");


    public HomePage(ActionsBot bot){
        this.bot=bot;
    }

    public  HomePage navigate(){
        bot.navigateTo(url);
        return this;
    }

    public LoginPage
    clickLoginLink(){
        bot.click(loginLink);
        return new LoginPage(bot);
    }


}
