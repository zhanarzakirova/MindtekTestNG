package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SmartBearTests {

    WebDriver driver;
    WebElement usernameInput;
    WebElement passwordInput;
    WebElement loginBtn;
    String username="Tester";
    String password="test";
    String expectedWelcomeText="Welcome, Tester!";
    String expectedInvalidMessage = "Invalid Login or Password.";

    WebElement orderTab;


    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");

        usernameInput= driver.findElement(By.id("ctl00_MainContent_username"));
        passwordInput= driver.findElement(By.id("ctl00_MainContent_password"));
        loginBtn = driver.findElement(By.id("ctl00_MainContent_login_button"));
    }

    @Test (priority = 0)
    public void loginPositive(){
       usernameInput.sendKeys(username);
       passwordInput.sendKeys(password);
       loginBtn.click();
       String actualWelcomeText=driver.findElement(By.xpath("//form[@id='aspnetForm']//div[@class='login_info']\n")).getText();
       Assert.assertEquals(actualWelcomeText.substring(0,actualWelcomeText.indexOf('!')+1),expectedWelcomeText);
    }

    @Test (priority = 1)
    public void loginNegative() {
        usernameInput.sendKeys("Zhanar");
        passwordInput.sendKeys("1234");
        loginBtn.click();
        String actualInvalidMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(actualInvalidMessage,expectedInvalidMessage);
    }

    @Test (priority = 2)
    public void loginEmptyCredentials() {
        usernameInput.sendKeys("");
        passwordInput.sendKeys("");
        loginBtn.click();
        String actualInvalidMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(actualInvalidMessage,expectedInvalidMessage);
    }

    @Test (priority = 3)
    public void totalAmountPositive() throws InterruptedException {
        loginPositive();
        orderTab = driver.findElement(By.xpath("//a[@href='Process.aspx']"));
        orderTab.click();
//        WebElement product = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select productSelect = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
        WebElement quantityField = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        WebElement calculateBtn= driver.findElement(By.xpath("//input[@value='Calculate']"));
        String totalAmount= driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getText();

//        WebDriverWait wait = new WebDriverWait(driver,10);
//        wait.until(ExpectedConditions.)
        String quantity = "4";
        Integer price = 100;

        productSelect.selectByVisibleText("MyMoney");
        quantityField.clear();
        quantityField.sendKeys(Keys.BACK_SPACE + quantity);
        calculateBtn.click();
        Thread.sleep(2000);

       int expectedAmount = Integer.parseInt(quantity)*price;
       Assert.assertEquals(Integer.parseInt(totalAmount),expectedAmount);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
