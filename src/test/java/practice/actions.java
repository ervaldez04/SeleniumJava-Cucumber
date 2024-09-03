package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class actions {

    private static WebDriver driver;
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("headless");   //want headless browser run
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");

        // Initialize Actions
        Actions action = new Actions(driver);

        // Action: Right click
        WebElement button1 = driver.findElement(By.xpath("//span[contains(@class, 'context-menu-one')]"));
        action.contextClick(button1).perform();
        // Select options in menu (Edit, Cut, Copy, Paste, Delete, Quit)
        WebElement element = driver.findElement(By.cssSelector(".context-menu-icon-edit"));
        element.click();
        //Handle Alert displayed
        Alert alert = driver.switchTo().alert();
        String message = alert.getText();
        Assert.assertTrue(message.contains("edit"));
        alert.accept();

        System.out.println("SUCCESS: Right Click");

        // Action: Double click
        WebElement button2 = driver.findElement(By.xpath("//button[@ondblclick='myFunction()']"));
        action.doubleClick(button2).perform();
        //Handle Alert displayed
        alert = driver.switchTo().alert();
        message = alert.getText();
        Assert.assertEquals("You double clicked me.. Thank You..", message);
        alert.accept();

        System.out.println("SUCCESS: Double Click");

        driver.quit();
    }
}
