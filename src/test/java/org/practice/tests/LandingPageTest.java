package org.practice.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.practice.base.BaseTest;
import org.practice.pageObjects.LandingPage;
import org.practice.utilsForTests.AssertionUtils;
import org.practice.utilsForTests.RetryAnalyser;
import org.testng.annotations.Test;

public class LandingPageTest extends BaseTest {
    private static final Logger LoggerUtil = LogManager.getLogger(LandingPageTest.class);

    @Test(groups={"Smoke","Regression"},retryAnalyzer = RetryAnalyser.class)
    public void loginTest(){
        page.getInstance(LandingPage.class).cancelLoginPopup();
        AssertionUtils.assertElementNotVisible(false);
    }


}
