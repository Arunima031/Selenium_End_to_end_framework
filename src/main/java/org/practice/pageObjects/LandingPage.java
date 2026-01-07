package org.practice.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.practice.base.BasePage;
import org.practice.properties.ConfigureProperties;
import org.practice.utilities.ExtentLoggerUtil;

import java.util.List;

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

    @FindBy(css="#username")
    private WebElement usernameField;

    @FindBy(css="#password")
    private WebElement passwordField;

    @FindBy(css="[name='signin']")
    private WebElement loginBtn;

    @FindBy(css="[name=\"terms\"]")
    private WebElement termsAndCondition;

    @FindBy(xpath="//label[@class='customradio']/span[@class=\"radiotextsty\"]")
    private List<WebElement> radioItems;

    @FindBy(css="div.alert-danger")
    private WebElement incorrectPassword;

    @FindBy(css="#okayBtn")
    private WebElement okBtn;

    public void goTo(){
        getDriver().get(ConfigureProperties.getProperty("url"));

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

    public TraversalPage clickTraversalMenu(){
        return getInstance(TraversalPage.class);
    }

    public LandingPage typeUserName(String username){
        type(usernameField,LONG_TIMEOUT,username);
        ExtentLoggerUtil.info("Typed username");
        return this;
    }

    public LandingPage typePassword(String password){
          type(passwordField,LONG_TIMEOUT,password);
          ExtentLoggerUtil.info("Typed in password");
          return this;
    }

    public ItemsDisplayPage clickLoginButton(){
        click(loginBtn,LONG_TIMEOUT);
        ExtentLoggerUtil.info("Clicked login");
       return getInstance(ItemsDisplayPage.class);
    }

    public LandingPage selectRadioItems(String option){
                radioItems.stream().filter(s->s.getText().trim().equalsIgnoreCase(option)).findFirst()
                        .orElseThrow(() ->
                new RuntimeException("Radio option not found: " + option)
        ).findElement(By.xpath("./following-sibling::span[@class='checkmark']")).click();
                ExtentLoggerUtil.info("Selected radio option : "+option);
                return this;
    }

    public LandingPage clickTermsAndCondition(){
        click(termsAndCondition,LONG_TIMEOUT);
        ExtentLoggerUtil.info("clicked terms and condition checkbox");
        return this;
    }

    public String getTextOfAlert(){
        return getText(incorrectPassword,LONG_TIMEOUT,2);
    }

    public Boolean validateErrorMessage(){
        return isElementDisplayed(incorrectPassword,LONG_TIMEOUT);
    }

    public void clickOkOnModal(){
        click(okBtn,LONG_TIMEOUT);
    }
}
