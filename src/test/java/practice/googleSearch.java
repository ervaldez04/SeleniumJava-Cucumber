package practice;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class googleSearch {
    private static WebDriver driver;

    public static void main(String[] args) {

        String searchTerm = "Test Automation";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(searchTerm);
        driver.findElement(By.xpath("//div[@class='lJ9FBc']//input[@name='btnK']")).click();
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals("Test Automation - Google Search", title);
        System.out.println("Searched successfully");
        driver.quit();
    }
}