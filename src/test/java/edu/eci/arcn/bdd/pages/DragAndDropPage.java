package edu.eci.arcn.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for the Drag and Drop page of The Internet.
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-07
 */
public class DragAndDropPage {

    private final WebDriver driver;

    @FindBy(id = "column-a")
    private WebElement columnA;

    @FindBy(id = "column-b")
    private WebElement columnB;

    /**
     * Initializes the DragAndDropPage and its elements via PageFactory.
     *
     * @param driver the active WebDriver instance
     */
    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Drags column A onto column B using the Actions API.
     */
    public void dragAtoB() {
        new Actions(driver)
                .dragAndDrop(columnA, columnB)
                .perform();
    }

    /**
     * Returns the header text of column A.
     *
     * @return header text of column A
     */
    public String getColumnAHeader() {
        return columnA.findElement(
                org.openqa.selenium.By.tagName("header")
        ).getText();
    }
}
