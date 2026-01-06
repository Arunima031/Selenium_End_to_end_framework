package org.practice.tests;

import org.practice.DriverFactory.DriverManager;
import org.practice.base.BaseTest;
import org.practice.pageObjects.MultiPracticePage;
import org.practice.pageObjects.UploadPage;
import org.practice.utilities.DownloadUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class PracticeTest extends BaseTest {
UploadPage uploadPage;
MultiPracticePage multiPracticePage;
    @Test(groups = "Practice")
    public void uploadTest(){
       uploadPage=page.getInstance(UploadPage.class);
       Assert.assertTrue(uploadPage.validateHeaderIsVisible());
       Assert.assertTrue(uploadPage.validateWebTableDisplayed());
       Assert.assertTrue(uploadPage.uploadDoc().validateSuccessMessage());
    }

    @Test(groups = "Practice")
    public void downloadTest(){
        uploadPage=page.getInstance(UploadPage.class);
            String downloadDir = DownloadUtility.getDownloadDir();
        File file = uploadPage.downloadFile(downloadDir);
            Assert.assertTrue(file.exists());
            Assert.assertTrue(file.length() > 0);
        DownloadUtility.cleanDownloadDir();
    }

    @Test(groups = "Practice")
    public void validateWebTable(){

        uploadPage=page.getInstance(UploadPage.class);
        Assert.assertTrue(uploadPage.validateHeaderIsVisible());
        Assert.assertTrue(uploadPage.validateWebTableDisplayed());
        Assert.assertEquals(uploadPage.getValue("Mango",3),"Yellow",
                "Colour of fruit does not match");
        Assert.assertEquals(uploadPage.getValue("Apple",4),"345",
                "Price of fruit does not match");

        Assert.assertEquals(uploadPage.getValue("Papaya",5),"Spring",
                "Season of fruit does not match");

        Assert.assertEquals(uploadPage.getValue("Orange",3),"Orange",
                "Color of fruit does not match");

        Assert.assertEquals(uploadPage.getValue("Kivi",5),"Winter",
                "Season of fruit does not match");
    }

    @Test(groups = {"Practice","MultiWindow"})
    public void MultiWindows(){
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        multiPracticePage=page.getInstance(MultiPracticePage.class);
        multiPracticePage.multiWindowHandle();
        Assert.assertTrue(multiPracticePage.isCoursesButtonDisplayed());
    }

    @Test(groups = {"Practice","MultiWindow"})
    public void MultiTabs(){
        DriverManager.getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
        multiPracticePage=page.getInstance(MultiPracticePage.class);
        String parentTab=multiPracticePage.multiTabHandle();
        Assert.assertTrue(multiPracticePage.isCoursesButtonDisplayed());
        multiPracticePage.backToParent(parentTab);
        Assert.assertEquals(multiPracticePage.getUrlOfPage(),"https://rahulshettyacademy.com/AutomationPractice/");

    }

}
