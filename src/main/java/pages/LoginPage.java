package pages;

import engin.ActionsBot;
import models.LoginData;
import models.PreSignUpData;
import org.openqa.selenium.By;
import org.openqa.selenium.bidi.log.Log;

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




    public LoginPage navigate(){
        bot.navigateTo(URL);
        return this;
    }
    public SignUpPage preSignUp(PreSignUpData data)
    {
        bot.type(signUpNameInput, data.name());
        bot.type(signUpEmailInput,data.email());
        bot.click(signUpButton);
        return new SignUpPage(bot);
    }



    public void login(LoginData loginData){
        bot.type(loginEmailInput,loginData.email());
        bot.type(loginPasswordInput, loginData.password());
        bot.click(loginButton);

    }






}
