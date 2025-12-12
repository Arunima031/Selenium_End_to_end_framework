package org.practice.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage extends Page {

    protected BasePage(WebDriver driver){
        super(driver);
    };

   @Override
   public void click(WebElement element,int timeout){
        waitUntilElementIsClickable(element,timeout).click();
   };

   @Override
   public void click(WebElement element,int timeout,int pollingTime){
       waitUntilElementIsClickable(element,timeout,pollingTime).click();
   }

    @Override
    public void type(WebElement element,int timeout,String text){
        waitUntilElementIsVisible(element,timeout).sendKeys(text);
    };

    @Override
    public  void getText(WebElement element,int timeout,int pollingTime){
        waitUntilElementIsVisible(element,timeout,pollingTime).getText();
    };

    @Override
    public void isElementDisplayed(WebElement element,int timeout){
        waitUntilElementIsVisible(element,timeout).isDisplayed();
    }
    @Override
    public void isElementSelected(WebElement element,int timeout){
        waitUntilElementIsVisible(element,timeout).isSelected();
    }

    @Override
    public WebElement waitUntilElementIsVisible(WebElement element, int timeout){
        WebDriverWait wait= new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public WebElement waitUntilElementIsVisible(WebElement element, int timeout, int pollingTime){
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class).ignoring(TimeoutException.class);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public WebElement waitUntilElementIsClickable(WebElement element, int timeout){
        WebDriverWait wait= new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public WebElement waitUntilElementIsClickable(WebElement element, int timeout, int pollingTime){
        FluentWait<WebDriver> wait = new FluentWait<>(getDriver()).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class).ignoring(TimeoutException.class);
       return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}

