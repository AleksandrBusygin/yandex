package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Init;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ParamMarketPage extends BasePageObject {

    @FindBy(xpath = "//input[@id='glpricefrom']")
    WebElement minPrice;

    @FindBy(xpath = "//input[@id='glpriceto']")
    WebElement maxPrice;

    @FindBy(xpath = "//span[@class='NVoaOvqe58']")
    List<WebElement> manufacturers;

    @FindBy(xpath = "//div[@data-id]")
    List<WebElement> resultModels;

    @FindBy(xpath = "//div[@data-id]//a[@title]")
    List<WebElement> nameModels;


    @FindBy(xpath = "//div[@class='price']")
    List<WebElement> price;

    public void setPrice(String from, String to) {
        scrollToElement(minPrice);
        fillForm(from, minPrice);
        fillForm(to,maxPrice);
    }

    public void chooseManufacturer(String firstName, String secondName){
        chooseElement(manufacturers,firstName);
        chooseElement(manufacturers,secondName);
    }

    public void waitPageLoaded(){
        WebDriverWait wait = new WebDriverWait(Init.getDriver(), 30);
        wait.ignoring(NoSuchElementException.class).until((ExpectedCondition<Boolean>) driver ->
                !isPresent( By.xpath("//*[@class='helpers-params loading']")));
    }

    public boolean isPresent(By locator){
        try {
            Init.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            return Init.getDriver().findElement(locator).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }finally {
            Init.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }

    public void checkCountOfProducts(int count){
//        waitPageLoaded();
        Assert.assertTrue("Количество элементов не равно ожидаемому - " + count, resultModels.size() == count);
    }

    public boolean productExist(String manufacturerName, String secondManufacturerName){
        for (WebElement item: resultModels){
            scrollToElement(item);
            if (isElementPresent(item) && ((item.getText().contains(manufacturerName)) || (item.getText().contains(secondManufacturerName))))
            {
                return true;
            }
        }
        return false;
    }
    public boolean priceExist(String from, String to){
        for (WebElement item: price){
            scrollToElement(item);
            if (isElementPresent(item) && ((Integer.valueOf(item.getText().replaceAll("\\D","")) >= Integer.valueOf(from)) && (Integer.valueOf(item.getText().replaceAll("\\D","")) <= Integer.valueOf(to))))
            {
                return true;
            }
        }
        return false;
    }

    public void checkProduct(String firstName, String secondName){
        Assert.assertTrue("Выбранные производители указаны не для всех товаров",productExist(firstName,secondName));
    }

    public void checkPrice(String from, String to){
        Assert.assertTrue("Товары не находятся в выбранном диапазоне цен",priceExist(from,to));
    }

}