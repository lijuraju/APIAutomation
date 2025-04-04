package org.APIAutomation.Apr2025;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "C:\\Personal\\APIAutomation\\src\\test\\java\\org\\APIAutomation\\Apr2025\\features",
        glue = "org/APIAutomation/Apr2025/stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

