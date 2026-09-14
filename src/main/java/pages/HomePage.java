package pages;

import engin.ActionsBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage {

    private String url="https://automationexercise.com/";
    private ActionsBot actionsBot;

    public HomePage(ActionsBot actionsBot){
        this.actionsBot= actionsBot;
    }


    //locators
    By loginLink= By.xpath("//a[@href='/login']");


    @Step("Navigate to home page ")
    public  HomePage navigate(){
        actionsBot.getBrowserAction().navigateTo(url);
        return this;
    }

    @Step("Click on login link")
    public LoginPage clickLoginLink(){
        actionsBot.getElementAction().click(loginLink);
        return new LoginPage(actionsBot);
    }


}
