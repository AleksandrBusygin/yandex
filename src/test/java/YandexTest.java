import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pages.MainPage;
import pages.MarketPage;
import pages.ParamMarketPage;
import pages.SecondMarketPage;
import util.Init;
import util.TestProperties;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class YandexTest {

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


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Компьютерная техника", "Ноутбуки", "0", "30000", "HP","Lenovo", 48, 1},
                { "Компьютерная техника", "Планшеты", "20000","25000","Apple","HP", 5, 2},
                });
    }

    @Parameterized.Parameter
    public String category;
    @Parameterized.Parameter(1)
    public String item;
    @Parameterized.Parameter(2)
    public String from;
    @Parameterized.Parameter(3)
    public String to;
    @Parameterized.Parameter(4)
    public String firstManufacturer;
    @Parameterized.Parameter(5)
    public String secondManufacturer;
    @Parameterized.Parameter(6)
    public int countOfElements;
    @Parameterized.Parameter(7)
    public int number;

    public void checkStep(int num){
        ParamMarketPage paramMarketPage = new ParamMarketPage();
        if (num == 1){
            paramMarketPage.checkProduct(firstManufacturer,secondManufacturer);
        }
        if (num == 2){
            paramMarketPage.checkPrice(from,to);
        }
    }

    @Test
    public void test() {
        MainPage mainPage = new MainPage();
        MarketPage marketPage = new MarketPage();
        SecondMarketPage secondMarketPage = new SecondMarketPage();
        ParamMarketPage paramMarketPage = new ParamMarketPage();

        mainPage.chooseService();
        marketPage.chooseCategory(category);
        secondMarketPage.chooseItem(item);
        paramMarketPage.setPrice(from,to);
        paramMarketPage.chooseManufacturer(firstManufacturer, secondManufacturer);
        paramMarketPage.checkCountOfProducts(countOfElements);
        checkStep(number);

    }
}