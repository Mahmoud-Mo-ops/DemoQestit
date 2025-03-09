package tests.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;  
import org.testng.annotations.Test;

@CucumberOptions(
    features = "src/main/resources",
    glue = "tests",
    monochrome = true,
    plugin = {"html:target/cucumber.html"}
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
