package practice;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class frames {

    private static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/frames");

        //Get total number of frames in page
        Integer size = driver.findElements(By.tagName("iframe")).size();
        System.out.println("Number of frames: " + size);

        //Get header text of frame1
        driver.switchTo().frame("frame1");
        WebElement headerText = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
        String header = headerText.getText();
        Assert.assertEquals("This is a sample page", header);
        driver.switchTo().defaultContent();

        System.out.println("SUCCESS");
        driver.quit();

    }
}
