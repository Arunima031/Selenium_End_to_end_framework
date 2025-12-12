package org.practice.base;


import org.openqa.selenium.WebDriver;
import org.practice.DriverFactory.DriverClass;
import org.practice.DriverFactory.DriverManager;
import org.practice.pageObjects.LandingPage;
import org.practice.resources.ConfigureProperties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    public Page page;
    @BeforeMethod(alwaysRun = true)
    public void initialiseDriver(){
        String browserName = ConfigureProperties.getProperty("browser");
        WebDriver driver = DriverClass.getDriver(browserName);
        DriverManager.setDriver(driver);
        page=new BasePage(driver);
        launchApp();
    }

    public void launchApp(){
      page.getInstance(LandingPage.class).goTo();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }


}
