package cucumber_options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

  @CucumberOptions(
            features = "src/test/java/feature",
            glue = "step_definitions",
            plugin = {"pretty", "html:target/cucumber-reports.html"},
            monochrome = true
    )
    public class TestRunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = false)
        public Object[][] scenarios() {
            return super.scenarios();
        }
    }

