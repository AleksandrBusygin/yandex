package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SecondMarketPage extends BasePageObject {

    @FindBy(xpath = "//a[@class='_2qvOOvezty _2x2zBaVN-3 _9qbcyI_fyS']")
    List<WebElement> items;

    public void chooseItem(String name){
        chooseElement(items, name);
    }
}