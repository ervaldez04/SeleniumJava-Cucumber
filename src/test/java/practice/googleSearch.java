package practice;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class googleSearch {
    private static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Test Automation");
        driver.findElement(By.xpath("//div[@class='lJ9FBc']//input[@name='btnK']")).click();
        String title = driver.getTitle();
        Assert.assertEquals("Test Automation - Google Search", title);
        System.out.println("Searched successfully");
        driver.quit();
    }
}