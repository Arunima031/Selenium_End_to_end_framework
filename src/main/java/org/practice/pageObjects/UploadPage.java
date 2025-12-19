package org.practice.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.practice.base.BasePage;
import org.practice.utilities.DownloadUtility;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.practice.constants.constantValue.LONG_TIMEOUT;

public class UploadPage extends BasePage {

    By headerPage=By.tagName("h1");
    By webTable=By.cssSelector("[role=\"table\"]");
    By downloadButton= By.id("downloadButton");
    By uploadButton=By.id("fileinput");
    By uploadSuccess=By.xpath("//div[contains(@class,'Toastify__toast--success')]//div[contains(text(),'Updated Excel Data Successfully')]\n");
    By fruitsAvailable =By.xpath("//div[contains(@class,\"TableBody\")]//div[@id=\"cell-2-undefined\"]/div");
    private By columnValueNeeded(int columnNo) {
        return By.xpath(
                "./../following-sibling::div[@data-column-id='" + columnNo + "']"
        );
    };
    public UploadPage(WebDriver driver) {
        super(driver);
    }

    public Boolean validateHeaderIsVisible(){
        return isElementDisplayed(find(headerPage),LONG_TIMEOUT);
    }

    public UploadPage uploadDoc(){
        Path filePath = Paths.get(
                "src/test/java/org/practice/testData/uploads/download.xlsx"
        ).toAbsolutePath();
        find(uploadButton).sendKeys(filePath.toString());
        return this;
    }
    public Boolean validateSuccessMessage(){
        return isElementDisplayed(find(uploadSuccess),LONG_TIMEOUT);
    }


    public Boolean validateWebTableDisplayed(){
        return isElementDisplayed(find(webTable),LONG_TIMEOUT);
    }

    public File downloadFile(String downloadDir) {
        click(find(downloadButton),LONG_TIMEOUT);

       return DownloadUtility.waitForDownloadedFile(
                downloadDir,
                "download.xlsx",
                20
        );
    }

    public String getValue(String fruitName,int dataCoulmn){
       return getDriver().findElements(fruitsAvailable).stream().filter(item -> item.getText().contains(fruitName)).findFirst()
                . map(item -> item.findElement(columnValueNeeded(dataCoulmn)).getText())
                .orElseThrow(() ->
                        new NoSuchElementException("Column not found for fruit: " + fruitName)
                );
    }
}
