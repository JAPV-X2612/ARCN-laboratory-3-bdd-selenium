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
import static org.junit.Assert.assertEquals;

import edu.eci.arcn.bdd.pages.DynamicLoadingPage;

import java.time.Duration;

/**
 * Step definitions for Dynamic Loading BDD scenarios.
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-07
 */
public class DynamicLoadingSteps {

    private WebDriver driver;
    private DynamicLoadingPage dynamicLoadingPage;

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
     * Navigates to the dynamic loading example 1 page.
     */
    @Given("I am on the dynamic loading page")
    public void i_am_on_the_dynamic_loading_page() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        dynamicLoadingPage = new DynamicLoadingPage(driver);
    }

    /**
     * Clicks the start button to trigger the loading process.
     */
    @When("I click the start button")
    public void i_click_the_start_button() {
        dynamicLoadingPage.clickStart();
    }

    /**
     * Asserts the expected text appears after loading completes.
     *
     * @param expectedText the text expected to appear after loading
     */
    @Then("I should see the text {string}")
    public void i_should_see_the_text(String expectedText) {
        assertEquals(expectedText, dynamicLoadingPage.getFinishText());
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
