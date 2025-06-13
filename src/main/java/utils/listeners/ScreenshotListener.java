package utils.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.config.BaseTest;

import java.io.ByteArrayInputStream;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            WebDriver driver = (WebDriver) iTestResult.getInstance().getClass().getMethod("getDriver").invoke(iTestResult.getInstance());

            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot), "png");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
