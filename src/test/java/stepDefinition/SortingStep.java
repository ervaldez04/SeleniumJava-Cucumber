package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingStep{

    WebDriver driver;
    String username = "standard_user";
    String password = "secret_sauce";

    @When("User select sort via {string}")
    public void user_select_sort_via(String sortOption) {
        WebElement element = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select option = new Select(element);
        option.selectByValue(sortOption);
    }

    @Then("Items will be sorted in via price in ascending order")
    public void items_will_be_sorted_via_price_in_ascending_order() {
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        List<Double> productPriceList = new ArrayList<>();
        for (WebElement we : elementList) {
            productPriceList.add(Double.valueOf(we.getText().replace("$", "")));
        }

        ArrayList<Double> sortedList = new ArrayList<>();
        for (Double s : productPriceList) {
            sortedList.add(s);
        }

        Collections.sort(sortedList);
        System.out.println(sortedList);
        System.out.println(productPriceList);
        Assert.assertTrue(sortedList.equals(productPriceList));
    }

    @Then("Items will be sorted in via price in descending order")
    public void items_will_be_sorted_via_price_in_descending_order() {
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        List<Double> productPriceList = new ArrayList<>();
        for (WebElement we : elementList) {
            productPriceList.add(Double.valueOf(we.getText().replace("$", "")));
        }

        ArrayList<Double> sortedList = new ArrayList<>();
        for (Double s : productPriceList) {
            sortedList.add(s);
        }

        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        System.out.println(sortedList);
        System.out.println(productPriceList);
        Assert.assertTrue(sortedList.equals(productPriceList));
    }

    @Then("Items will be sorted in via name in descending order")
    public void items_will_be_sorted_via_name_in_descending_order() {
        ArrayList<String> productNameList = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        for (WebElement we : elementList) {
            productNameList.add(we.getText());
        }

        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : productNameList) {
            sortedList.add(s);
        }

        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        System.out.println(sortedList);
        System.out.println(productNameList);
        Assert.assertTrue(sortedList.equals(productNameList));
    }

    @Then("Items will be sorted in via name in ascending order")
    public void items_will_be_sorted_via_name_in_ascending_order() {
        ArrayList<String> productNameList = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        for (WebElement we : elementList) {
            productNameList.add(we.getText());
        }

        ArrayList<String> sortedList = new ArrayList<>();
        for (String s : productNameList) {
            sortedList.add(s);
        }

        Collections.sort(sortedList);
        System.out.println(sortedList);
        System.out.println(productNameList);
        Assert.assertTrue(sortedList.equals(productNameList));
    }

    @When("User enters valid credentials")
    public void user_enters_valid_credentials() {
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @Then("User closes the browser")
    public void user_closes_the_browser() {

        driver.quit();
    }

    @Given("User opens SauceDemo site")
    public void user_opens_saucedemo_site() {
        WebDriverManager.chromedriver().setup();
        // System.setProperty("webdriver.chrome.driver", "/Users/evaldez/Documents/driver/chromedriver_mac_arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Then("User will be navigated to Dashboard")
    public void user_will_be_navigated_to_dashboard() {
        String titlePage = driver.findElement(By.xpath("//div[@class='header_secondary_container']/span[@class='title']")).getText();
        Assert.assertEquals("Products", titlePage);
        boolean isCartDisplayed = driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).isDisplayed();
        Assert.assertTrue(isCartDisplayed);
    }
}
