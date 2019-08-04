package com.AutomationPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CodeChallenge1 {
    WebDriver driver;
    List<WebElement> PropertyPrices;
    @BeforeClass
    public void startBrowser()
    {
        System.setProperty("webdriver.chrome.driver","E://Selenium Setup//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.zoopla.co.uk");
    }
    @Test(priority = 1)
    public void listOfLondonBasedPropertiesAndPrices()
    {
        elementLocation("//*[@id='search-input-location']").sendKeys("London");
        elementLocation("//*[@id='search-submit']").click();
        elementLocation("//*[@data-role='listview']//a[@class='listing-results-price text-price']");
        PropertyPrices = driver.findElements(By.xpath("//*[@data-role='listview']//a[@class='listing-results-price text-price']"));
        List<Integer> SortedPrices = new ArrayList<Integer>();

        for(WebElement element:PropertyPrices)
        {
            String priceDetails = element.getText().trim();
            int prices = Integer.parseInt(priceDetails.replaceAll("[^0-9]",""));
            SortedPrices.add(prices);
        }
        Collections.sort(SortedPrices);
        Collections.reverse(SortedPrices);
        System.out.println("List of property Prices in descending order - "+SortedPrices);
    }

    @Test(priority = 2)
    public void RankFivePropertyDetails() throws InterruptedException {

        PropertyPrices.get(4).click();
        Boolean agentLogo = elementLocation("//*[@id='dp-sticky-element']//img").isDisplayed();
        if(agentLogo)
            System.out.println("Agent logo is displayed...");

        WebElement AgentContact = elementLocation("//a[@data-track-label=\"Agent phone number 1\"]");
        if (AgentContact.isDisplayed()) {
         String contact = AgentContact.getText().trim();
            System.out.println("Agent contact number: " + Integer.parseInt(contact.replaceAll("[^0-9]","")));
        }
        WebElement AgentName = elementLocation("//*[@id=\"dp-sticky-element\"]//h4");
        if(AgentName.isDisplayed())
        {
            String AgentNameDetails = AgentName.getText();
            System.out.println("Agent name: "+AgentNameDetails);
            AgentName.click();
            String ActualAgentName = elementLocation("//*[@id=\"content\"]//h1/b[1]").getText();
            Assert.assertEquals(ActualAgentName,AgentNameDetails);
            System.out.println("Agent name is verified...");
        }

    }
    @AfterClass
    public void closeBrowser() throws InterruptedException {
        driver.close();
    }
     public WebElement elementLocation(String path)
     {
         return driver.findElement(By.xpath(path));
     }
}
