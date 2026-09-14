package pages;

import engin.AssertionBot;
import engin.BrowserBot;
import engin.ElementBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AccountCreatedPage {


    ElementBot elementBot;
    BrowserBot browserBot;
    AssertionBot assertionBot;


    public AccountCreatedPage(ElementBot elementBot,BrowserBot browserBot, AssertionBot assertionBot){
        this.elementBot=elementBot;
        this.browserBot=browserBot;
        this.assertionBot=assertionBot;


    }

    //locators
    By accountCreatedHeader= By.xpath("//h2[contains(.,'Account Created!')]");
    By continueButton= By.xpath("//a[@data-qa='continue-button']");

    @Step("Click on continue button")
    public HomePage clickContinueButton(){
        elementBot.click(continueButton);
        return new HomePage(elementBot,browserBot,assertionBot);
    }

    @Step("Validate that account created success message is displayed")
    public void validateAccountCreatedSuccessMessage (){
        assertionBot.validateTheTextOfElement(accountCreatedHeader,"ACCOUNT CREATED!");

    }
}
