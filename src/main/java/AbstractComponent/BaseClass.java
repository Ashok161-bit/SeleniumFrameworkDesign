package AbstractComponent;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

    WebDriver driver;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForEleToAppear(By findBy) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForEleToAppear(WebElement element) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForEleToClickAble(WebElement element) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForEleToClickAble(By locator) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForEleToDisApper(WebElement ele) throws InterruptedException {
        Thread.sleep(4000); // Wait for 4 seconds
        // Uncomment the following if you want to use WebDriverWait instead of Thread.sleep
        // WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        // w.until(ExpectedConditions.invisibilityOf(ele));
    }
}
