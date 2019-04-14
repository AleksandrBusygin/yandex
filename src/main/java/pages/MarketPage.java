package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MarketPage extends BasePageObject {
    @FindBy(xpath = "//a[@class='link n-w-tab__control b-zone b-spy-events']")
    List<WebElement> categories;

    public void chooseCategory(String name){
       chooseElement(categories,name);
    }
}