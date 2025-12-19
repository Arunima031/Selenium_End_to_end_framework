package org.practice.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.practice.base.BasePage;

import static org.practice.constants.constantValue.LONG_TIMEOUT;


public class ItemsDisplayPage extends BasePage {

    By itemCards=By.cssSelector(".card");
    By itemText=By.cssSelector(".card-title a");
    By addButton=By.cssSelector(".card-footer button");
    By checkoutButton=By.xpath("//a[contains(text(),'Checkout')]");
    public ItemsDisplayPage(WebDriver driver) {
        super(driver);
    }

    public ItemsDisplayPage addItem(String purchaseItem){
        getDriver().findElements(itemCards).stream()
                .filter(item -> item.findElement(itemText).getText().contains(purchaseItem))
                .findFirst().ifPresentOrElse(item->item.findElement(addButton).click(),
                        () -> {
                            throw new NoSuchElementException(
                                    "Item not found: " + purchaseItem);
                });

//        for(WebElement item:items){
//            String itemText=item.findElement(By.cssSelector(".card-body .card-title a")).getText();
//            if(itemText.contains(purchaseItem)){
//                item.findElement(By.cssSelector(".card-footer button")).click();
//                break;
//            }
        return this;
        }

        public CheckoutPage clickCheckout(){
        click(find(checkoutButton),LONG_TIMEOUT);
        return new CheckoutPage(getDriver());
        }
}
