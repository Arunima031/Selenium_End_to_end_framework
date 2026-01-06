package org.practice.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.practice.base.BasePage;
import org.practice.utilities.ExtentLoggerUtil;
import org.practice.utilities.LoggerUtil;

import java.time.Duration;

import static org.practice.constants.constantValue.LONG_TIMEOUT;

public class MultiPracticePage extends BasePage {

    By multiWindow=By.cssSelector("button#openwindow");
    By multiTab=By.cssSelector("fieldset a#opentab");
    By qaClickAllCoursesButton=By.xpath("//a[text()='Access all our Courses']");

    public MultiPracticePage(WebDriver driver) {
        super(driver);
    }

    public void multiWindowHandle(){
        ExtentLoggerUtil.info(getDriver().getTitle());
        click(find(multiWindow),LONG_TIMEOUT);
        String parentWindow=getDriver().getWindowHandle();
        WebDriverWait wait=new WebDriverWait(getDriver(), Duration.ofSeconds(LONG_TIMEOUT));
        wait.until(d->d.getWindowHandles().size()>1);
        for(String windowHandle:getDriver().getWindowHandles()){
         if(!windowHandle.equals(parentWindow)){
           getDriver().switchTo().window(windowHandle);
             ExtentLoggerUtil.info("Switch to new Window : "+ getDriver().getTitle());
           return;
         }
        }
        LoggerUtil.info(getDriver().getTitle());
     throw new RuntimeException("No new window found");
    }

    public Boolean isCoursesButtonDisplayed(){
        return isElementDisplayed(find(qaClickAllCoursesButton),LONG_TIMEOUT);
    }

    public String multiTabHandle(){
        String parentTab=getDriver().getWindowHandle();
        click(find(multiTab),LONG_TIMEOUT);
        WebDriverWait wait= new WebDriverWait(getDriver(),Duration.ofSeconds(LONG_TIMEOUT));
        wait.until(d->d.getWindowHandles().size()>1);
        for(String handle:getDriver().getWindowHandles()){
            if(!handle.equals(parentTab)){
                getDriver().switchTo().window(handle);
                ExtentLoggerUtil.info("Switch to new Tab : "+ getDriver().getTitle());
                return parentTab;
            }
        }
       throw new RuntimeException("Tab unavailable");
    }

    public void backToParent(String parentTab){
        getDriver().switchTo().window(parentTab);
        ExtentLoggerUtil.info("Switching back to parent : "+getDriver().getTitle());
    }
}
