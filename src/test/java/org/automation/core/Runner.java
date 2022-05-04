package org.automation.core;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        plugin = {"json:target/cucumber-report/cucumber.json"}
)

public class Runner extends AbstractTestNGCucumberTests {


}
