package tests.projects;

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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class project1 {

    WebDriver driver;
    String login = "Mindtek";
    String password = "MindtekStudent";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://qeenv-webhr-arslan.herokuapp.com/");
    }

    @Test
    public void logIn() {

        WebElement loginInput = driver.findElement(By.name("username"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.xpath("//button[@class=\"btn btn-success\"]"));

        loginInput.sendKeys("Mindtek");
        passwordInput.sendKeys("MindtekStudent");
        loginBtn.click();


    }

//    @Test
//    public void createEmployee() throws InterruptedException {
//
//        logIn();
//
//        WebElement createEmployee = driver.findElement(By.xpath("//a[@routerlink=\"/employee/-1\"]"));
//        createEmployee.click();
//        Thread.sleep(5000);
//        WebElement firstName = driver.findElement(By.xpath("//input[@name=\"firstName\"]"));
//        // firstName.click();
//        firstName.sendKeys("Global");
//        WebElement lastName = driver.findElement(By.xpath("//input[@name=\"lastName\"]"));
//        // lastName.click();
//        lastName.sendKeys("Student");
//
//        System.out.println("Waiting for 5 seconds");
//        Thread.sleep(2000);
//        System.out.println("After for 5 seconds");
//        // WebElement dropdown=driver.findElement(By.id("department"));
//        //  Select department=new Select(dropdown);
//        // department.selectByIndex(3);
//
//
//        //    Select jobTitle=new Select(driver.findElement(By.id("job")));
//        //  jobTitle.selectByValue("MK_MAN");
//
//        WebElement salaryBox = driver.findElement(By.xpath("//input[@name=\"salary\"]"));
//        salaryBox.sendKeys("100000");
//
//        WebElement saveBtn = driver.findElement(By.xpath("//button[@class=\"btn btn-success\"]"));
//        saveBtn.click();
//
//
//    }

    @Test
    public void logOut() {
        logIn();
        WebElement logOut = driver.findElement(By.xpath("//a[@routerlink=\"/logout\"]"));
        logOut.click();
        String expectedResult = "You are logged out";
        String actualResult = driver.findElement(By.xpath("//h1")).getText();


        Assert.assertEquals(expectedResult, actualResult);

    }

    //positive testcase
    @Test
    public void employeeEdit1() {
        logIn();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Ali";
        String lastName = "Muh";
        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();
        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName) && nameText.get(i).getText().contains(lastName));
        }
    }

    //negative testcase
    @Test
    public void employeeEdit2() { //negative testcase
        logIn();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "JZ";
        String lastName = "OG";
        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();
        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName) && nameText.get(i).getText().contains(lastName));
        }
    }

    @Test
    public void employeeEdit3() { //negative testcase
        logIn();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel%";
        String lastName = "Harsh**";
        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();
        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName) && nameText.get(i).getText().contains(lastName));
        }
    }

    @Test
    public void checkSalary1() { //positive
        logIn();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel";
        String lastName = "Harsh";
        String salary="1000";

        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement salaryBox=driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys(salary);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();
        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName) && nameText.get(i).getText().contains(lastName));
        }
    }

    @Test
    public void checkSalary2() { //negative
        logIn();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel";
        String lastName = "Harsh";
        String salary="1";

        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement salaryBox=driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys(salary);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();
        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName) && nameText.get(i).getText().contains(lastName));
        }
    }

    @Test
    public void checkSalary3() { //negative
        logIn();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel";
        String lastName = "Harsh";
        String salary="-1";

        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement salaryBox=driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys(salary);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();
        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName) && nameText.get(i).getText().contains(lastName));
        }
    }

    @Test
    public void checkSalary4() { //negative
        logIn();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel";
        String lastName = "Harsh";
        String salary="0";

        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement salaryBox=driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys(salary);
        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();

        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(firstName) && nameText.get(i).getText().contains(lastName));
        }
    }

    @Test
    public void checkDepartmentAndJobTitle1() {
        logIn();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel";
        String lastName = "Harsh";
        String salary="1000";
        String depName="IT";
        String jobName = "President";

        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement salaryBox=driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys(salary);

        WebElement dropdown=driver.findElement(By.id("department"));
        Select department=new Select(dropdown);
        department.selectByVisibleText("IT");

        Select jobTitle=new Select(driver.findElement(By.id("job")));
        jobTitle.selectByVisibleText(jobName);

        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();

        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(depName));
        }
    }


    @Test
    public void checkDepartmentAndJobTitle2() {
        logIn();
        WebElement editBtn = driver.findElement(By.xpath("/html/body/app-root/div[1]/app-employee-details/div[3]/table/tbody[2]/tr/td[5]/button"));
        editBtn.click();
        WebElement fNInput = driver.findElement(By.name("firstName"));
        WebElement lNInput = driver.findElement(By.name("lastName"));
        String firstName = "Patel";
        String lastName = "Harsh";
        String salary="1000";
        String depName="Sales";
        String jobName = "Sales Manager";

        fNInput.clear();
        fNInput.sendKeys(firstName);
        lNInput.clear();
        lNInput.sendKeys(lastName);
        WebElement salaryBox=driver.findElement(By.name("salary"));
        salaryBox.clear();
        salaryBox.sendKeys(salary);

        WebElement dropdown=driver.findElement(By.id("department"));
        Select department=new Select(dropdown);
        department.selectByVisibleText(depName);

        Select jobTitle=new Select(driver.findElement(By.id("job")));
        jobTitle.selectByVisibleText(jobName);

        WebElement saveBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        saveBtn.click();

        List<WebElement> nameText = driver.findElements(By.xpath("//tbody[2]"));
        for (int i = 0; i < nameText.size(); i++) {
            Assert.assertTrue(nameText.get(i).getText().contains(depName));
        }
    }



    @AfterMethod
    public void tearDown(){
//        driver.quit();
    }
}
