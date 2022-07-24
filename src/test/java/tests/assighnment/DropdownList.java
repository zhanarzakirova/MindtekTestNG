package tests.assighnment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DropdownList {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
    }

    @Test
    public void selectWednesday(){
        WebElement dropdownList = driver.findElement(By.id("select-demo"));
        Select dropdownSelect = new Select(dropdownList);
        dropdownSelect.getOptions().get(4).click();
        String selectedDayText = driver.findElement(By.xpath("//p[@class=\"selected-value\"]")).getText();
        Assert.assertEquals(selectedDayText,"Day selected :- Wednesday");
    }

    @Test
    public void selectSaturday(){
        WebElement dropdownList = driver.findElement(By.id("select-demo"));
        Select dropdownSelect = new Select(dropdownList);
        dropdownSelect.selectByValue("Saturday");
        String selectedDayText = driver.findElement(By.xpath("//p[@class=\"selected-value\"]")).getText();
        Assert.assertEquals(selectedDayText,"Day selected :- Saturday");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
