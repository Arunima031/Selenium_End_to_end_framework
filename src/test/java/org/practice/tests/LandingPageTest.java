package org.practice.tests;

import org.practice.base.BaseTest;
import org.practice.dataProviders.LoginDataProvider;
import org.practice.pageObjects.ItemsDisplayPage;
import org.practice.pageObjects.LandingPage;
import org.practice.pojoClasses.webPojo.LoginScenario;
import org.practice.utilsForTests.RetryAnalyser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPageTest extends BaseTest {

    ItemsDisplayPage itemDisplayPage;
    LandingPage landingPage;
    @Test(dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class,
            groups={"Smoke","Regression"},retryAnalyzer = RetryAnalyser.class)
    public void loginTest(LoginScenario data){
      landingPage= page.getInstance(LandingPage.class).typeUserName(data.getUsername())
               .typePassword(data.getPassword()).selectRadioItems(data.getRole());
        if (data.isRequiresModalOk()) {
          landingPage.clickOkOnModal();
      }
        if (data.getAcceptTerms()) {
            landingPage.clickTermsAndCondition();
        }

        if ("ItemDisplay".equals(data.getExpectedPage())) {
            itemDisplayPage = landingPage.clickLoginButton();
            Assert.assertTrue(itemDisplayPage.isItemPageDisplayed());
        } else {
            landingPage.clickLoginButton();
            Assert.assertTrue(landingPage.validateErrorMessage());
            Assert.assertEquals(
                    landingPage.getTextOfAlert(),
                    data.getExpectedToast()
            );
        }
    }
}
