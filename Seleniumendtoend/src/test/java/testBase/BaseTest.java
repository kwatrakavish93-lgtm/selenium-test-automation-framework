package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigReader;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import static utilities.ConfigReader.prop;

public class BaseTest {
    public WebDriver driver;
    @BeforeMethod
    public void setUp() {
        ConfigReader.initProperties();

        String browserName = prop.getProperty("browser");
        System.out.println("Tests are running in browser" + browserName);

        boolean isHeadless = Boolean.parseBoolean(prop.getProperty("headless", "false"));

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                System.out.println("Running tests in headless mode");
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) {
                System.out.println("Running tests in headless mode");
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            //Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
