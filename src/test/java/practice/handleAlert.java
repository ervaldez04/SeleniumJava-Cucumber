package practice;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class handleAlert {

    private static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/alerts");

        //Handle Alert 1
        driver.findElement(By.xpath("//button[@id='alertButton']")).click();
        Alert alert1 = driver.switchTo().alert();
        String alert1Message = alert1.getText();
        System.out.println(alert1Message);
        alert1.accept();
        driver.switchTo().defaultContent();

        //Handle Alert 2
        driver.findElement(By.xpath("//button[@id='timerAlertButton']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert2 = driver.switchTo().alert();
        String alert2Message = alert1.getText();
        System.out.println(alert2Message);
        alert2.accept();
        driver.switchTo().defaultContent();

        //Handle Alert 3
        WebElement button = driver.findElement(By.xpath("//button[@id='confirmButton']"));
        button.click();
        Alert alert3 = driver.switchTo().alert();
        String alert3Message = alert1.getText();
        System.out.println(alert3Message);
        alert3.dismiss();
        driver.switchTo().defaultContent();
        String message = driver.findElement(By.xpath("//span[@id='confirmResult']")).getText();
        Assert.assertTrue(message.contains("Cancel"));
        button.click();
        alert3 = driver.switchTo().alert();
        alert3.accept();
        driver.switchTo().defaultContent();
        message = driver.findElement(By.xpath("//span[@id='confirmResult']")).getText();
        Assert.assertTrue(message.contains("Ok"));

        //Handle Alert 4
        driver.findElement(By.xpath("//button[@id='promtButton']")).click();
        Alert alert4 = driver.switchTo().alert();
        String text = "test";
        alert4.sendKeys(text);
        alert4.accept();
        driver.switchTo().defaultContent();
        String promptMessage = driver.findElement(By.xpath("//span[@id='promptResult']")).getText();
        Assert.assertTrue(promptMessage.contains(text));
        driver.findElement(By.xpath("//button[@id='promtButton']")).click();
        alert4 = driver.switchTo().alert();
        alert4.sendKeys(text);
        alert4.dismiss();

        System.out.println("SUCCESS");
        driver.quit();
    }

}
