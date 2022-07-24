package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HeadlessBrowser {

   WebDriver driver;
   @BeforeMethod
    public void setUp(){
       WebDriverManager.chromedriver().setup();

       ChromeOptions options = new ChromeOptions();
       options.addArguments("--headless");

       driver= new ChromeDriver(options);
       driver.manage().window().maximize(); //to be able to see all elements
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.get("https://www.amazon.com/");
   }

   @Test
    public void amazonHomePageVerification(){
       String expectedTitle="Amazon.com. Spend less. Smile more.";
       Assert.assertEquals(driver.getTitle(),expectedTitle);
       System.out.println(driver.getTitle());
   }

   @AfterMethod
    public void tearDown(){
       driver.quit();
   }
}
