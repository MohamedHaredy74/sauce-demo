package pages;

import engin.ActionsBot;
import io.qameta.allure.Step;
import dataModels.AccountData;
import dataModels.AddressData;
import org.openqa.selenium.By;

public class SignUpPage {
    private String URL="https://automationexercise.com/signup";
    private ActionsBot actionsBot;


    public SignUpPage(ActionsBot actionsBot) {
        this.actionsBot = actionsBot;
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
        actionsBot.getBrowserAction().navigateTo(URL);
        return this;
    }
    @Step("Validate that register form is open")
    public void validateThatRegisterFormIsOpen(){
        actionsBot.getAssertionAction().validateElementIsDisplayed(accountInformationHeader);

    }

    @Step("Fill account information with valid data")
    public SignUpPage  fillAccountInfo(AccountData accountData) {
        actionsBot.getElementAction().click(selectTitleRadioButton(accountData.title()));
        actionsBot.getElementAction().type(passwordInput,accountData.password());
        selectBirthDate(accountData.day(), accountData.month(), accountData.year());
        return this;
    }
    @Step("Fill address information with valid data and submit")
    public AccountCreatedPage fillAddressInfoAndSubmit(AddressData addressData) {
        actionsBot.getElementAction().type(firstNameInput,addressData.firstName());
        actionsBot.getElementAction().type(lastNameInput,addressData.lastName());
        actionsBot.getElementAction().type(companyInput,addressData.company());
        actionsBot.getElementAction().type(address1Input,addressData.address1());
        actionsBot.getElementAction().type(address2Input,addressData.address2());
        actionsBot.getElementAction().selectByVisibleText(countrySelect,addressData.country());
        actionsBot.getElementAction().type(stateInput,addressData.state());
        actionsBot.getElementAction().type(cityInput,addressData.city());
        actionsBot.getElementAction().type(zipcodeInput,addressData.zipCode());
        actionsBot.getElementAction().type(mobileNumberInput,addressData.mobileNumber());
        actionsBot.getElementAction().click(createAccountButton);
        return new AccountCreatedPage(actionsBot);
    }

    @Step("Select birth date: {day}-{month}-{year}")
    private void selectBirthDate(String day, String month, String year) {
        actionsBot.getElementAction().selectByVisibleText(birthDaySelect, day);
        actionsBot.getElementAction().selectByVisibleText(birthMonthSelect, month);
        actionsBot.getElementAction().selectByVisibleText(birthYearSelect, year);

    }



}
