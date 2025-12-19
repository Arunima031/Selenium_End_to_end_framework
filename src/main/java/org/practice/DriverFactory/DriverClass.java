package org.practice.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.practice.utilities.DownloadUtility;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverClass {

    public static WebDriver getDriver(String browser){
        WebDriver driver;
        switch(browser.toLowerCase()){
            case "chrome":
                String downloadDir = DownloadUtility.getDownloadDir();
                ChromeOptions option =new ChromeOptions();
                option.addArguments("--disable-notifications");
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.default_directory", downloadDir);
                prefs.put("download.prompt_for_download", false);
                prefs.put("safebrowsing.enabled", true);

                option.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(option);
                break;
            case "firefox":
                FirefoxOptions options=new FirefoxOptions();
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
                driver=new FirefoxDriver(options);
                break;
            case "edge" :
                driver=new EdgeDriver();
                break;
            default:
               throw new IllegalArgumentException("browser is not supported");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
}
