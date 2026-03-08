package edu.eci.arcn.bdd.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * Step definitions for Google Search BDD scenarios.
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-07
 */
public class SearchSteps {

    private WebDriver driver;

    /**
     * Initializes the ChromeDriver in headless mode before each scenario.
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
     * Navigates to the Google search page.
     */
    @Given("I am on the Google search page")
    public void i_am_on_the_google_search_page() {
        driver.get("https://www.google.com");
    }

    /**
     * Types a search term into the Google search box and submits it.
     *
     * @param term the search term to look up
     */
    @When("I search for {string}")
    public void i_search_for(String term) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(term);
        searchBox.submit();
    }

    /**
     * Asserts that the given term appears in the page source of the results.
     *
     * @param term the expected term to find in the results
     */
    @Then("I should see {string} in the results")
    public void i_should_see_in_the_results(String term) {
        assert driver.getPageSource().contains(term)
                : "Expected term '" + term + "' not found in results.";
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
