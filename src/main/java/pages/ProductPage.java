package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private By productTitle = By.xpath("//h1");

    private By addToCartBtn = By.xpath("//button[contains(.,'Add')]");
    // Cart popup / side panel (Pepperfry dynamic)
    private By cartPanel = By.xpath("//div[contains(@class,'cart')]");

    public boolean isProductPageLoaded() {

        System.out.println("Validating Product Page...");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(@class,'product') or contains(text(),'₹')]")
                )
        ).isDisplayed();
    }

    public void clickAddToCart() {

        System.out.println("Trying to click Add to Cart...");
        System.out.println("Current URL: " + driver.getCurrentUrl());

        WebElement btn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(addToCartBtn)
        );

        System.out.println("Add to Cart button found");

        btn.click();
    }

    public boolean isCartOpened() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartPanel)
        ).isDisplayed();
    }
}