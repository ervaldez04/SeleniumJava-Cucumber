package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Map;

public class LoginStep{

    WebDriver driver;

    @Given("User is in SauceDemo Login Page")
    public void user_is_in_sauce_demo_login_page() {
//        System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("headless");   //want headless browser run
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @When("User enters {string} and {string}")
    public void user_enters_and(String username, String password) {
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @When("^User enters ([^\"]*) and ([^\"]*)$")
    public void user_enters_username_password(String username, String password) {
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @When("^User enters credentials$")
    public void user_enters_credentials(DataTable usercredentials) throws Throwable {
        //Write the code to handle Data Table
        List<List<String>> data = usercredentials.asLists();

        //This is to get the first data of the set (First Row + First Column)
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(data.get(0).get(0));

        //This is to get the first data of the set (First Row + Second Column)
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(data.get(0).get(1));

        driver.findElement(By.xpath("//input[@id='login-button']")).click();

    }

//    @When("^User enters credentials$")
//    public void user_enters_credentials(DataTable usercredentials) throws Throwable {
//        //Write the code to handle Data Table
//        for (Map<String, String> data : usercredentials.asMaps(String.class, String.class)) {
//
//            driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(data.get("loginName"));
//            driver.findElement(By.xpath("//input[@id='password']")).sendKeys(data.get("loginPassword"));
//            driver.findElement(By.xpath("//input[@id='login-button']")).click();
//        }
//    @When("^User enters credentials$")
//    public void user_enters_credentials(List<Credentials>  usercredentials) throws Throwable {
//
//        //Write the code to handle Data Table
//        for (Credentials credentials : usercredentials) {
//            driver.findElement(By.id("log")).sendKeys(credentials.getUsername());
//            driver.findElement(By.id("pwd")).sendKeys(credentials.getPassword());
//            driver.findElement(By.id("login")).click();
//        }
//    } @When("^User enters credentials$")
//    public void user_enters_credentials(List<Credentials>  usercredentials) throws Throwable {
//
//        //Write the code to handle Data Table
//        for (Credentials credentials : usercredentials) {
//            driver.findElement(By.id("log")).sendKeys(credentials.getUsername());
//            driver.findElement(By.id("pwd")).sendKeys(credentials.getPassword());
//            driver.findElement(By.id("login")).click();
//        }
//    }

    @Then("User is login successfully")
    public void user_is_login_successfully() {
        String titlePage = driver.findElement(By.xpath("//div[@class='header_secondary_container']/span[@class='title']")).getText();
        Assert.assertEquals("Products", titlePage);
        boolean isCartDisplayed = driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).isDisplayed();
        Assert.assertTrue(isCartDisplayed);
    }

    @Then("Page will display {string}")
    public void page_displays_error_message(String errorMessage) {
        String message = driver.findElement(By.xpath("//*[@class='error-message-container error']/h3[@data-test='error']")).getText();
        Assert.assertEquals(errorMessage, message);
        driver.navigate().refresh();
    }

    @Then("^Page will display error message$")
    public void page_will_display_error_message(DataTable message) throws Throwable {
        //Write the code to handle Data Table
        List<List<String>> data = message.asLists();
        String errorMessage = driver.findElement(By.xpath("//*[@class='error-message-container error']/h3[@data-test='error']")).getText();
        Assert.assertEquals(data.get(0).get(0), errorMessage);
        driver.navigate().refresh();
    }

    @Then("^Page displays error message$")
    public void page_displays_error_message(DataTable message) throws Throwable {
        for (Map<String, String> data : message.asMaps(String.class, String.class)) {
            String errorMessage = driver.findElement(By.xpath("//*[@class='error-message-container error']/h3[@data-test='error']")).getText();
            Assert.assertEquals(data.get("ErrorMessage"), errorMessage);
//            driver.navigate().refresh();


        }

    }

    @Then("User closes browser")
    public void user_closes_browser() {

        driver.quit();
    }
}
