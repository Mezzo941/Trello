package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class WebDriverFactory {

    public static WebDriver gerDriver(String browserName, String... arguments) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(new ChromeOptions().addArguments(arguments));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(new FirefoxOptions().addArguments(arguments));
        } else if (browserName.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().setup();
            return new OperaDriver(new OperaOptions().addArguments(arguments));
        } else {
            return new ChromeDriver(new ChromeOptions().addArguments(arguments));
        }
    }

}


