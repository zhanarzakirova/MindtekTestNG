package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WindowHandles {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/windows");
    }

    @Test
    public void windowHandleVerification(){
        WebElement newWindowLink = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a"));
        String mainWindowId = driver.getWindowHandle();

        newWindowLink.click();

        Set<String> windowIds = driver.getWindowHandles();

        for(String windowId : windowIds){
            if(!windowId.equalsIgnoreCase(mainWindowId)){
                driver.switchTo().window(windowId);
            }
        }

        String newWindowText = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(newWindowText,"New Window");

        driver.close();
    }

    @AfterMethod
 public void tearDown(){
        driver.quit();
    }

}
