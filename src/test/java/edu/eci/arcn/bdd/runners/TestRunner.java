package edu.eci.arcn.bdd.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * JUnit test runner for Cucumber BDD scenarios.
 *
 * @author Jesús Pinzón
 * @version 1.0
 * @since 2026-03-07
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/edu/eci/arcn/bdd/features",
        glue = "edu.eci.arcn.bdd.steps",
        monochrome = true,
        publish = true,
        plugin = {
                "pretty",
                "junit:target/JUnitReports/report.xml",
                "json:target/JSonReports/report.json",
                "html:target/HtmlReports/report.html"
        }
)
public class TestRunner {
}
