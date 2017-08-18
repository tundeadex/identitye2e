package task.directory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EnterRegistrationNumberPage {

    private final WebDriver driver;

    public EnterRegistrationNumberPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterRegistrationNumberAndContinue(String regNo) {
        WebElement regNoTextField = driver.findElement(By.cssSelector("#Vrm"));
        regNoTextField.sendKeys(regNo);
        WebElement continueButton = driver.findElement(By.cssSelector("button[name='Continue']"));
        continueButton.click();
    }
}
