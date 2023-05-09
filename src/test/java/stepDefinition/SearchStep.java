package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SearchStep {

    WebDriver driver;

    @Given("User navigates to Google site")
    public void user_navigates_to_google_site() {
        System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }
    @When("User searches word")
    public void user_searches_word() {
        driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Test Automation");
        driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys(Keys.RETURN);
    }

    @Then("Page will be redirected to the Search Page")
    public void page_will_be_redirected_to_the_search_page() {
        String searchedtitle = driver.getTitle();
        Assert.assertEquals( "Test Automation - Google Search", searchedtitle);
        driver.quit();
    }

}