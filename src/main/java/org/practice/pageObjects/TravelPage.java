package org.practice.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.practice.base.BasePage;

public class TravelPage extends BasePage {
    protected TravelPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[alt=\"Travel Policy Customisation\"]")
    private WebElement travelPolicy;
}
