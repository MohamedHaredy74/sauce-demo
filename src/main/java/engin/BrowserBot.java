package engin;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import utils.LogUtils;

public class BrowserBot {

    private final  Wait<WebDriver> wait;
    public BrowserBot (Wait<WebDriver> wait) {
        this.wait = wait;
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
    @Step("Retrieving the title of the current page")
    public String getTitle() {
        return wait.until(d -> {
            String title = d.getTitle();
            LogUtils.info("Retrieved page title: " + title);
            return title;
        });
    }


}
