package tests;

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
import utilities.ConfigReader;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class BlazeDemo {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
    }

    @Test (groups = "regression")
    public void verifyFlightDestinations(){

        driver.get(ConfigReader.getProperty("BlazeDemoURL"));

        String myLocation = "Paris";
        String myDestination = "London";

        Select fromPort = new Select(driver.findElement(By.name("fromPort")));
        Select toPort = new Select(driver.findElement(By.name("toPort")));
        WebElement findBtn = driver.findElement(By.xpath("//input[@value='Find Flights']"));

        fromPort.selectByValue(myLocation);
        toPort.selectByValue(myDestination);
        findBtn.click();

        String expectedResult = "Flights from "+myLocation+" to "+myDestination+":";
        String actualResult = driver.findElement(By.tagName("h3")).getText().trim();

        Assert.assertEquals(expectedResult, actualResult, "Flight destination are incorrect");

    }

    @Test (groups = {"regression","smoke"})
    public void destinationOfTheWeek(){

        driver.get(ConfigReader.getProperty("BlazeDemoURL"));

        driver.findElement(By.xpath("//a[@href='vacation.html']")).click();
        String destination = driver.findElement(By.xpath("//div[contains(text(),'Hawaii')]")).getText();
        Assert.assertEquals(destination.trim(),"Destination of the week: Hawaii !");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
