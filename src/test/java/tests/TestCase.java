package tests;
import engin.*;
import io.qameta.allure.Step;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.*;
import utils.BrowserFactory;
import utils.ExcecutionListener;
import utils.LogUtils;
import utils.ScreenshotListener;
import utils.TestLogCaptureAppender;

import java.time.Duration;
@Listeners({ExcecutionListener.class, ScreenshotListener.class})
public abstract class TestCase {

    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    protected ActionsBot actionsBot;

    protected BrowserFactory browserFactory;

    @Step("Setting up the test environment with browser type: {browserType}")
    @BeforeMethod
    @Parameters({"browserType" })
    public void setUp(@Optional("chrome") String browserType) {
        TestLogCaptureAppender.clear();
        LogUtils.info("Setting up the test environment with browser type: " + browserType);
        browserFactory=new BrowserFactory();
        driver = browserFactory.createDriver(browserType);
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NotFoundException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);

        actionsBot= new ActionsBot(wait);
    }

    @Step("Tearing down the test environment and quitting the browser")
    @AfterMethod
    public void tearDown() {
        LogUtils.info("Tearing down the test environment and quitting the browser");
        browserFactory.quitDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}