package pages;

import engin.AssertionBot;
import engin.BrowserBot;
import engin.ElementBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {

    private String url="https://automationexercise.com/";

    ElementBot elementBot;
    BrowserBot browserBot;
    AssertionBot assertionBot;

    public HomePage(ElementBot elementBot, BrowserBot browserBot, AssertionBot assertionBot){
        this.assertionBot=assertionBot;
        this.browserBot=browserBot;
        this.elementBot=elementBot;
    }


    //locators
    By loginLink= By.xpath("//a[@href='/login']");


    @Step("Navigate to home page ")
    public  HomePage navigate(){
        browserBot.navigateTo(url);
        return this;
    }

    @Step("Click on login link")
    public LoginPage clickLoginLink(){
        elementBot.click(loginLink);
        return new LoginPage(elementBot,browserBot,assertionBot);
    }


}
