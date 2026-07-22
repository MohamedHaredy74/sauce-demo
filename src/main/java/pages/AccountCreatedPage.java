package pages;

import engin.ActionsBot;
import org.openqa.selenium.By;

public class AccountCreatedPage {

    ActionsBot bot;
    public AccountCreatedPage(ActionsBot bot){
        this.bot=bot;
    }

    //locators
    By continueButton= By.xpath("//a[@data-qa='continue-button']");

    public HomePage clickContinueButton(){
        bot.click(continueButton);
        return new HomePage(bot);
    }

    public void assertPageTitle() {
        bot.assertEqual(bot.getTitle(),"Automation Exercise - Account Created");
    }
}
