package steps;

import io.qameta.allure.Step;
import pages.MainPage;
import pages.MarketPage;
import pages.ParamMarketPage;
import pages.SecondMarketPage;

public class AllSteps {

    MainPage mainPage = new MainPage();
    MarketPage marketPage = new MarketPage();
    SecondMarketPage secondMarketPage = new SecondMarketPage();
    ParamMarketPage paramMarketPage = new ParamMarketPage();

    @Step("Заходим на маркет яндекса")
    public void marketStep(){
        mainPage.chooseService();
    }

    @Step("Выбираем категорию {0}")
    public void categoryStep(String category){
        marketPage.chooseCategory(category);
    }

    @Step("Выбираем вид товара {0}")
    public void itemStep(String item){
        secondMarketPage.chooseItem(item);
    }

    @Step("Задаем фильтр цены: от {0} до {1}")
    public void priceStep(String priceFrom, String priceTo){
        paramMarketPage.setPrice(priceFrom, priceTo);
    }

    @Step("Задаем фильтр производителя: {0} и {1}")
    public void manufacturerStep(String first, String second){
        paramMarketPage.chooseManufacturer(first, second);
    }

    @Step("Проверяем ожидаемое количество товаров {0} с полученным")
    public void checkCountStep(int count){
        paramMarketPage.checkCountOfProducts(count);
    }

    @Step("Проверяем, что найденные товары изготовлены только {0} и {1}")
    public void checkManStep(String first, String second){
        paramMarketPage.checkProduct(first, second);
    }

    @Step("Проверяем, что найденные товары находятся в диапазоне цены от {0} до {1}")
    public void checkPriceStep(String from, String to){
        paramMarketPage.checkPrice(from, to);
    }




}
