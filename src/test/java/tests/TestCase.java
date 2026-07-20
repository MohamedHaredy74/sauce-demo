package tests;
import engin.ActionsBot;
import engin.BrowserFactory;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public abstract class TestCase {

    WebDriver driver;
    public Wait<WebDriver> wait;
    public ActionsBot bot;
    private BrowserFactory browserFactory;

    @BeforeMethod
    @Parameters({"browserType" })
    public void setUp(@Optional("chrome") String browserType) {
        browserFactory = new BrowserFactory();
        driver = browserFactory.createDriver(browserType);
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NotFoundException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(StaleElementReferenceException.class);

        bot = new ActionsBot(wait);
    }

    @AfterMethod
    public void tearDown() {
        browserFactory.quitDriver();
    }
}