package org.practice.base;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.practice.DriverFactory.DriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public abstract class Page {

    protected Page(WebDriver driver){
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }


    public abstract void click(WebElement element, int timeout);

    public abstract void click(WebElement element,int timeout,int pollingTime);


    public abstract void type(WebElement element,int timeout,String text);

    public abstract void getText(WebElement element,int timeout,int pollingTime);

    public abstract void isElementDisplayed(WebElement element,int timeout);
    public abstract void isElementSelected(WebElement element,int timeout);

    public abstract WebElement waitUntilElementIsVisible(WebElement element, int timeout);

    public abstract WebElement waitUntilElementIsVisible(WebElement element, int timeout, int pollingTime);

    public abstract WebElement waitUntilElementIsClickable(WebElement element, int timeout);
    public abstract WebElement waitUntilElementIsClickable(WebElement element, int timeout, int pollingTime);

    public <T> T getInstance(Class<T> tclass) {
        try {
            return tclass.getDeclaredConstructor(WebDriver.class).newInstance(getDriver());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not create instance of: " + tclass.getName(), e);
        }
    }

    public static String captureScreenshot(String methodName) {
        WebDriver driver = DriverManager.getDriver(); // ThreadLocal-safe driver
        if (driver == null) {
            System.out.println("Driver is null, cannot capture screenshot!");
            return null;
        }
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = methodName
                    + "_T" + Thread.currentThread().getId()
                    + "_" + System.currentTimeMillis()
                    + ".png";

            Path destPath = Path.of(System.getProperty("user.dir"), "reports", "screenshots", fileName);
            Files.createDirectories(destPath.getParent());
            Files.copy(src.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

            return destPath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
