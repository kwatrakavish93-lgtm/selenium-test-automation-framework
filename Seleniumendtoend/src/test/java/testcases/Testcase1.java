package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Testcase1 {

   @Test
    public void Tc01(){

       WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();
       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
       driver.get("https://www.amazon.com");
       driver.manage().window().maximize();

       driver.quit();

   }

}
