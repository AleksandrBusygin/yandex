package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BasePageObject {

    @FindBy(xpath = "//a[@data-id='market']")
    WebElement service;

    public void chooseService(){
        service.click();
    }

}