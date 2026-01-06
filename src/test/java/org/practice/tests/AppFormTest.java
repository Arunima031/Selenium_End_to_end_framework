package org.practice.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.practice.base.BaseTest;
import org.practice.pageObjects.FormPage;
import org.practice.utilities.ExtentLoggerUtil;
import org.practice.utilsForTests.AssertionUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppFormTest extends BaseTest {
    private static final Logger LoggerUtil = LogManager.getLogger(AppFormTest.class);
    FormPage formPage;
    @Test(groups="Form")
    public void formTest(){

    formPage=page.getInstance(FormPage.class).enterFormDetails().selectGender().submitForm();
    AssertionUtils.assertElementVisible(formPage.validateSuccess());
    AssertionUtils.assertTextContains(formPage.getSucessText(),"Success! The Form has been submitted successfully!." );
    Assert.assertFalse(formPage.validateRadioDisabled());
    }

    @Test(groups={"Form"})
    public void navigation(){
        formPage=page.getInstance(FormPage.class);
        formPage.goToShopPage();
        ExtentLoggerUtil.info(formPage.getUrlOfPage());
        AssertionUtils.assertText(formPage.getUrlOfPage(),"https://rahulshettyacademy.com/angularpractice/shop");
        formPage.goToHomePage();
        ExtentLoggerUtil.info(formPage.getUrlOfPage());
        AssertionUtils.assertText(formPage.getUrlOfPage(),"https://rahulshettyacademy.com/angularpractice/");
    }
}
