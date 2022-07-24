package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BrowserUtils;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Waits {

    WebDriver driver;
    WebDriverWait explicitlyWait;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
    }

    @Test
    public void expWait(){
        WebElement addRemoveBtn =driver.findElement(By.xpath("//form[@id='checkbox-example']//button"));
        WebElement checkBoxBtn = driver.findElement(By.xpath("//div[@id='checkbox']//input"));

        checkBoxBtn.click();
        addRemoveBtn.click();

//        explicitlyWait = new WebDriverWait(driver,10);
//        String expectedMessage = explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText();

        WebElement message = driver.findElement(By.id("message"));
        BrowserUtils.waitForElementToBeVisible(message,10);
        String expectedMessage = message.getText();
        Assert.assertEquals(expectedMessage,"It's gone!");
    }

    @Test
    public void fluWait(){
        WebElement enableBtn= driver.findElement(By.xpath("//form[@id='input-example']/button"));
        enableBtn.click();

        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(20,TimeUnit.SECONDS)
                .pollingEvery(5,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement actualMessage = fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("message"));
            }
        });

        Assert.assertEquals(actualMessage.getText(), "It's enabled!");


    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
