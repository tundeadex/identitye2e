package task.directory.webtest;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pmw.tinylog.Logger;
import task.directory.helpers.Screenshooter;
import task.directory.model.Cars;
import task.directory.pages.ConfirmVehiclePage;
import task.directory.pages.EnterRegistrationNumberPage;
import task.directory.pages.GetVehicleInfoPage;
import task.directory.service.DirectoryInformationServiceImpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(JUnitParamsRunner.class)
public class UiTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver_win32.exe");
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver_mac");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }



    @Test
    public void searchForCarsFromCsvUsingService() {
        DirectoryInformationServiceImpl directoryInformationService = new DirectoryInformationServiceImpl();
        List<Cars> carsFromCsv = directoryInformationService.getCarsFromCsv();

        for(Cars car : carsFromCsv) {
            Logger.info(car.toString());
            driver.navigate().to("https://www.gov.uk/get-vehicle-information-from-dvla");
            GetVehicleInfoPage getVehicleInfoPage = new GetVehicleInfoPage(driver);
            getVehicleInfoPage.startNow();
            EnterRegistrationNumberPage enterRegistrationNumberPage = new EnterRegistrationNumberPage(driver);
            enterRegistrationNumberPage.enterRegistrationNumberAndContinue(car.regNo);
            ConfirmVehiclePage confirmVehiclePage = new ConfirmVehiclePage(driver);
            Assert.assertEquals(car.make, confirmVehiclePage.getMake());
            Assert.assertEquals(car.color, confirmVehiclePage.getColor());
            String screenshotName = car.regNo + car.make + car.color;
            Screenshooter.takeScreenshot(driver, screenshotName);
        }
    }

    @Test
    @FileParameters("src/main/resources/files/list-of-cars.csv")
    public void searchForCarsFromCsvUsingParameters(String regNo, String make, String color) {
        driver.navigate().to("https://www.gov.uk/get-vehicle-information-from-dvla");
        GetVehicleInfoPage getVehicleInfoPage = new GetVehicleInfoPage(driver);
        getVehicleInfoPage.startNow();
        EnterRegistrationNumberPage enterRegistrationNumberPage = new EnterRegistrationNumberPage(driver);
        enterRegistrationNumberPage.enterRegistrationNumberAndContinue(regNo);
        ConfirmVehiclePage confirmVehiclePage = new ConfirmVehiclePage(driver);
        Assert.assertEquals(make, confirmVehiclePage.getMake());
        Assert.assertEquals(color, confirmVehiclePage.getColor());
    }
}
