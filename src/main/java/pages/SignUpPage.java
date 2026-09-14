package pages;

import engin.AssertionBot;
import engin.BrowserBot;
import engin.ElementBot;
import io.qameta.allure.Step;
import models.AccountData;
import models.AddressData;
import org.openqa.selenium.By;

public class SignUpPage {
    private String URL="https://automationexercise.com/signup";

    ElementBot elementBot;
    BrowserBot browserBot;
    AssertionBot assertionBot;


    public SignUpPage(ElementBot elementBot, BrowserBot browserBot, AssertionBot assertionBot)
    {
        this.elementBot=elementBot;
        this.assertionBot=assertionBot;
        this.browserBot=browserBot;

    }



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



    @Step("Navigate to sign up page")
    public SignUpPage navigate() {
        browserBot.navigateTo(URL);
        return this;
    }
    @Step("Validate that register form is open")
    public void validateThatRegisterFormIsOpen(){
        assertionBot.validateElementIsDisplayed(accountInformationHeader);

    }

    @Step("Fill account information with data: {accountData}")
    public SignUpPage  fillAccountInfo(AccountData accountData) {
        elementBot.click(selectTitleRadioButton(accountData.title()));
        elementBot.type(passwordInput,accountData.password());
        selectBirthDate(accountData.day(), accountData.month(), accountData.year());
        return this;
    }
    @Step("Fill address information with data: {addressData} and submit")
    public AccountCreatedPage fillAddressInfoAndSubmit(AddressData addressData) {
        elementBot.type(firstNameInput,addressData.firstName());
        elementBot.type(lastNameInput,addressData.lastName());
        elementBot.type(companyInput,addressData.company());
        elementBot.type(address1Input,addressData.address1());
        elementBot.type(address2Input,addressData.address2());
        elementBot.selectByVisibleText(countrySelect,addressData.country());
        elementBot.type(stateInput,addressData.state());
        elementBot.type(cityInput,addressData.city());
        elementBot.type(zipcodeInput,addressData.zipCode());
        elementBot.type(mobileNumberInput,addressData.mobileNumber());
        elementBot.click(createAccountButton);
        return new AccountCreatedPage(elementBot,browserBot,assertionBot);
    }

    @Step("Select birth date: {day}-{month}-{year}")
    private void selectBirthDate(String day, String month, String year) {
        elementBot.selectByVisibleText(birthDaySelect, day);
        elementBot.selectByVisibleText(birthMonthSelect, month);
        elementBot.selectByVisibleText(birthYearSelect, year);

    }



}
