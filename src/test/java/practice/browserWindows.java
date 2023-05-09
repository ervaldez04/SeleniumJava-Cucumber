package practice;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class browserWindows {
    private static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/windows");

        WebElement linkToClick = driver.findElement(By.xpath("//a[contains(@href, 'new')]"));
        String link = linkToClick.getAttribute("href");
        linkToClick.click();
        ArrayList<String> wid = new ArrayList<String>(driver.getWindowHandles());

        //switch to active tab
        driver.switchTo().window(wid.get(1));
        System.out.println("Page title of active tab: " + driver.getTitle());
        Assert.assertEquals("New Window", driver.getTitle());

        //switch to parent tab
        driver.switchTo().window(wid.get(0));
        System.out.println("Page title of active tab: " + driver.getTitle());

        System.out.println("SUCCESS");
        driver.quit();
    }
}
