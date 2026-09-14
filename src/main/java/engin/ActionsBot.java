package engin;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import utils.LogUtils;

public class ActionsBot {
    public Wait<WebDriver> wait;
    public ActionsBot(Wait<WebDriver> wait) {
        this.wait = wait;
    }

    @Step("Typing text: {text} into element located by: {locator}")
    public void type(By locator, String text) {
        wait.until(d -> {
            LogUtils.info("Typing text: " + text + " into element located by: " + locator);
            d.findElement(locator).sendKeys(text);
            LogUtils.info("Typing was successful.");
            return true;
        });
    }

    @Step("Typing text: {text} into element located by: {locator} and pressing Enter")
    public void typeAndEnter(By locator, String text) {
        wait.until(d -> {
            LogUtils.info("Typing text: " + text + " into element located by: " + locator + " and pressing Enter");
            d.findElement(locator).sendKeys(text, Keys.ENTER);
            LogUtils.info("Typing and Enter was successful.");
            return true;
        });
    }

    @Step("Clicking on element located by: {locator}")
    public void click(By locator) {
        wait.until(d -> {
            LogUtils.info("Clicking on element located by: " + locator);
            d.findElement(locator).click();
            LogUtils.info("Click was successful.");
            return true;
        });
    }

    @Step("Navigating to URL: {url}")
    public void navigateTo(String url) {
        wait.until(d -> {
            LogUtils.info("Navigating to URL: " + url);
            d.get(url);
            LogUtils.info("Navigation was successful.");
            return true;
        });
    }

    //@Step("Asserting that actual value: {actual} is equal to expected value: {expected}")
   /* public ActionsBot assertEqual(String actual, String expected) {
        wait.until( d -> {
           // System.out.println("Asserting that actual value: " + actual + " is equal to expected value: " + expected);
            LogUtils.info("Asserting that actual value: " + actual + " is equal to expected value: " + expected);
            Assert.assertEquals(actual, expected);
            //System.out.println("Assertion was successful.");
            LogUtils.info("Assertion was successful.");
            return true;
        });
        return this;
    }
    */



    //@Step("Asserting that condition is true with message: {message}")
   /* public ActionsBot assertTrue(boolean condition, String message) {
        wait.until(d -> {
            //System.out.println("Asserting that condition is true."+condition );
            LogUtils.info("Asserting that condition is true."+condition );
            Assert.assertTrue(condition, message);
            //System.out.println("Assertion was successful.");
            LogUtils.info("Assertion was successful.");
            return true;
        });
        return this;
    }
    */


   @Step("Validating that element located by: {locator} is displayed")
    public ActionsBot validateElementIsDisplayed(By locator) {
        wait.until(d -> {
            LogUtils.info("Asserting that element located by: "+locator+" is displayed");
            Assert.assertTrue(d.findElement(locator).isDisplayed());
            LogUtils.info("Assertion was successful.");
            return true;
        });
        return this;
    }
    @Step("Validating that the text of element located by: {locator} is equal to expected text: {expectedText}")
    public ActionsBot validateTheTextOfElement(By locator, String expectedText) {
        wait.until(d -> {
            String actualText = d.findElement(locator).getText();
            LogUtils.info("Validating that the text of element located by: " + locator + " is equal to expected text: " + expectedText);
            Assert.assertEquals(actualText, expectedText);
            LogUtils.info("Validation was successful.");
            return true;
        });
        return this;
    }


    @Step("Retrieving the title of the current page")
    public String getTitle() {
        return wait.until(d -> {
            String title = d.getTitle();
            LogUtils.info("Retrieved page title: " + title);
            return title;
        });
    }


    @Step("Selecting option with visible text: {text} from dropdown located by: {locator}")
    public ActionsBot selectByVisibleText(By locator, String text) {
        wait.until(d -> {
            LogUtils.info("Selecting Element  with value: " + text + " from dropdown located by: " + locator);
            Select select = new Select(d.findElement(locator));
            select.selectByVisibleText(text);
            LogUtils.info("Selection was successful.");
            return true;
        });
        return this;
    }


}
