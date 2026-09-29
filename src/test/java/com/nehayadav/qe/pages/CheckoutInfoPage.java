package com.nehayadav.qe.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage {

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutOverviewPage fillInfoAndContinue(String firstName, String lastName, String postalCode) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(postalCodeInput, postalCode);
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public void submitIncomplete() {
        click(continueButton);
    }

    public String getErrorMessage() {
        return textOf(errorMessage);
    }
}