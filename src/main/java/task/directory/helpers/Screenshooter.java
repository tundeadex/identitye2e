package task.directory.helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshooter {

    public static void takeScreenshot(WebDriver driver, String filename) {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            String path = System.getProperty("user.dir");
            FileUtils.copyFile(scrFile, new File(path + "/" + "src/test/resources/screenshots/" + filename + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
