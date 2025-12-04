package org.practice.base;


import org.openqa.selenium.WebDriver;
import org.practice.DriverFactory.DriverClass;
import org.practice.pageObjects.LandingPage;
import org.practice.resources.ConfigureProperties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    protected WebDriver driver;
//    protected LandingPage landingPage;
    public Page page;
    @BeforeMethod(alwaysRun = true)
    public void initialiseDriver(){
        String browserName = ConfigureProperties.getProperty("browser");
        driver= DriverClass.getDriver(browserName);
        page=new BasePage(driver);
        launchApp();
    }

    public void launchApp(){
      page.getInstance(LandingPage.class).goTo();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
