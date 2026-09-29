package com.nehayadav.qe.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By inventoryItemName = By.className("inventory_item_name");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return textOf(pageTitle);
    }

    public boolean isLoaded() {
        return isVisible(pageTitle) && "Products".equals(getPageTitle());
    }

    public ProductsPage addProductToCart(String productSlug) {
        click(By.id("add-to-cart-" + productSlug));
        return this;
    }

    public int getCartItemCount() {
        if (!isVisible(cartBadge)) {
            return 0;
        }
        return Integer.parseInt(textOf(cartBadge));
    }

    public CartPage goToCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public String getFirstProductName() {
        return textOf(inventoryItemName);
    }
}
