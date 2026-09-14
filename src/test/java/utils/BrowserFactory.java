package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class BrowserFactory {
    private WebDriver driver;

    public WebDriver createDriver(String browserType)  {
        if (driver != null) {
            throw new IllegalStateException("A driver instance already exists. Please quit the existing driver before creating a new one.");
        }

        return switch (browserType.toLowerCase()) {
            case "chrome" -> {

                    driver = new ChromeDriver(getChromeOptions());

                yield driver;
            }
            case "firefox" -> {
                driver = new FirefoxDriver(getFirefoxOptions());
                yield driver;
            }
            case "edge" -> {
                driver = new EdgeDriver(getEdgeOptions());
                yield driver;
            }
            default -> throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        };
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        return chromeOptions;
    }

    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        return options;
    }

    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920,1080");
        return options;
    }
}
