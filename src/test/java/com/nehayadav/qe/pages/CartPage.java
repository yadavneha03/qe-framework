package com.nehayadav.qe.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By checkoutButton = By.id("checkout");
    private final By cartItem = By.className("cart_item");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return driver.findElements(cartItem).size();
    }

    public CheckoutInfoPage proceedToCheckout() {
        click(checkoutButton);
        return new CheckoutInfoPage(driver);
    }
}
