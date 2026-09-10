package pages;

import engin.ActionsBot;
import io.qameta.allure.Step;
import models.LoginData;
import models.PreSignUpData;
import org.openqa.selenium.By;


public class LoginPage {

    private final  String URL ="https://automationexercise.com/login";
    ActionsBot bot;
    public LoginPage(ActionsBot bot  ){
        this.bot=bot;
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
        bot.navigateTo(URL);
        return this;
    }

    @Step("Pre sign up with:{data} ")
    public SignUpPage preSignUp(PreSignUpData data)
    {
        bot.type(signUpNameInput, data.name());
        bot.type(signUpEmailInput,"user" + System.currentTimeMillis() + "@test.com");
        bot.click(signUpButton);
        return new SignUpPage(bot);
    }


    @Step("Login with: {loginData}")
    public void login(LoginData loginData){
        bot.type(loginEmailInput,loginData.email());
        bot.type(loginPasswordInput, loginData.password());
        bot.click(loginButton);

    }






}
