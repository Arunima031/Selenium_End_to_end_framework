package org.practice.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.practice.DriverFactory.DriverManager;
import org.practice.utilities.ExtentLoggerUtil;

import java.time.Duration;

public class BasePage extends Page {

    protected BasePage(WebDriver driver){
        super(driver);
    }

    @Override
    public WebElement find(By locator) {
        return DriverManager.getDriver().findElement(locator);
    }
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
    public String getText(WebElement element, int timeout, int pollingTime){
        System.out.println(waitUntilElementIsVisible(element,timeout,pollingTime).getText());
        return waitUntilElementIsVisible(element,timeout,pollingTime).getText();
    };

    @Override
    public boolean isElementDisplayed(WebElement element, int timeout){
        return waitUntilElementIsVisible(element,timeout).isDisplayed();
    }
    @Override
    public boolean isElementSelected(WebElement element, int timeout){
        return waitUntilElementIsVisible(element,timeout).isSelected();
    }

    public boolean isElementEnabled(WebElement element, int timeout){
        return waitUntilElementIsVisible(element,timeout).isEnabled();
    }

    public String getUrlOfPage(){
      return getDriver().getCurrentUrl();
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

    public void scrollIntoView(By locator, int timeout) {
        WebElement element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) DriverManager.getDriver())
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public void safeClick(By locator){
        click(find(locator),10);
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        }
        catch (TimeoutException e){
            ExtentLoggerUtil.info("Alert never came");
        }

    }

    public void action(WebElement element){
        Actions a=new Actions(getDriver());
        a.keyDown(element,Keys.F1);
    }



}

