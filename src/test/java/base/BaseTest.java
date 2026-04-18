package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://www.pepperfry.com/");

        // ✅ Handle login/signup popup (very important for Pepperfry)
        try {
            WebElement closePopup = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@id='wzrk-cancel']")
                    )
            );
            closePopup.click();
        } catch (Exception e) {
            System.out.println("Popup not displayed");
        }

        // ✅ Navigate to Product Listing Page (example: Furniture)
        driver.get("https://www.pepperfry.com/furniture.html");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}