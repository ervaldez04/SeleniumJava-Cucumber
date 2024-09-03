package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class frames {

    private static WebDriver driver;
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("headless");   //want headless browser run
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
