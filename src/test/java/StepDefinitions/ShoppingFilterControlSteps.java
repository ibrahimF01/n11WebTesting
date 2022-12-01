package StepDefinitions;

import Pages.DialogContent;
import Utilities.GWD;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingFilterControlSteps {

    DialogContent dc=new DialogContent();

    WebDriverWait wait = new WebDriverWait(GWD.getDriver(), Duration.ofSeconds(30));

    @And("Login to the site")
    public void loginToTheSite() {
        dc.findAndClick("tReddetBtn");
        dc.findAndClick("signInBtn");
        dc.findAndSend("email","venusn11test@gmail.com");
        dc.findAndClick("dahaSonraBtn");
        dc.findAndSend("password","ehj68qaTr");
        dc.scrollGoAndMid(dc.getMyElement("loginBtn"), "mid");
        dc.findAndClick("loginBtn");
    }

    @Then("User should login successfully")
    public void userShouldLoginSuccessfully() {
        dc.assertElementVisible("dashboard");
    }

    @Given("Click on Bathroom & Home Appliances under the Home & Living menu")
    public void clickOnBathroomHomeAppliancesUnderTheHomeLivingMenu() {

        WebElement evVeYasamMenu= GWD.getDriver().findElement(By.xpath("(//a[@title='Ev & Yaşam']/span)[1]"));
        WebElement banyoVeEvGerecleriMenu=GWD.getDriver().findElement(By.xpath("(//a[@title='Banyo & Ev Gereçleri']/span)[1]"));

        Actions aksiyonlar=new Actions(GWD.getDriver());

        Action aksiyon=aksiyonlar.moveToElement(evVeYasamMenu).build();
        aksiyon.perform();

        aksiyon=aksiyonlar.moveToElement(banyoVeEvGerecleriMenu).build();
        aksiyon.perform();

        aksiyon=aksiyonlar.click().build();
        aksiyon.perform();

    }

    @And("Sort by ascending price")
    public void sortByAscendingPrice() {

//        WebElement menu=GWD.getDriver().findElement(By.cssSelector("select[id='sortingType']"));
//        Select ddmenu=new Select(menu);
//        ddmenu.selectByVisibleText("Artan fiyat");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='selected-item']")));
        WebElement menu = GWD.getDriver().findElement(By.cssSelector("div[class='selected-item']"));
        menu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='selected-item arrowanim']")));
        WebElement artanFiyat = GWD.getDriver().findElement(By.cssSelector("div[data-value='PRICE_LOW']"));
        artanFiyat.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='selected-item']")));
    }

    @And("Select required filters")
    public void selectRequiredFilters() {



        dc.findAndClick("filtreUcretsizKargo");
        dc.findAndClick("filtre9PuanVeUzeri");
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[class='choiceList'] div"),2));
        dc.findAndClick("filtreUrunPuan5Yildiz");
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[class='choiceList'] div"),4));
        dc.findAndSend("filtreFiyatAraligiEnAz","10");
        dc.findAndSend("filtreFiyatAraligiEnCok","1000");

        JavascriptExecutor js=(JavascriptExecutor) GWD.getDriver();
        js.executeScript("window.scrollBy (0,-600)","");

        WebElement filtreFiyatAraligiBtn = GWD.getDriver().findElement(By.id("priceSearchButton"));
        filtreFiyatAraligiBtn.click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[class='choiceList'] div"),6));
        
    }

    @And("Add product")
    public void addProduct() {


        WebElement sepeteEkle = GWD.getDriver().findElement(By.xpath("(//li[@class='column '] //span[@class='btnBasket'])[1]"));
        sepeteEkle.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span[class='btnBasket completed']")));

    }

    @And("Go to cart")
    public void goToCart() {

        dc.findAndClick("sepetBtn");

    }

    @And("Check shopping filters")
    public void checkShoppingFilters() {

        int uygun=0;
        List<WebElement> magazaPuanlari = GWD.getDriver().findElements(By.cssSelector("div[class='sellerInfo--point  green']"));
        for (int i = 0; i < magazaPuanlari.size(); i++) {
            String magazaPuanStr=magazaPuanlari.get(i).getText();
            int magazaPuanInt=Integer.parseInt(magazaPuanStr.substring(0,1));
            if (magazaPuanInt==9||magazaPuanInt==1){
                uygun +=1;
            }
        }

        if (magazaPuanlari.size()==uygun){
            System.out.println("<--------------------------------------------------------------->");
            System.out.println("Secilen ürünlerin satıcıları 9 Puan Üzeri Satıcılardan oluşuyor.");
            System.out.println("<--------------------------------------------------------------->");
        }else{
            System.out.println("<--------------------------------------------->");
            System.out.println("HATA: 9 mağaza puanın altında olan satıcı var.");
            System.out.println("<--------------------------------------------->");
        }


        int onay=0;
        List<WebElement> bedavaKargo = GWD.getDriver().findElements(By.xpath("//span[text()='Kargon Ücretsiz']"));
        for (int i = 0; i < bedavaKargo.size(); i++) {
            String bedavaKargoStr=bedavaKargo.get(i).getText();
            if (bedavaKargoStr.equals("Kargon Ücretsiz")){
                onay +=1;
            }
        }

        if (bedavaKargo.size()==onay){
            System.out.println("<------------------------------------------------>");
            System.out.println("Sepetteki ürünlerin hepsinin kargosu Ücretsiz`dır.");
            System.out.println("<------------------------------------------------>");
        }else{
            System.out.println("<---------------------------------------------------------------------->");
            System.out.println("HATA: Sepetteki ürünlerin arasında bedava kargo olmayan secenekler var.");
            System.out.println("<---------------------------------------------------------------------->");
        }


        int fiyatSayi=0;
        List<WebElement> urunFiyatBilgisi = GWD.getDriver().findElements(By.cssSelector("div[class='priceArea'] span"));
        for (int i = 0; i < urunFiyatBilgisi.size(); i++) {
            String urunFiyatBilgisiStr=urunFiyatBilgisi.get(i).getText();
            int urunFiyatBilgisiInt=Integer.parseInt(urunFiyatBilgisiStr.substring(0,2));
            if (urunFiyatBilgisiInt>=9){
                fiyatSayi +=1;
            } else if (urunFiyatBilgisiInt<=1000) {
                fiyatSayi +=1;
            }
        }

        if (urunFiyatBilgisi.size()==fiyatSayi){
            System.out.println("<---------------------------------------------------------->");
            System.out.println("Sepetteki ürünlerin hepsinin Fiyatı 10-1000 TL arasındadır.");
            System.out.println("<---------------------------------------------------------->");
        }else{
            System.out.println("<-------------------------------------------------------------------------------->");
            System.out.println("HATA: Sepetteki ürünlerin arasında Fiyatı 10-1000 TL arasında olmayan ürünler var.");
            System.out.println("<-------------------------------------------------------------------------------->");
        }


    }

    @And("Empty the cart")
    public void emptyTheCart() {

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class='removeProd svgIcon svgIcon_trash']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[id='deleteBtnDFLB']"))).click();

    }
}
