package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class dropdown {
    private static WebDriver driver;
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='dropdown']"));
        Select option = new Select(dropdown);

        //selectByValue
        option.selectByValue("1");
        WebElement selected = option.getFirstSelectedOption();
        String selectedOption = selected.getText();
        Assert.assertEquals("Option 1", selectedOption);
        System.out.println("Selected Option:" + selectedOption);

        // selectByVisibleText
        option.selectByVisibleText("Option 2");
        selected = option.getFirstSelectedOption();
        selectedOption = selected.getText();
        System.out.println("Selected Option:" + selectedOption);

        System.out.println("SUCCESS");
        driver.quit();
    }
}
