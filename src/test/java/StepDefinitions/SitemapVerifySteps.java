package StepDefinitions;

import Pages.DialogContent;
import Utilities.GWD;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class SitemapVerifySteps {
    DialogContent dc=new DialogContent();
    WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));

    @Given("Navigate to NOnBir")
    public void navigateToNOnBir() {

        GWD.getDriver().get("https://www.n11.com/");
        GWD.getDriver().manage().window().maximize();
    }
    @When("Click on the Sitemap")
    public void clickOnTheSitemap() {
    dc.findAndClick("sitemapLink");
    }

    @And("Compare the titles with those in the section below")
    public void compareTheTitlesWithThoseInTheSectionBelow() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='urls']>p")));
        for (int i = 0; i <dc.sitemapTitlesList_x.size(); i++) {

            WebElement title=GWD.getDriver().findElement(By.xpath("(//a[@class='main-category']/span)["+(i+1)+"]"));
            wait.until(ExpectedConditions.visibilityOf(title));
            scrollToIntoMiddle(title);
            Assert.assertEquals(dc.sitemapTitlesList_x.get(i).getText(),title.getText());

        }
    }

    @Then("Verify all subheadings that appear")
    public void verifyAllSubheadingsThatAppear() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='category-wrapper box']>div[class^='sub-category']>a")));
        for (int i = 0; i <dc.subheadingsListVerify.size(); i++) {
            WebElement title=GWD.getDriver().findElement(By.xpath("(//div[@class='category-wrapper box']/div[starts-with(@class,'sub-category')]/a)["+(i+1)+"]"));
            wait.until(ExpectedConditions.visibilityOf(title));
            scrollToIntoMiddle(title);
            Assert.assertEquals(dc.subheadingsListVerify.get(i).getText(),title.getText());

        }
    }
    public void scrollToIntoMiddle(WebElement element) {

         JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
         String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                 + "var elementTop = arguments[0].getBoundingClientRect().top;"
                 + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
         js.executeScript(scrollElementIntoMiddle, element);

    }

}
