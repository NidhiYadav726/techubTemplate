package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class ResourcePage {

    private static final Logger log = Logger.getLogger(ResourcePage.class.getName());
    private final Queue<WebDriver> driverPool = new LinkedList<>();
    private final int maxInstances = 5;

    public ResourcePage() {
        super();
    }

    public void preloadDrivers(int predictedInstances) {
        log.info("Preloading browser instances");
        closeAllDrivers();
        int instancesToLoad = Math.min(predictedInstances, maxInstances);
        int currentPoolSize = driverPool.size();

        // Load only if the pool doesn't already have enough drivers
        if (currentPoolSize < instancesToLoad) {
            for (int i = currentPoolSize; i < instancesToLoad; i++) {
                driverPool.add(createDriver());
            }
            log.info((instancesToLoad - currentPoolSize) + " new browser instances preloaded.");
        } else {
            log.info("No additional drivers are preloaded. Pool already has sufficient drivers.");
        }
    }

    public WebDriver acquireDriver() {
        WebDriver driver = driverPool.poll();
        if (driver == null) {
            log.info("Driver pool is empty! Unable to acquire a driver.");
        } else {
            log.info("Driver acquired. Pool size: " + driverPool.size());
        }
        return driver;
    }

    public void releaseDriver(WebDriver driver) {
        if (driver != null) {
            driverPool.add(driver);
            log.info("Driver released back to the pool. Pool size: " + driverPool.size());
        } else {
            log.info("Attempted to release a null driver.");
        }
    }

    private WebDriver createDriver() {
        return new ChromeDriver();
    }

    public void closeAllDrivers() {
        log.info("Closing all preloaded drivers");
        while (!driverPool.isEmpty()) {
            WebDriver driver = driverPool.poll();
            if (driver != null) {
                driver.quit();
                    log.info("Driver has been closed successfully.");

            }
        }
        if (driverPool.isEmpty()){
            log.info("All drivers have been closed and the pool is empty.");
        } else {
            log.warning("Some drivers remain in the pool after closing!");
        }
    }
}
