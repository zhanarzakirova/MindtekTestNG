package tests.assighnment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ShowMessage {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.seleniumeasy.com/basic-first-form-demo.html");
}

    @Test
    public void checkMessage(){
        WebElement inputMessage = driver.findElement(By.id("user-message"));
        String message="Hello,world!";
        inputMessage.sendKeys(message);
        driver.findElement(By.xpath("//*[@id=\"get-input\"]/button")).click();
        String textMessage = driver.findElement(By.id("display")).getText();
        Assert.assertEquals(textMessage,message);
    }
    @Test
    public void getTotal(){
        String a = "28";
        String b = "36";
        WebElement inputA = driver.findElement(By.id("sum1"));
        WebElement inputB = driver.findElement(By.id("sum2"));
        inputA.sendKeys(a);
        inputB.sendKeys(b);
        WebElement getTotalBtn = driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button"));
        getTotalBtn.click();
        String actualResult=driver.findElement(By.id("displayvalue")).getText();
        Integer result= (Integer.parseInt(a))+(Integer.parseInt(b));
        String expectedResult = result.toString();
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test
    public void getTotal2(){

       driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button")).click();
        String actualResult=driver.findElement(By.id("displayvalue")).getText();
        Assert.assertEquals(actualResult, "NaN");
    }



@AfterMethod
    public void tearDown(){
//        driver.quit();
}

}
