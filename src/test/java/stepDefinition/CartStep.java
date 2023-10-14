package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartStep {

    private WebDriver driver;

    @Given("User is login to site")
    public void user_is_login_to_site() {
        WebDriverManager.chromedriver().setup();
        // System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @When("User adds {string}")
    public void user_adds(String item) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
        driver.findElement(By.xpath("//div[@class='inventory_item']//div[contains(text(),'" + item + "')]//..//..//..//button[contains(@class,'btn_inventory')]")).click();

    }
    @Then("{string} is added to cart")
    public void is_added_to_cart(String item) {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        boolean status = driver.findElement(By.xpath("//div[@class='cart_item_label']//div[contains(text(),'"+item+"')]")).isDisplayed();
        Assert.assertTrue(status);
    }

    @When("User removes {string}")
    public void user_removes(String item) {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        driver.findElement(By.xpath("//div[@class='cart_item_label']//div[contains(text(),'"+item+"')]//..//..//button[contains(@class,'btn_secondary')]")).click();

    }
    @Then("{string} is removed to cart")
    public void is_removed_to_cart(String item) {
        int isItemInCart = driver.findElements(By.xpath("//div[@class='cart_item_label']//div[contains(text(),'" + item + "')]")).size();
        Assert.assertEquals(0, isItemInCart);
    }

}
