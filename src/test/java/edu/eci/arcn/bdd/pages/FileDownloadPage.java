package edu.eci.arcn.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Page Object for the File Download page of The Internet.
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-07
 */
public class FileDownloadPage {

    @FindBy(css = ".example a")
    private List<WebElement> downloadLinks;

    /**
     * Initializes the FileDownloadPage and its elements via PageFactory.
     *
     * @param driver the active WebDriver instance
     */
    public FileDownloadPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the download link matching the given filename.
     *
     * @param filename the name of the file to download
     * @throws IllegalArgumentException if no link matching the filename is found
     */
    public void clickDownloadLink(String filename) {
        downloadLinks.stream()
                .filter(link -> link.getText().equals(filename))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("File not found: " + filename))
                .click();
    }
}
