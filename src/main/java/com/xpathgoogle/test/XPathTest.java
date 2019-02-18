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

public class XPathTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String URL_PHONE = "https://supsystic.com/example/comparison-example/";
    private final String SAMSUNG_NEW_PRICE_XPATH = "//*[@id=\"ptsBlock_108860\"]//div[@class=\"ptsEl ptsCol ptsElWithArea ptsCol-2\"]//div[@class=\"ptsRows ui-sortable\"]/div[@class=\"ptsCell\"][4]//span";
    private final String SAMSUNG_OLD_PRICE_XPATH = "//*[@id='ptsBlock_108860']//div[@class='ptsEl ptsCol ptsElWithArea ptsCol-2']//div[@class='ptsColFooter']/div[@class='ptsEl']//span";


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
     *
     */
    @Test
    public void testOpenSiteWithWaitsTest() {
        driver.get(URL_PHONE);
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'$959.00')]"))));
        String samsungNewPrice = driver.findElement(By.xpath(SAMSUNG_NEW_PRICE_XPATH)).getText().substring(1);
        String samsungOldPrice = driver.findElement(By.xpath(SAMSUNG_OLD_PRICE_XPATH)).getText().substring(1);
        float diffSamsungPrice = (Float.parseFloat(samsungOldPrice) - Float.parseFloat(samsungNewPrice));
        System.out.println("Samsung new price - " + samsungNewPrice);
        System.out.println("Samsung old price - " + samsungOldPrice);
        System.out.println("Samsung diff price - " + diffSamsungPrice);
    }

    /**
     * Quit WebDriver
     */
    @After
    public void tearDown() {
        driver.quit();
    }
}