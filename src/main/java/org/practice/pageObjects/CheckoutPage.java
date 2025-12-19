package org.practice.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.practice.base.BasePage;

import java.util.List;

import static org.practice.constants.constantValue.LONG_TIMEOUT;

public class CheckoutPage extends BasePage {
    By itemAddedNames = By.xpath("//tr/td[@class='col-sm-8 col-md-6']//div[@class='media-body']/h4/a");
    By checkoutButton=By.cssSelector(".btn.btn-success");
    By continueShopButton=By.cssSelector(".btn.btn-default");
    By totalAmount=By.cssSelector("td.text-right");
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public Boolean validateItemPresentOnList(String productName) {
        return getDriver().findElements(itemAddedNames).stream().anyMatch(item -> item.getText().contains(productName));
    }

    public int getSizeOfList() {
        return getDriver().findElements(itemAddedNames).size();
    }

    public String getPriceOfAddedItem(String productName) {
        List<WebElement> items = getDriver().findElements(itemAddedNames);
        for (WebElement item : items) {
            if (item.getText().equals(productName)) {
                return item.findElement(By.xpath(".//ancestor::td/following-sibling::td[2]")).getText().split(" ")[1];
            }
        }
        return null;
    }

    public PurchasePage clickCheckoutButton(){
        click(find(checkoutButton),LONG_TIMEOUT);
        return new PurchasePage(getDriver());
    }
    public ItemsDisplayPage clickContinueShoppingButton(){
        click(find(continueShopButton),LONG_TIMEOUT);
        return new ItemsDisplayPage(getDriver());
    }

    public String getTotalAmount(){
        return getText(find(totalAmount),LONG_TIMEOUT,1).split(" ")[1];
    }
}
