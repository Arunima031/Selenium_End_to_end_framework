package org.practice.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.practice.DriverFactory.DriverManager;
import org.practice.base.BaseTest;
import org.practice.pageObjects.LandingPage;
import org.practice.pageObjects.TraversalPage;
import org.testng.annotations.Test;

import java.util.Set;

public class TraversalPageTest extends BaseTest {

    private static final Logger LoggerUtil = LogManager.getLogger(TraversalPageTest.class);
    TraversalPage traversalPage;
    @Test(groups = "Regression")
    public void dropdownProblem(){
        page.getInstance(LandingPage.class).cancelLoginPopup();
        traversalPage=page.getInstance(TraversalPage.class);
        traversalPage.clickDropdown();
        System.out.println(traversalPage.getDropdownElements());
        String currentWindow = DriverManager.getDriver().getWindowHandle();
        traversalPage.clickElementNeeded();
        Set<String> allWindows = DriverManager.getDriver().getWindowHandles();
        for(String window:allWindows){
            if(!window.equals(currentWindow)){
                DriverManager.getDriver().switchTo().window(window);
                break;
            }
        }
        LoggerUtil.info("", DriverManager.getDriver().getCurrentUrl());
    }
}
