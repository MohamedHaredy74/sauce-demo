package pages;

import engin.ActionsBot;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class AccountCreatedPage {

    private ActionsBot actionsBot;

    public AccountCreatedPage(ActionsBot actionsBot){
        this.actionsBot= actionsBot;
    }

    //locators
    By accountCreatedHeader= By.xpath("//h2[contains(.,'Account Created!')]");
    By continueButton= By.xpath("//a[@data-qa='continue-button']");

    @Step("Click on continue button")
    public HomePage clickContinueButton(){
        actionsBot.getElementAction().click(continueButton);
        return new HomePage(actionsBot);
    }

    @Step("Validate that account created success message is displayed")
    public void validateAccountCreatedSuccessMessage (){
        actionsBot.getAssertionAction().validateTheTextOfElement(accountCreatedHeader,"ACCOUNT CREATED!");

    }
}
