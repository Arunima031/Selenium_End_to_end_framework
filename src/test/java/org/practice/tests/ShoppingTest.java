package org.practice.tests;

import org.practice.base.BaseTest;
import org.practice.pageObjects.CheckoutPage;
import org.practice.pageObjects.FormPage;
import org.practice.pageObjects.ItemsDisplayPage;
import org.practice.pageObjects.PurchasePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingTest extends BaseTest {
ItemsDisplayPage itemPage;
CheckoutPage checkoutPage;
PurchasePage purchasePage;
    @Test(groups="Shopping")
    public void shoppingTest(){
        String purchaseItem="iphone X";
        itemPage= page.getInstance(FormPage.class).goToShopPage();
        System.out.println( itemPage.getUrlOfPage());
        checkoutPage=itemPage.addItem(purchaseItem).clickCheckout();
        Assert.assertTrue(checkoutPage.validateItemPresentOnList(purchaseItem));
        Assert.assertEquals(checkoutPage.getSizeOfList(),1,"Extra items added");
        String price=checkoutPage.getPriceOfAddedItem(purchaseItem);
        Assert.assertEquals(price,"100000","Price does not match");
        Assert.assertEquals(checkoutPage.getPriceOfAddedItem(purchaseItem),checkoutPage.getTotalAmount());
        purchasePage=checkoutPage.clickCheckoutButton();
        purchasePage.selectCountry("India");
        Assert.assertTrue(purchasePage.clickTermsAndCondition().validatePopupDisplayed());
        purchasePage.closePopup();
        purchasePage.clickPurchase();
        Assert.assertTrue(purchasePage.validateSuccessPurchase());
        Assert.assertTrue(purchasePage.getTextOfSuccessMessage().contains("Success! Thank you! Your order will be delivered in next few weeks :-)."));
        purchasePage.closeAlert();

    }

    @Test(groups="Shopping")
    public void addingMultipleItemsToCart(){
        String purchaseItem1="iphone X";
        String purchaseItem2="Samsung Note 8";
        itemPage= page.getInstance(FormPage.class).goToShopPage();
        System.out.println( itemPage.getUrlOfPage());
        checkoutPage=itemPage.addItem(purchaseItem1).clickCheckout();
        Assert.assertTrue(checkoutPage.validateItemPresentOnList(purchaseItem1));
        Assert.assertEquals(checkoutPage.getSizeOfList(),1,"Extra items added");
        checkoutPage.clickContinueShoppingButton().addItem(purchaseItem2).clickCheckout();
        Assert.assertEquals(checkoutPage.getSizeOfList(),1,"Extra items added");


    }
}
