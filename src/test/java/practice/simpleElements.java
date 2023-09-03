package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class simpleElements {

    private static WebDriver driver;
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        //Click checkbox1 if not selected
        WebElement checkBoxElement1 = driver.findElement(By.xpath("//input[@type='checkbox'][1]"));
        boolean isSelected = checkBoxElement1.isSelected();
        System.out.println("Checkbox is selected:" + isSelected);
        if(isSelected == false) {
            checkBoxElement1.click();
        }
        isSelected = checkBoxElement1.isSelected();
        Assert.assertTrue(isSelected);

        //Click checkbox2 if not selected
        WebElement checkBoxElement2 = driver.findElement(By.xpath("//input[@type='checkbox'][2]"));
        isSelected = checkBoxElement1.isSelected();
        System.out.println("Checkbox is selected:" + isSelected);
        if(isSelected == false) {
            checkBoxElement1.click();
        }
        isSelected = checkBoxElement1.isSelected();
        Assert.assertTrue(isSelected);

        //Unselect checkbox2
        checkBoxElement2 = driver.findElement(By.xpath("//input[@type='checkbox'][2]"));
        isSelected = checkBoxElement2.isSelected();
        System.out.println("Checkbox is selected:" + isSelected);
        if(isSelected == true) {
            checkBoxElement2.click();
        }
        isSelected = checkBoxElement2.isSelected();
        Assert.assertFalse(isSelected);

        System.out.println("SUCCESS");
        driver.quit();
    }
}
