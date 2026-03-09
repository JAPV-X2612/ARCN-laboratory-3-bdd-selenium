package edu.eci.arcn.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for the login page of The Internet.
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-07
 */
public class LoginPage {

    private final WebDriver driver;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    /**
     * Initializes the LoginPage and its elements via PageFactory.
     *
     * @param driver the active WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters the given username into the username field.
     *
     * @param username the username to enter
     */
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    /**
     * Enters the given password into the password field.
     *
     * @param password the password to enter
     */
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    /**
     * Clicks the login submit button.
     */
    public void clickLoginButton() {
        loginButton.click();
    }
}
