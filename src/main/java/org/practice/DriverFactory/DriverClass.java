package org.practice.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverClass {

    public static WebDriver getDriver(String browser){
        WebDriver driver;
        switch(browser.toLowerCase()){
            case "chrome":
                ChromeOptions option =new ChromeOptions();
                option.addArguments("--disable-notifications");
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
