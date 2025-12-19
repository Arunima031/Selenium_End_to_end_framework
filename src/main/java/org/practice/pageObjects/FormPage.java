package org.practice.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.practice.base.BasePage;

import static org.practice.constants.constantValue.LONG_TIMEOUT;
import static org.practice.constants.constantValue.SHORT_TIMEOUT;

public class FormPage extends BasePage {

    private final By nameInput=By.name("name");
    private final By emailInput=By.name("email");
    private final By passwordInput=By.id("exampleInputPassword1");
    private final By checkBox=By.id("exampleCheck1");
    private final By radio=By.id("inlineRadio2");
    private final By genderDropdown=By.id("exampleFormControlSelect1");
    private final By submitButton=By.cssSelector("[value='Submit']");
    private final By successMessage=By.cssSelector(".alert.alert-success.alert-dismissible");
    private final By disabledRadio=By.cssSelector("#inlineRadio3");
    private final By homeTab=By.xpath("//a[text()='Home']");
    private final By shopTab=By.xpath("//a[text()='Shop']");

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public FormPage enterFormDetails(){
     type(find(nameInput),SHORT_TIMEOUT,"Arunima");
     type(find(emailInput),SHORT_TIMEOUT,"abc@example.com");
     type(find(passwordInput),SHORT_TIMEOUT,"abcx123");
     click(find(checkBox),SHORT_TIMEOUT);
     click(find(radio),SHORT_TIMEOUT);
     return this;
    }

    public FormPage selectGender(){
        Select sel =new Select(find(genderDropdown));
        sel.selectByVisibleText("Female");
        return this;
    }

    public FormPage submitForm(){
        scrollIntoView(submitButton,LONG_TIMEOUT);
        click(find(submitButton),SHORT_TIMEOUT);
        return this;
    }

    public Boolean validateSuccess(){
        return isElementDisplayed(find(successMessage),SHORT_TIMEOUT);
    }

    public String getSucessText(){
        return getText(find(successMessage),SHORT_TIMEOUT,1);
    }

    public Boolean validateRadioDisabled(){
        return isElementEnabled(find(disabledRadio),SHORT_TIMEOUT);
    }

    public ItemsDisplayPage goToShopPage(){
        click(find(shopTab),LONG_TIMEOUT);
        return new ItemsDisplayPage(getDriver());
    }

    public void goToHomePage(){
        click(find(homeTab),LONG_TIMEOUT);
    }


}
