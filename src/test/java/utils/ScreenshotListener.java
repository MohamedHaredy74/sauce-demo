package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import tests.TestCase;

import java.io.ByteArrayInputStream;

public class ScreenshotListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!method.isTestMethod() || testResult.getStatus() != ITestResult.FAILURE) {
            return;
        }

        Object testClass = testResult.getInstance();

        Allure.addAttachment("Test Logs", "text/plain", TestLogCaptureAppender.getLogs(), ".log");

        if (!(testClass instanceof TestCase testCase)) {
            return;
        }

        WebDriver driver = testCase.getDriver();
        if (driver == null) {
            return;
        }

        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            LogUtils.error("Failed to attach screenshot to Allure: " + e.getMessage());
        }
    }
}
