package pages;

import engin.ActionsBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {

    private String url="https://automationexercise.com/";
    ActionsBot bot;
    //locators
    By loginLink= By.xpath("//a[@href='/login']");


    public HomePage(ActionsBot bot){
        this.bot=bot;
    }

    @Step("Navigate to home page ")
    public  HomePage navigate(){
        bot.navigateTo(url);
        return this;
    }

    @Step("Click on login link")
    public LoginPage clickLoginLink(){
        bot.click(loginLink);
        return new LoginPage(bot);
    }


}
