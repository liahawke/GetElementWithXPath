package com.xpathgoogle.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class XPathSecondTestScenario {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String URL = "https://unicode-table.com/ru/";


       private String getXPath (String symbol){
        return "//div[@id='content']//ul[@class='unicode_table u0000']/li[contains(text(), '" + symbol + "')]";
    }

    /**
     * Set up method to initialize driver and WebDriverWait
     */
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

    }

    /**
     * Show certain letters in console
     *
     */
    @Test
    public void testFindElementsWithXPath() {
        driver.get(URL);
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(getXPath("Q")))));
        String xpathQ = driver.findElement(By.xpath(getXPath("Q"))).getText();
        String xpathAmp = driver.findElement(By.xpath(getXPath("&"))).getText();
        String xpathA = driver.findElement(By.xpath(getXPath("A"))).getText();
        System.out.println(xpathQ+ " " + xpathAmp+ " " +xpathA);
    }

    /**
     * Quit WebDriver
     */
    @After
    public void tearDown() {
        driver.quit();
    }
}
