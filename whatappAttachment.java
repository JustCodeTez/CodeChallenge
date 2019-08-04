package com.AutomationPractice;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class whatappAttachment {

    WebDriver driver;
    @BeforeMethod
    public void beforeTest()
    {
        System.setProperty("webdriver.chrome.driver", "E://Selenium Setup//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://web.whatsapp.com/");
    }

    @AfterMethod
    public void afterTest() throws InterruptedException {
        Thread.sleep(5000);//*[@id="app"]
        driver.findElement(By.xpath("//*[@id=\"side\"]/header/div[2]/div/span/div[3]")).click();
        driver.quit();
    }

    @Test
    public void test() throws InterruptedException, IOException {

        FileInputStream fileInputStream = new FileInputStream("E:\\Selenium Setup\\AutomationPractice\\src\\test\\Excelesheets\\ContactListForDiwali.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        int numberOfContact = sheet.getPhysicalNumberOfRows();
        System.out.println("rows: "+numberOfContact);
        for(int i=1;i<numberOfContact;i++) {

            XSSFCell cell = sheet.getRow(i).getCell(1);
//          String contactNumber = cell.getStringCellValue();
            String contactNumber = new DataFormatter().formatCellValue(cell);
            System.out.println(contactNumber);

            //actual process
            WebElement contactNumberPath = driver.findElement(By.xpath("//*[@class=\"_2MSJr\"]/input"));
            contactNumberPath.sendKeys(contactNumber);

//            By.xpath("//*[@class=\"_2MSJr\"]/input").findElement(driver).sendKeys(contactNumber);
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@id=\"pane-side\"]//*[@class=\"_2EXPL\"]")).click();
            //*[@id="pane-side"]/div/div/div/div[3]/div/div/div[2]
            driver.findElement(By.xpath("//*[@id=\"main\"]//div[@title=\"Attach\"]")).click();
            ////*[@id="main"]/header/div[3]/div/div[2]/span/div/div/ul/li[1]/input
            driver.findElement(By.xpath("//*[@id=\"main\"]//li[1]/input")).sendKeys("E://Selenium Setup//AutomationPractice//src//test//Screenshots//DiwaliImage.jpeg");
            WebElement element = driver.findElement(By.xpath("//*[@id=\"app\"]//div[@class=\"CzI8E\"]"));
            Actions actions = new Actions(driver);
            actions.click(element).perform();

            Thread.sleep(3000);
            actions.sendKeys(element, "Wish you a happy Diwali... May god bless you health, wealth and success in your life...").perform();
            Thread.sleep(3000);
            actions.click(driver.findElement(By.xpath("//*[@data-icon=\"send-light\"]"))).perform();
            contactNumberPath.clear();
            Thread.sleep(3000);
//        driver.findElement(By.xpath("//*[@id=\"app\"]//div[@class=\"CzI8E\"]")).sendKeys("hi....");
//        driver.findElement(By.xpath("//*[@data-icon=\"send-light\"]")).click();
            //*[@id="app"]//div[@class="CzI8E"]
        }
    }


}
