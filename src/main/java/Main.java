import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Открываем сайт Авито
        driver.get("https://www.avito.ru/");
        //В категории выбираем оргтехнику и расходники
        driver.findElement(By.xpath("//*[@id='category']/option[@value=99]")).click();

        //В поле поиска вводим значение "принтер"
        driver.findElement(By.xpath("//*[@id='search']")).sendKeys("Принтер");

        //Кликаем на поле "город"
        driver.findElement(By.xpath("//*[@data-marker='search-form/region']")).click();

        //Заполняем значение "Владивосток", кликаем по первому элементу

        driver.findElement(By.xpath("//input[@data-marker='popup-location/region/input']")).sendKeys("Владивосток");
        Thread.sleep(500);
        driver.findElement(By.xpath("//li[@data-marker='suggest(0)']")).click();

        //Показать объявления
        driver.findElement(By.xpath("//*[@data-marker='popup-location/save-button']")).click();

        //Фильтрация по убыванию цены
        Select select = new Select(
                driver.findElement(By.xpath("//div[@class='form-select-v2 sort-select-3QxXG']/select")));
        select.selectByVisibleText("Дороже");

        //Выести название и стоимость 3-х первых принтеров
        List<WebElement> printName = driver.findElements(By.xpath("//a[@class='snippet-link']"));
        List<WebElement> printPrice = driver.findElements(By.xpath("//div[@class='snippet-price-row']"));

        for (int i = 0; (i < 3); i++) {
            System.out.println(printName.get(i).getText());
            System.out.println(printPrice.get(i).getText());
        }

        driver.close();
    }
}
