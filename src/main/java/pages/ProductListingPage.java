package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ProductListingPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ProductListingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Updated working locator for Pepperfry
    private By products = By.xpath("//div[contains(@class,'product')]//a");

    public void clickFirstProduct() {

        System.out.println("Scrolling to load products...");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        // Scroll to trigger lazy loading
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,800)");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(products));

        WebElement firstProduct = driver.findElements(products).get(0);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                firstProduct
        );

        System.out.println("Clicking first product...");

        try {
            firstProduct.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    firstProduct
            );
        }

        System.out.println("Product clicked");
    }
}