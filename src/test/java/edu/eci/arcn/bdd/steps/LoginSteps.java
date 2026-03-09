package edu.eci.arcn.bdd.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertTrue;

import edu.eci.arcn.bdd.pages.LoginPage;

import java.time.Duration;

/**
 * Step definitions for authentication BDD scenarios.
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-07
 */
public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    /**
     * Initializes ChromeDriver in headless mode before each scenario.
     */
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Navigates to the login page and initializes the LoginPage object.
     */
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    /**
     * Enters credentials into the login form.
     *
     * @param username the username to enter
     * @param password the password to enter
     */
    @When("the user enters username {string} and password {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    /**
     * Clicks the login button to submit the form.
     */
    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLoginButton();
    }

    /**
     * Asserts the browser redirected to the secure area.
     */
    @Then("the user should be redirected to the secure area")
    public void the_user_should_be_redirected_to_the_secure_area() {
        assertTrue(driver.getCurrentUrl().contains("/secure"));
    }

    /**
     * Asserts a success message is visible on the page.
     *
     * @param message the expected success message text
     */
    @Then("a success {string} should be displayed")
    public void a_success_message_should_be_displayed(String message) {
        assertTrue(driver.getPageSource().contains(message));
    }

    /**
     * Closes the browser after each scenario.
     */
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
