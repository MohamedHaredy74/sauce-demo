package engin;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import utils.LogUtils;

public class ElementActions {

    private final  Wait<WebDriver> wait;
    public ElementActions(Wait<WebDriver> wait) {
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
    @Step("Selecting option with visible text: {text} from dropdown located by: {locator}")
    public ElementActions selectByVisibleText(By locator, String text) {
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
