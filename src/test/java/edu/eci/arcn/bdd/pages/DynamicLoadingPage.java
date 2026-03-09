package edu.eci.arcn.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object for the Dynamic Loading page of The Internet.
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-07
 */
public class DynamicLoadingPage {

    private final WebDriver driver;

    @FindBy(css = "#start button")
    private WebElement startButton;

    @FindBy(id = "finish")
    private WebElement finishElement;

    /**
     * Initializes the DynamicLoadingPage and its elements via PageFactory.
     *
     * @param driver the active WebDriver instance
     */
    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the start button to trigger the dynamic load.
     */
    public void clickStart() {
        startButton.click();
    }

    /**
     * Waits until the dynamically loaded element is visible and returns its text.
     *
     * @return the text of the dynamically loaded element
     */
    public String getFinishText() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(finishElement));
        return finishElement.getText();
    }
}
