package pages;

import engin.ActionsBot;
import org.openqa.selenium.By;

public class SignUpPage {
    private String URL="https://automationexercise.com/signup";
    ActionsBot bot;



    //locators
    By titleMrRadioButton= By.xpath("//input[@type='radio'][@value='Mr']");
    By titleMrsRadioButton= By.xpath("//input[@type='radio'][@value='Mrs']");
    By nameInput= By.xpath("//input[@name='name']");
    By emailInput= By.xpath("//input[@name='email']");
    By passwordInput= By.xpath("//input[@name='password']");
    By birthDaySelect= By.xpath("//select[@id='days']");
    By birthMonthSelect= By.xpath("//select[@id='months']");
    By birthYearSelect= By.xpath("//select[@id='years']");



    By createAccountButton= By.xpath("//");

    public SignUpPage(ActionsBot bot)
    {
        this.bot=bot;
    }


    public SignUpPage navigate() {
        bot.navigateTo(URL);
        return this;
    }

    private void  fillAccountInfo(){

    }
    private void fillAddressInfo() {
    }

    public void createAccount() {
        fillAccountInfo();
        fillAddressInfo();
        bot.click(createAccountButton);
    }



    public void assertAccountPageUrl() {
    }
}
