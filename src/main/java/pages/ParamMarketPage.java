package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ParamMarketPage extends BasePageObject {

    @FindBy(xpath = "//input[@id='glpricefrom']")
    WebElement minPrice;

    @FindBy(xpath = "//input[@id='glpriceto']")
    WebElement maxPrice;

    @FindBy(xpath = "//span[@class='NVoaOvqe58']")
    List<WebElement> manufacturers;

    @FindBy(xpath = "//div[@data-id]")
    List<WebElement> resultModels;

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

    public void checkCountOfProducts() throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertTrue("Количество элементов не равно 12", resultModels.size() == 12);
    }

    public boolean productExist(String manufacturerName, String secondManufacturerName){
        for (WebElement item: resultModels){
            if (isElementPresent(item) && ((item.getText().equalsIgnoreCase(manufacturerName)) || (item.getText().equalsIgnoreCase(secondManufacturerName))))
            {
                return true;
            }
        }
        return false;
    }
    public boolean priceExist(String from, String to){
        for (WebElement item: price){
            if (isElementPresent(item) && ((Integer.valueOf(item.getText()) >= Integer.valueOf(from)) && (Integer.valueOf(item.getText()) <= Integer.valueOf(to))))
            {
                return true;
            }
        }
        return false;
    }

    public void checkProduct(String firstName, String secondName){
        Assert.assertTrue("Производители [$s] и [$d] указаны не для всех выбранных товаров",productExist(firstName,secondName));
    }

    public void checkPrice(String from, String to){
        Assert.assertTrue("Товары не находятся в выбранном диапазоне цен",priceExist(from,to));
    }

}