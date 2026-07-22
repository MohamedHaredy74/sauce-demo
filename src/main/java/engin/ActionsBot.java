package engin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

public class ActionsBot {
    public Wait<WebDriver> wait;
    public ActionsBot(Wait<WebDriver> wait) {
        this.wait = wait;
    }

    public void type(By locator, String text) {
        wait.until(d -> {
            System.out.println("Typing text: " + text + " into element located by: " + locator);
            d.findElement(locator).sendKeys(text);
            System.out.println("Typing was successful.");
            return true;
        });
    }

    public void typeAndEnter(By locator, String text) {
        wait.until(d -> {
            System.out.println("Typing text: " + text + " into element located by: " + locator + " and pressing Enter");
            d.findElement(locator).sendKeys(text, Keys.ENTER);
            System.out.println("Typing and Enter was successful.");
            return true;
        });
    }

    public void click(By locator) {
        wait.until(d -> {
            System.out.println("Clicking on element located by: " + locator);
            d.findElement(locator).click();
            System.out.println("Click was successful.");
            return true;
        });
    }

    // create navigation methods
    public void navigateTo(String url) {
        wait.until(d -> {
            System.out.println("Navigating to URL: " + url);
            d.get(url);
            System.out.println("Navigation was successful.");
            return true;
        });
    }
    // create a methode to assert equal

    public ActionsBot assertEqual(String actual, String expected) {
        wait.until(d -> {
            System.out.println("Asserting that actual value: " + actual + " is equal to expected value: " + expected);
            Assert.assertEquals(actual, expected);
            System.out.println("Assertion was successful.");
            return true;
        });
        return this;
    }


    public ActionsBot assertTrue(boolean condition, String message) {
        wait.until(d -> {
            System.out.println("Asserting that condition is true."+condition );
            Assert.assertTrue(condition, message);
            System.out.println("Assertion was successful.");
            return true;
        });
        return this;
    }


    public String getTitle() {
        return wait.until(d -> {
            String title = d.getTitle();
            System.out.println("Retrieved page title: " + title);
            return title;
        });
    }

    public ActionsBot selectByVisibleText(By locator, String text) {
        wait.until(d -> {
            System.out.println("Selecting option with value: " + text + " from dropdown located by: " + locator);
            Select select = new Select(d.findElement(locator));
            select.selectByValue(text);
            System.out.println("Selection was successful.");
            return true;
        });
        return this;
    }


}
