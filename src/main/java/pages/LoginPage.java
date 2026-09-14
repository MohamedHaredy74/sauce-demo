package pages;

import engin.AssertionBot;
import engin.BrowserBot;
import engin.ElementBot;
import io.qameta.allure.Step;
import models.LoginData;
import models.PreSignUpData;
import org.openqa.selenium.By;


public class LoginPage {

    private final  String URL ="https://automationexercise.com/login";

    ElementBot elementBot;
    BrowserBot browserBot;
    AssertionBot assertionBot;
    public LoginPage(ElementBot elementBot, BrowserBot browserBot,AssertionBot assertionBot){

        this.elementBot=elementBot;
        this.browserBot= browserBot;
        this.assertionBot=assertionBot;

    }

    //locators
    By signUpNameInput= By.xpath("//form[@action='/signup']/input[@name='name']");
    By signUpEmailInput= By.xpath("//form[@action='/signup']/input[@name='email']");
    By signUpButton= By.xpath("//form[@action='/signup']/button");
    By loginEmailInput=By.xpath("//form[@action='/login']/input[@name='email']");
    By loginPasswordInput=By.xpath("//form[@action='/login']/input[@name='password']");
    By loginButton=By.xpath("//form[@action='/login']/button");



    @Step("Navigate to login page")
    public LoginPage navigate(){
        browserBot.navigateTo(URL);
        return this;
    }

    @Step("Pre sign up with:{data} ")
    public SignUpPage preSignUp(PreSignUpData data)
    {
        elementBot.type(signUpNameInput, data.name());
        elementBot.type(signUpEmailInput,"user" + System.currentTimeMillis() + "@test.com");
        elementBot.click(signUpButton);
        return new SignUpPage(elementBot,browserBot,assertionBot);
    }


    @Step("Login with: {loginData}")
    public void login(LoginData loginData){
        elementBot.type(loginEmailInput,loginData.email());
        elementBot.type(loginPasswordInput, loginData.password());
        elementBot.click(loginButton);

    }






}
