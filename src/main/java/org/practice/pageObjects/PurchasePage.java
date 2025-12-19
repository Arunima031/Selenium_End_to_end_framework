package org.practice.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.practice.base.BasePage;

import static org.practice.constants.constantValue.LONG_TIMEOUT;
import static org.practice.constants.constantValue.SHORT_TIMEOUT;

public class PurchasePage extends BasePage {

    By purchaseButton= By.cssSelector("[value=\"Purchase\"]");
    By autoSuggest=By.cssSelector("#country");
    By suggetions=By.cssSelector("div.suggestions");
    By termCondition=By.xpath("//a[text()='term & Conditions']");
    By popUpClose=By.cssSelector("button.btn-info");
    By popup=By.cssSelector("div.nsm-dialog");
    By purchaseSuccess=By.cssSelector("div.alert-success");
    By purchaseDismiss=By.cssSelector("[data-dismiss=\"alert\"]");

    public PurchasePage(WebDriver driver) {
        super(driver);
    }
    public void clickPurchase(){
        click(find(purchaseButton),SHORT_TIMEOUT);
    }

    public PurchasePage selectCountry(String country){
        type(find(autoSuggest),LONG_TIMEOUT,country);
        waitUntilElementIsVisible(find(suggetions),LONG_TIMEOUT,2).findElements(By.cssSelector("ul a")).stream().
                filter(item->item.getText().equalsIgnoreCase(country))
                .findFirst().orElseThrow(()-> new NoSuchElementException("Country not found :"+country)).click();

        return this;

    }
    public PurchasePage clickTermsAndCondition(){
        click(find(termCondition),LONG_TIMEOUT);
        return this;
    }

    public Boolean validatePopupDisplayed(){
        return isElementDisplayed(find(popup),LONG_TIMEOUT);
    }

    public void closePopup(){
        click(find(popUpClose),LONG_TIMEOUT);
    }

    public Boolean validateSuccessPurchase(){
        return isElementDisplayed(find(purchaseSuccess),LONG_TIMEOUT);
    }

    public String getTextOfSuccessMessage(){
        return getText(find(purchaseSuccess),LONG_TIMEOUT,2).replaceAll("\\s+", " ")
                .trim();
    }

    public void closeAlert(){
        click(find(purchaseDismiss),LONG_TIMEOUT);
    }
}
