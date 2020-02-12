import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.AllSteps;
import steps.BaseSteps;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class YandexTest extends BaseSteps {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Компьютерная техника", "Ноутбуки", "0", "35000", "Apple", "Xiaomi", 9, 1},
//                { "Компьютерная техника", "Планшеты", "0","25000","Samsung","Lenovo", 18, 2},
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

    @Test
    @DisplayName("Тест маркета Яндекс")
    public void test() {
        AllSteps steps = new AllSteps();
        steps.marketStep();
        steps.categoryStep(category);
        steps.itemStep(item);
        steps.priceStep(from, to);
        steps.manufacturerStep(firstManufacturer, secondManufacturer);
        steps.checkCountStep(countOfElements);
        checkStep(number);
    }

    public void checkStep(int num) {
        AllSteps steps = new AllSteps();
        if (num == 1) {
            steps.checkManStep(firstManufacturer, secondManufacturer);
        }
        if (num == 2) {
            steps.checkPriceStep(from, to);
        }
    }
}