package steps;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import util.Init;
import util.TestProperties;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    public static Properties properties = TestProperties.getInstance().getProperties();
    protected static String baseUrl;

    @Before
    public void beforeEach() {
        switch (properties.getProperty("browser2")) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                Init.setDriver(new FirefoxDriver());
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                Init.setDriver(new ChromeDriver());
                break;
            case "explorer":
                System.setProperty("webdriver.ie.driver", properties.getProperty("webdriver.ie.driver"));
                Init.setDriver(new InternetExplorerDriver());
                break;
        }
        baseUrl = properties.getProperty("app.url");
        Init.getDriver().get(baseUrl);
        Init.getDriver().manage().window().maximize();
        Init.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @After
    public void close(){
        Init.getDriver().close();
    }
}