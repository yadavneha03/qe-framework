package com.nehayadav.qe.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private final By finishButton = By.id("finish");
    private final By completeHeader = By.className("complete-header");
    private final By totalLabel = By.className("summary_total_label");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getTotalLabel() {
        return textOf(totalLabel);
    }

    public void finishOrder() {
        click(finishButton);
    }

    public String getCompleteHeaderText() {
        return textOf(completeHeader);
    }
}
