package step_definitions;



import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ResourcePage;
import utils.ResourceMonitor;

import java.util.logging.Logger;

    public class ResourceManagementStepDefinition {

        private static final Logger log = Logger.getLogger(ResourceManagementStepDefinition.class.getName());
        private ResourceMonitor resourceMonitor;
        private ResourcePage resourcePage;
        private int predictedInstances;

        @Given("the AI model is loaded successfully")
        public void theAIModelIsLoadedSuccessfully() throws Exception {
            log.info("Loading AI model...");
            resourceMonitor = new ResourceMonitor();
            Assert.assertNotNull(resourceMonitor, "AI Model failed to load.");
        }

        @When("optimal browser instances are predicted")
        public void optimalBrowserInstancesArePredicted() throws Exception {
            log.info("Predicting optimal instances...");
            predictedInstances = resourceMonitor.predictOptimalInstances();
            Assert.assertTrue(predictedInstances > 0, "Prediction failed.");
        }

        @Then("drivers are preloaded based on the prediction")
        public void driversArePreloadedBasedOnThePrediction() {
            log.info("Preloading browser instances");
            resourcePage = new ResourcePage();
            resourcePage.preloadDrivers(predictedInstances);
        }

        @And("tests are executed on the preloaded drivers")
        public void testsAreExecutedOnThePreloadedDrivers() {
            log.info("Executing tests");
            for (int i = 1; i <= 5; i++) {
                var driver = resourcePage.acquireDriver();
                if (driver != null) {
                    driver.get("https://saucelabs.com");
                    log.info("Test #" + i + " executed.");
                    resourcePage.releaseDriver(driver);
                } else {
                    log.warning("No driver available for Test #" + i);
                }
            }
        }

        @And("all drivers are closed after test execution")
        public void allDriversAreClosedAfterTestExecution() {
            log.info("Closing all drivers");
            resourcePage.closeAllDrivers();
        }
    }

