package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductListingPage;
import pages.ProductPage;

import java.util.Set;

public class TestRun extends BaseTest {

    @Test
    public void testEndToEnd_AddToCartFlow() {

        System.out.println("Step 1: Click product from listing");

        ProductListingPage listingPage = new ProductListingPage(driver);
        listingPage.clickFirstProduct();

        // ✅ DEBUG
        System.out.println("After click - URL: " + driver.getCurrentUrl());

        // ✅ Handle new tab properly
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // ✅ DEBUG after switch
        System.out.println("After switch - URL: " + driver.getCurrentUrl());

        System.out.println("Step 2: Validate Product Page");

        ProductPage productPage = new ProductPage(driver);

        Assert.assertTrue(
                productPage.isProductPageLoaded(),
                "❌ Product page NOT loaded"
        );

        System.out.println("Step 3: Add to Cart");

        productPage.clickAddToCart();

        System.out.println("✅ Test Completed Successfully");
    }
}