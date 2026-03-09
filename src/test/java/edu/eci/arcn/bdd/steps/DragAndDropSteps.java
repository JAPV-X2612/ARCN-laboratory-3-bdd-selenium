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

import edu.eci.arcn.bdd.pages.DragAndDropPage;

import java.time.Duration;

/**
 * Step definitions for Drag and Drop BDD scenarios.
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-07
 */
public class DragAndDropSteps {

    private WebDriver driver;
    private DragAndDropPage dragAndDropPage;

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
     * Navigates to the drag and drop page.
     */
    @Given("I am on the drag and drop page")
    public void i_am_on_the_drag_and_drop_page() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        dragAndDropPage = new DragAndDropPage(driver);
    }

    /**
     * Performs the drag action from column A to column B.
     */
    @When("I drag element A to element B")
    public void i_drag_element_a_to_element_b() {
        dragAndDropPage.dragAtoB();
    }

    /**
     * Asserts that column A now holds the label "B" after the drag.
     */
    @Then("element A should be in the position of element B")
    public void element_a_should_be_in_the_position_of_element_b() {
        assertEquals("B", dragAndDropPage.getColumnAHeader());
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
