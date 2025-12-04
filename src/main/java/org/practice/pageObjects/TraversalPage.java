package org.practice.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.practice.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class TraversalPage extends BasePage {
   public TraversalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//span[text()='Corporates']")
    private WebElement dropDownElement;

    @FindBy(css=".MuiBox-root.css-iayqfd p")
    private List<WebElement> dropDownElements;

    public void clickDropdown(){
        click(dropDownElement,10);
    }

    public ArrayList<String> getDropdownElements(){
        ArrayList<String> al=new ArrayList<>();
      for(WebElement element:dropDownElements){
          al.add(element.getText().trim());
      }
      return al;
    }

    public void clickElementNeeded(){
        ArrayList<String> al=new ArrayList<>();
        for(WebElement element:dropDownElements){
            al.add(element.getText().trim());
            if(element.getText().trim().contains("Corporate Travel")){
                element.click();
        }
    }
    }
}
