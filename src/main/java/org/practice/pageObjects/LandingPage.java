package org.practice.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.practice.base.BasePage;
import org.practice.resources.ConfigureProperties;

import static org.practice.constants.constantValue.LONG_TIMEOUT;

public class LandingPage extends BasePage {
    public LandingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="span[class*='style_cross__q1ZoV'] img[src*='close']")
    private WebElement closeElement;

    @FindBy(css="[name='close']")
    private WebElement closeForAd;

    public void goTo(){
        driver.get(ConfigureProperties.getProperty("url"));

    }

    public LandingPage cancelLoginPopup(){
        try {
            click(closeElement, LONG_TIMEOUT);
        }
        catch (Exception e){
            System.out.println("Login popup is not available");
        }
        return this;
    }

    public TraversalPage clickTraversLEmENUnu(){

        return getInstance(TraversalPage.class);
    }
}
