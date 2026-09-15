package pages;

import engin.ActionsBot;
import io.qameta.allure.Step;
import dataModels.LoginData;
import dataModels.PreSignUpData;
import org.openqa.selenium.By;


public class LoginPage {

    private final  String URL ="https://automationexercise.com/login";
    private ActionsBot actionsBot;

    public LoginPage(ActionsBot actionsBot){

        this.actionsBot=actionsBot;
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
        actionsBot.getBrowserAction().navigateTo(URL);
        return this;
    }

    @Step("Pre sign up with name and email ")
    public SignUpPage preSignUp(PreSignUpData data)
    {
        actionsBot.getElementAction().type(signUpNameInput, data.name());
        actionsBot.getElementAction().type(signUpEmailInput,"user" + System.currentTimeMillis() + "@test.com");
        actionsBot.getElementAction().click(signUpButton);
        return new SignUpPage(actionsBot);
    }

    @Step("Login with valid email and valid password")
    public HomePage login(LoginData loginData){
        actionsBot.getElementAction().type(loginEmailInput,loginData.email());
        actionsBot.getElementAction().type(loginPasswordInput, loginData.password());
        actionsBot.getElementAction().click(loginButton);
        return new HomePage(actionsBot);

    }






}
