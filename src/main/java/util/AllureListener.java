package util;

import io.qameta.allure.Attachment;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AllureListener {

    @Override
    public void testFailure(final Failure failure){
        takeScreenshot();
        super.testFailure(failure);
    }
    @Attachment(type = "image/png", value = "Screenshot")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) Init.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
