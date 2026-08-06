package pages;

import engin.ActionsBot;
import models.AccountData;
import models.AddressData;
import org.openqa.selenium.By;

public class SignUpPage {
    private String URL="https://automationexercise.com/signup";
    ActionsBot bot;



    //locators
    private By selectTitleRadioButton(String title) {
       return By.xpath("//input[@type='radio'][@value='"+title+"']");
    }
    By accountInformationHeader = By.xpath("//h2[contains(.,'Enter Account Information')]");
    By passwordInput= By.xpath("//input[@name='password']");
    By birthDaySelect= By.xpath("//select[@id='days']");
    By birthMonthSelect= By.xpath("//select[@id='months']");
    By birthYearSelect= By.xpath("//select[@id='years']");
    //address info locators
    By firstNameInput= By.xpath("//input[@id='first_name']");
    By lastNameInput= By.xpath("//input[@id='last_name']");
    By companyInput= By.xpath("//input[@id='company']");
    By address1Input= By.xpath("//input[@id='address1']");
    By address2Input= By.xpath("//input[@id='address2']");
    By countrySelect= By.xpath("//select[@id='country']");
    By stateInput= By.xpath("//input[@id='state']");
    By cityInput= By.xpath("//input[@id='city']");
    By zipcodeInput= By.xpath("//input[@id='zipcode']");
    By mobileNumberInput= By.xpath("//input[@id='mobile_number']");
    By createAccountButton= By.xpath("//button[@type='submit'][contains(.,'Create Account')]");

    public SignUpPage(ActionsBot bot)
    {
        this.bot=bot;
        //Islam sends his greetings
    }


    public SignUpPage navigate() {
        bot.navigateTo(URL);
        return this;
    }

    public void validateThatRegisterFormIsOpen(){
      bot.validateElementIsDisplayed(accountInformationHeader);

    }

    public SignUpPage  fillAccountInfo(AccountData accountData) {
        bot.click(selectTitleRadioButton(accountData.title()));
        bot.type(passwordInput,accountData.password());
        selectBirthDate(accountData.day(), accountData.month(), accountData.year());
        return this;
    }
    public AccountCreatedPage fillAddressInfoAndSubmit(AddressData addressData) {
        bot.type(firstNameInput,addressData.firstName());
        bot.type(lastNameInput,addressData.lastName());
        bot.type(companyInput,addressData.company());
        bot.type(address1Input,addressData.address1());
        bot.type(address2Input,addressData.address2());
        bot.selectByVisibleText(countrySelect,addressData.country());
        bot.type(stateInput,addressData.state());
        bot.type(cityInput,addressData.city());
        bot.type(zipcodeInput,addressData.zipCode());
        bot.type(mobileNumberInput,addressData.mobileNumber());
        bot.click(createAccountButton);
        return new AccountCreatedPage(bot);
    }

    private void selectBirthDate(String day, String month, String year) {
        bot.selectByVisibleText(birthDaySelect, day);
        bot.selectByVisibleText(birthMonthSelect, month);
        bot.selectByVisibleText(birthYearSelect, year);

    }



}
