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

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import edu.eci.arcn.bdd.pages.FileDownloadPage;

/**
 * Step definitions for File Download BDD scenarios.
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-07
 */
public class FileDownloadSteps {

    private WebDriver driver;
    private FileDownloadPage fileDownloadPage;
    private final String downloadPath = Paths.get(
            System.getProperty("user.home"), "downloads"
    ).toString();

    /**
     * Initializes ChromeDriver with a custom download directory before each scenario.
     */
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Navigates to the file download page.
     */
    @Given("I am on the file download page")
    public void i_am_on_the_file_download_page() {
        driver.get("https://the-internet.herokuapp.com/download");
        fileDownloadPage = new FileDownloadPage(driver);
    }

    /**
     * Clicks the download link for the specified file.
     *
     * @param filename the name of the file to download
     */
    @When("I click to download the file {string}")
    public void i_click_to_download_the_file(String filename) {
        fileDownloadPage.clickDownloadLink(filename);
    }

    /**
     * Asserts that the downloaded file exists in the downloads directory.
     *
     * @param filename the expected downloaded filename
     * @throws InterruptedException if the polling wait is interrupted
     */
    @Then("the file {string} should exist in the downloads folder")
    public void the_file_should_exist_in_the_downloads_folder(String filename)
            throws InterruptedException {
        File downloadedFile = new File(downloadPath, filename);
        long timeout = System.currentTimeMillis() + 10_000;
        while (!downloadedFile.exists() && System.currentTimeMillis() < timeout) {
            Thread.sleep(500);
        }
        assertTrue("File was not downloaded: " + filename, downloadedFile.exists());
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
