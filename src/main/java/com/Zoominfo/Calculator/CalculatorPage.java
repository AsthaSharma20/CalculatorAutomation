package com.Zoominfo.Calculator;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CalculatorPage {
    private WebDriver driver; // WebDriver instance for interacting with the browser
    private WebDriverWait wait; // WebDriverWait instance for waiting for elements

    // Locators for web elements on the calculator page
    private By displayLocator = By.id("input"); // Locator for the input/display field
    private By historyButtonLocator = By.id("hist"); // Locator for the history button
    private By historyLocator = By.id("histframe"); // Locator for the history frame

    // Constructor to initialize WebDriver and WebDriverWait
    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        // Initialize WebDriverWait with a timeout of 5 seconds
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Method to enter a formula into the calculator display
    public void enterFormula(String formula) {
        WebElement displayElement = driver.findElement(displayLocator);
        displayElement.clear(); // Clear any existing input in the display field
        displayElement.sendKeys(formula); // Enter the formula
        displayElement.sendKeys(Keys.ENTER); // Press the Enter key and submit
    }

    // Method to get the result displayed in the calculator after calculation
    public String getDisplayResult() {
        WebElement display = driver.findElement(displayLocator);
        String initialValue = display.getAttribute("value"); // Get the initial value from the display
        // driver.findElement(equalButtonLocator).click(); // Click the '=' button to
        // calculate
        waitForResult(display, initialValue); // Wait for the result to change
        return driver.findElement(displayLocator).getAttribute("value"); // Get the updated value after calculation
    }

    // Method to get the history of calculations from the calculator
    public String getHistory() {
        driver.findElement(historyButtonLocator).click(); // Click the history button to view history
        return driver.findElement(historyLocator).getText(); // Get the text content of the history frame
    }

    // Private method to wait for the result to change after calculation
    private void waitForResult(WebElement display, String initialValue) {
        // Wait until the display value changes from the initial value after calculation
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(display, "value", initialValue)));
    }
}
