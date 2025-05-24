package cucumber;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/cucumber", glue = "StepDefinition", monochrome = true,tags="@Regression", plugin = {
        "pretty", "html:target/cucumber.html"})
public class TestNgTestRunner extends AbstractTestNGCucumberTests {
    
}
