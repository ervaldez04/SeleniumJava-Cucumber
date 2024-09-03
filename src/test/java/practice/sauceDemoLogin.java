package practice;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.lang.Thread;
import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class sauceDemoLogin {
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        // FOR MAC
        // System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        // FOR WINDOWS
        // System.setProperty("webdriver.chrome.driver", "C:\\User\\evaldez\\Documents\\driver\\chromedriver_mac_arm64\\chromedriver.exe");
        // ChromeOptions if version of Chrome is v111
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--remote-allow-origins=*");
        // driver = new ChromeDriver(options);
        // For Chrome v115 and up
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");   //want headless browser run
        driver = new ChromeDriver(options);
       //  driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//*[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id='login-button']")).click();
        //create validations
        String title = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals("Products", title);
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", url);
        boolean isCartDisplayed = driver.findElement(By.xpath("//*[@class='shopping_cart_link']")).isDisplayed();
        Assert.assertTrue(isCartDisplayed);
        System.out.println("SUCCESS");
        driver.quit();

    }
}
