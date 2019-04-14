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

    public void setPrice(String from, String to) {
        scrollToElement(minPrice);
        fillForm(from, minPrice);
        fillForm(to,maxPrice);
    }

    public void chooseManufacturer(String firstName, String secondName){
        chooseElement(manufacturers,firstName);
        chooseElement(manufacturers,secondName);
    }

    public void checkCountOfProducts(){
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

    public void checkProduct(String firstName, String secondName){
        Assert.assertTrue("Производители [$s] и [$d] указаны не для всех выбранных товаров",productExist(firstName,secondName));
    }

}