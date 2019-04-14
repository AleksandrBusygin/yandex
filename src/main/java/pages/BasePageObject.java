package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Init;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class BasePageObject {

    public BasePageObject(){
        PageFactory.initElements(Init.getDriver(), this);
    }

    public static void chooseElement(List<WebElement> list, String name){
        list.stream().filter(element -> element.getText().equals(name)).findFirst().get().click();
    }

    public static void scrollToElement(WebElement find){
        ((JavascriptExecutor) Init.getDriver()).executeScript("arguments[0].scrollIntoView();", find);
        Wait<WebDriver> wait = new WebDriverWait(Init.getDriver(), 20, 1000);
        wait.until(ExpectedConditions.visibilityOf(find));
    }

    public boolean isElementPresent(WebElement element){
        try{
            Init.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            return  element.isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public static void fillForm(String text, WebElement element) {
        while (!(element.getAttribute("value").equals(text))) {
            scrollToElement(element);
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }
}