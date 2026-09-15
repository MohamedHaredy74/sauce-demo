package engin;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import utils.LogUtils;

public class AssertionActions {

private final  Wait<WebDriver> wait;
public AssertionActions(Wait<WebDriver> wait) {
    this.wait = wait;
}



    @Step("Validating that element located by: {locator} is displayed")
    public AssertionActions validateElementIsDisplayed(By locator) {
        wait.until(d -> {
            LogUtils.info("Asserting that element located by: "+locator+" is displayed");
            Assert.assertTrue(d.findElement(locator).isDisplayed());
            LogUtils.info("Assertion was successful.");
            return true;
        });
        return this;
    }
    @Step("Validating that the text of element located by: {locator} is equal to expected text: {expectedText}")
    public AssertionActions validateTheTextOfElement(By locator, String expectedText) {
        wait.until(d -> {
            String actualText = d.findElement(locator).getText();
            LogUtils.info("Validating that the text of element located by: " + locator + " is equal to expected text: " + expectedText);
            Assert.assertEquals(actualText, expectedText);
            LogUtils.info("Validation was successful.");
            return true;
        });
        return this;
    }


    @Step("Asserting that page title is equal to: {expectedTitle}")
    public AssertionActions assertThePageTitle( String expectedTitle) {
        wait.until(d -> {
            LogUtils.info("Asserting that page title is equal to: " + expectedTitle);
            Assert.assertEquals(d.getTitle(), expectedTitle);
            LogUtils.info("Assertion was successful.");
            return true;
        });
        return this;
    }



}
