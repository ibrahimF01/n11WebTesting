package Pages;

import Utilities.GWD;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DialogContent extends Parent{

    public DialogContent() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy (css = "li>a[title$='Haritası']")
    private WebElement sitemapLink;

    @FindBy (css = "div[class='urls']>p")
    public List<WebElement> sitemapTitlesList_x;


    @FindBy (css = "div[class='category-wrapper box']>div[class^='sub-category']>a")
    public List<WebElement> subheadingsListVerify;




    WebElement myElement;
    public void findAndSend(String strElement, String value){
        switch (strElement)
        {
//            case "" : myElement = ;break;
        }

        sendKeysFunction(myElement, value);
    }

    public void findAndClick(String strElement){
        switch (strElement)
        {
            case "sitemapLink" : myElement = sitemapLink; break;

        }

        clickFunction(myElement);
    }

    public void assertElementVisible(String strElement){
        switch (strElement)
        {
//            case "" : myElement = ;break;

        }

        verifyElementVisible(myElement);
    }

    }
