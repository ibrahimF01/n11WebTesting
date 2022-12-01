package Pages;

import Utilities.GWD;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Parent{

    public void sendKeysFunction(WebElement element, String value) {
        waitUntilVisible(element);
        if (!element.isDisplayed()){
            scrollToElement(element);}
        element.clear();
        element.sendKeys(value);
    }

    public void waitUntilVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    //sorunlu noktalara scroll yaparken alternatif olarak kullanılabilir. go yaz elemente gitsin, mid yaz elementin sayfa orta gelmesini sağlasın
    public void scrollGoAndMid(WebElement element,String goOrmid) {
        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        if(goOrmid.equalsIgnoreCase("go")){
            js.executeScript("arguments[0].scrollIntoView();", element);}
        if(goOrmid.equalsIgnoreCase("mid")) {
            String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
            js.executeScript(scrollElementIntoMiddle, element);
        }
    }
    public void clickFunction(WebElement element)
    {
        waitUntilClickable(element);
        if (!element.isDisplayed()){
            scrollToElement(element);}
        element.click();
    }

    public void waitUntilClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void verifyContainsText(WebElement element, String text)
    {
        waitUntilVisible(element);
        Assert.assertTrue(element.getText().toLowerCase().contains(text.toLowerCase()));
    }

    public void verifyElementVisible(WebElement element){
        waitUntilVisible(element);
        Assert.assertTrue(element.isDisplayed());
    }

    public void waitUntilLoading() {
        WebDriverWait wait=new WebDriverWait(GWD.driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("fuse-progress-bar > *"), 0));
    }

    public List<WebElement> waitVisibleListAllElement(List<WebElement> elementList) {
        WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
        return elementList;
    }

}
