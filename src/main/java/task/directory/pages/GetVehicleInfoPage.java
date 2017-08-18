package task.directory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GetVehicleInfoPage {

    private final WebDriver driver;

    public GetVehicleInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void startNow() {
        WebElement startNowButton = driver.findElement(By.cssSelector("#get-started a"));
        startNowButton.click();
    }
}
