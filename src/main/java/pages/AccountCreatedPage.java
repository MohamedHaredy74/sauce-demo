package pages;

import engin.ActionsBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AccountCreatedPage {

    ActionsBot bot;


    public AccountCreatedPage(ActionsBot bot){
        this.bot=bot;
    }

    //locators
    By accountCreatedHeader= By.xpath("//h2[contains(.,'Account Created!')]");
    By continueButton= By.xpath("//a[@data-qa='continue-button']");

    @Step("Click on continue button")
    public HomePage clickContinueButton(){
        bot.click(continueButton);
        return new HomePage(bot);
    }

    @Step("Validate that account created success message is displayed")
    public void validateAccountCreatedSuccessMessage (){
      bot.validateTheTextOfElement(accountCreatedHeader,"ACCOUNT CREATED!");

    }
}
