package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStep{

    WebDriver driver;

    @Given("User is login to SauceDemo site")
    public void user_is_login_to_sauce_demo_site() {
        System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @Given("User adds {string} item")
    public void user_adds_item(String item) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title']")));
        driver.findElement(By.xpath("//div[@class='inventory_item']//div[contains(text(),'" + item + "')]//..//..//..//button[contains(@class,'btn_inventory')]")).click();
    }

    @Then("Item {string} is displayed in cart")
    public void item_is_displayed_in_cart(String item) {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'checkout_button')]")));
        boolean status = driver.findElement(By.xpath("//div[@class='cart_item_label']//div[contains(text(),'" + item + "')]")).isDisplayed();
        Assert.assertTrue(status);
    }
    @When("User clicks Checkout")
    public void user_clicks_checkout() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'checkout_button')]")));
        driver.findElement(By.xpath("//button[contains(@class,'checkout_button')]")).click();
    }

    @Then("User will be redirected to Checkout page")
    public void user_will_be_redirected_to_checkout_page() {
        String title = driver.findElement(By.xpath("//*[@class='header_secondary_container']/span")).getText();
        Assert.assertEquals("Checkout: Your Information", title);
        driver.quit();
    }

    @Given("User checkouts cart")
    public void user_checkouts_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'checkout_button')]")));
        driver.findElement(By.xpath("//button[contains(@class,'checkout_button')]")).click();
    }
    @When("User enters {string}, {string}, {string} information")
    public void user_enters_information(String firstName, String lastName, String zip) {
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(zip);
    }
    @When("User continues to checkout item")
    public void user_continues_to_checkout_item() {
        driver.findElement(By.xpath("//input[@id='continue']")).click();
        driver.findElement(By.xpath("//button[@id='finish']")).click();
    }
    @Then("Item is successfully checkout")
    public void item_is_successfully_checkout() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals("https://www.saucedemo.com/checkout-complete.html", url);
        String title = driver.findElement(By.xpath("//*[@class='complete-header']")).getText();
        Assert.assertEquals("Thank you for your order!", title);
        driver.quit();
    }
}
