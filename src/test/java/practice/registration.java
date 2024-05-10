package practice;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class registration {
    private static WebDriver driver;

    public static void main(String[] args) {
        String email = "testing@email.com";
        String password = "Testingwelp1!";

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://practicesoftwaretesting.com/#/auth/register");
        driver.findElement(By.xpath("//input[@id = 'first_name']")).sendKeys("First");
        driver.findElement(By.xpath("//input[@id = 'last_name']")).sendKeys("Last");
        driver.findElement(By.xpath("//input[@id = 'dob']")).sendKeys("02/02/1995");
        driver.findElement(By.xpath("//input[@id = 'address']")).sendKeys("Test Address");
        driver.findElement(By.xpath("//input[@id = 'postcode']")).sendKeys("1807");
        driver.findElement(By.xpath("//input[@id = 'city']")).sendKeys("city name");
        driver.findElement(By.xpath("//input[@id = 'state']")).sendKeys("state name");
        WebElement countrydropdown = driver.findElement(By.xpath("//select[@id = 'country']"));
        Select option = new Select(countrydropdown);
        option.selectByVisibleText("Philippines (the)");
        WebElement selected = option.getFirstSelectedOption();
        String selectedOption = selected.getText();
        System.out.println("Selected Option:" + selectedOption);
        driver.findElement(By.xpath("//input[@id = 'phone']")).sendKeys("0001277");
        driver.findElement(By.xpath("//input[@id = 'email']")).clear();
        driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id = 'password']")).clear();
        driver.findElement(By.xpath("//input[@id = 'password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@data-test = 'register-submit']")).click();

        driver.get("https://practicesoftwaretesting.com/#/auth/login");
        driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id = 'password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value = 'Login']")).click();

        String currentTitle = driver.getTitle();
        System.out.println(currentTitle);
        Assert.assertEquals("Practice Software Testing - Toolshop - v5.0", currentTitle);

        driver.quit();

    }

}
