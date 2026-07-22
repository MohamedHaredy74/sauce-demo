package pages;

import engin.ActionsBot;
import org.openqa.selenium.By;

public class SignUpPage {
    private String URL="https://automationexercise.com/signup";
    ActionsBot bot;



    //locators
    private By selectTitleRadioButton(String title) {
       return By.xpath("//input[@type='radio'][@value='"+title+"']");
    }
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
    }


    public SignUpPage navigate() {
        bot.navigateTo(URL);
        return this;
    }

    public SignUpPage  fillAccountInfo(String title,String password, String day, String month, String year ) {
        bot.click(selectTitleRadioButton(title));
        bot.type(passwordInput,password);
        selectBirthDate(day,month,year);
        return this;
    }
    public AccountCreatedPage fillAddressInfoAndSubmit(String firstName, String lastName, String company, String address1, String address2, String country, String state, String city, String zipcode, String mobileNumber) {
        bot.type(firstNameInput,firstName);
        bot.type(lastNameInput,lastName);
        bot.type(companyInput,company);
        bot.type(address1Input,address1);
        bot.type(address2Input,address2);
        bot.selectByVisibleText(countrySelect,country);
        bot.type(stateInput,state);
        bot.type(cityInput,city);
        bot.type(zipcodeInput,zipcode);
        bot.type(mobileNumberInput,mobileNumber);
        bot.click(createAccountButton);
        return new AccountCreatedPage(bot);
    }

    public void selectBirthDate(String day, String month, String year) {
        bot.selectByVisibleText(birthDaySelect, day);
        bot.selectByVisibleText(birthMonthSelect, month);
        bot.selectByVisibleText(birthYearSelect, year);

    }



}
