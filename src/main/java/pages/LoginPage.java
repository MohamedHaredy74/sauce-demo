package pages;

import engin.ActionsBot;
import org.openqa.selenium.By;

public class LoginPage {

    private  String url="https://automationexercise.com/login";
    ActionsBot bot;
    public LoginPage(ActionsBot bot){
        this.bot=bot;
    }

    //locators
    By signUpNameInput= By.xpath("//form[@action='/signup']/input[@name='name']");
    By signUpEmailInput= By.xpath("//form[@action='/signup']/input[@name='email']");
    By signUpButton= By.xpath("//form[@action='/signup']/button");



    public LoginPage navigate(){
        bot.navigateTo(url);
        return this;
    }







}
