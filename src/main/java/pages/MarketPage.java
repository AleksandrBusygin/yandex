package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MarketPage extends BasePageObject {
    @FindBy(xpath = "//div[@data-zone-name='category-link']")
    List<WebElement> categories;

    @FindBy(xpath = "//div[@class='n-region-notification__header']")
    WebElement regionHelper;

    @FindBy(xpath = "//div/span[contains(@class,'n-region-notification__ok')]")
    WebElement region;

    public void chooseCategory(String name) {
        waitFieldisDisplayed(regionHelper);
        click(region);
        chooseElement(categories, name);
    }
}