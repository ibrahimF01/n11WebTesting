package StepDefinitions;

import Pages.DialogContent;
import Utilities.GWD;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class AssertStepsOA {

    DialogContent dc=new DialogContent();
    Actions actions=new Actions(GWD.getDriver());
    WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));

    @Given("Navigate to Neleven")
    public void navigateToN() {
        GWD.getDriver().get("https://www.n11.com/");
        GWD.getDriver().manage().window().maximize();
    }
    @When("I type toilet brush in the search box and click the search button")
    public void iTypeToiletBrushInTheSearchBoxAndClickTheSearchButton() throws AWTException {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dn-slide-buttons horizontal']//button[contains(text(),'Evet')]")));
        dc.findAndClick("popup_Oa");
        dc.findAndClick("reject_Oa");

        Robot rbt=new Robot();
        rbt.keyPress(KeyEvent.VK_TAB);
        rbt.keyRelease(KeyEvent.VK_TAB);
        rbt.keyPress(KeyEvent.VK_ENTER);
        rbt.keyRelease(KeyEvent.VK_ENTER);

        dc.findAndSend("searchBox_Oa","klozet fırçası");
        dc.findAndClick("searchClick_Oa");

    }
    String homepage;
    @And("I click on any product")
    public void iClickOnAnyProduct() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class='productName']")));

        List<WebElement> brushList=GWD.getDriver().findElements(By.cssSelector("[class='productName']"));

        int random=(int)(Math.random() * brushList.size());

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='productName']")));

        homepage = GWD.getDriver().getWindowHandle();
        System.out.println("homepage = " + homepage);

        brushList.get(random).click();

        GWD.Bekle(5);

        Set<String> windows = GWD.getDriver().getWindowHandles();

        for (String w : windows){

            System.out.println("w = " + w);

            if (!homepage.equalsIgnoreCase(w)){

                GWD.getDriver().switchTo().window(w);
                wait.withTimeout(Duration.ofSeconds(10));
            }

        }
    }

    @Then("Product title should look correct")
    public void productTitleShouldLookCorrect() {

        if (dc.closePp.isDisplayed()){

            dc.findAndClick("closePp");

        }
        System.out.println("dc.MainTitle.getText() = " + dc.MainTitle.getText());

        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        js.executeScript(scrollElementIntoMiddle, dc.subTitle);

        System.out.println("dc.subTitle.getText() = " + dc.subTitle.getText());

        Assert.assertEquals(dc.MainTitle.getText(),dc.subTitle.getText());

    }

    @Then("Product details should look correct")
    public void productDetailsShouldLookCorrect() {


        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        js.executeScript(scrollElementIntoMiddle, dc.detailProduct);

        try{

            File dosya = new File("src/test/java/StepDefinitions/Resources/productInfo.txt");
            FileWriter yazici = new FileWriter(dosya,true);
            BufferedWriter yaz = new BufferedWriter(yazici);
            yaz.write("Price  :  " + dc.detailProduct.getText());
            yaz.close();
            System.out.println("Successfully");
        }
        catch (Exception hata){
            hata.printStackTrace();
        }

        System.out.println("dc.detailProduct.getText() = " + dc.detailProduct.getText());

    }



    @Then("Product price should look correct")
    public void productPriceShouldLookCorrect() {

        JavascriptExecutor js = (JavascriptExecutor) GWD.getDriver();
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        js.executeScript(scrollElementIntoMiddle, dc.newPrice);
        System.out.println("dc.newPrice.getText() = " + dc.newPrice.getText());
    }

    @Then("shipping price should appear")
    public void shippingPriceShouldAppear() {


        System.out.println("dc.cargoPrice = " + dc.cargoPrice.getText());
    }
}
