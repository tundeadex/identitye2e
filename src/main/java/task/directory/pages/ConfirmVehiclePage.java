package task.directory.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmVehiclePage {

    private final WebDriver driver;

    public ConfirmVehiclePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMake() {
        WebElement make = driver.findElement(By.xpath("//*[@id='pr3']/div/ul/li[2]/span[2]/strong"));
        return make.getText();
    }

    public String getColor() {
        WebElement color = driver.findElement(By.xpath("//*[@id='pr3']/div/ul/li[3]/span[2]/strong"));
        return color.getText();
    }
}
