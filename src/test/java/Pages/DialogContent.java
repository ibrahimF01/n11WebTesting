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

    @FindBy (xpath = "//a[.='Giriş Yap']")
    private WebElement signInBtn;

    @FindBy (xpath = "//span[text()='Tümünü Reddet']")
    private WebElement tReddetBtn;

    @FindBy (xpath = "(//button[text()='Daha Sonra'])[1]")
    private WebElement dahaSonraBtn;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "loginButton")
    private WebElement loginBtn;

    @FindBy (css = "a[title='Hesabım']")
    private WebElement dashboard;

    @FindBy (xpath = "(//div[@class='track']/span)[10]")
    private WebElement filtre9PuanVeUzeri;

    @FindBy (css = "input[id='freeShippingOption']+span")
    private WebElement filtreUcretsizKargo;

    @FindBy (css = "input[data-point='5']+span+span")
    private WebElement filtreUrunPuan5Yildiz;

    @FindBy (xpath = "(//div[@class='priceInput']/input)[1]")
    private WebElement filtreFiyatAraligiEnAz;

    @FindBy (xpath = "(//div[@class='priceInput']/input)[2]")
    private WebElement filtreFiyatAraligiEnCok;

    @FindBy (id = "priceSearchButton")
    private WebElement filtreFiyatAraligiBtn;

    @FindBy (css = "i[class='iconsBasketWhite']")
    private WebElement sepetBtn;

    @FindBy (xpath = "(//span[@class='removeProd svgIcon svgIcon_trash'])[1]")
    private WebElement silBtn1;

    @FindBy (css = "span[id='deleteBtnDFLB']")
    private WebElement silBtn2;

    // omerAvci

    @FindBy (xpath = "//div[@class='dn-slide-buttons horizontal']//button[contains(text(),'Evet')]")
    private WebElement popup_Oa;

    @FindBy (xpath = "//span[contains(text(),'Tümünü Reddet')]")
    private WebElement reject_Oa;

    @FindBy (css = "[id='searchData']")
    private WebElement searchBox_Oa;

    @FindBy (css = "[class='iconsSearch']")
    private WebElement searchClick_Oa;

    @FindBy (css = "[class='proName']")
    public WebElement MainTitle;

    @FindBy (id = "shareWinTooltipClose")
    public WebElement closePp;

    @FindBy (xpath = "div[class='product-add-cart']>button[title='Sepete Ekle']")
    public WebElement addToCCCart;

    @FindBy (css = "[class='unf-sell-sub-title']")
    public WebElement subTitle;

    @FindBy (css = "[class='newPrice']")
    public WebElement newPrice;

    @FindBy (css = "[class='unf-info-desc']")
    public WebElement detailProduct;

    @FindBy (css = "[class='cargo-price']")
    public WebElement cargoPrice;
    WebElement myElement;
    public void findAndSend(String strElement, String value){
        switch (strElement)
        {
            case "email" : myElement = email ;break;
            case "password" : myElement = password ;break;
            case "filtreFiyatAraligiEnAz" : myElement = filtreFiyatAraligiEnAz ;break;
            case "filtreFiyatAraligiEnCok" : myElement = filtreFiyatAraligiEnCok ;break;
            case "searchBox_Oa" : myElement = searchBox_Oa;break;
        }

        sendKeysFunction(myElement, value);
    }

    public void findAndClick(String strElement){
        switch (strElement)
        {
            case "sitemapLink" : myElement = sitemapLink; break;
            case "signInBtn" : myElement = signInBtn; break;
            case "tReddetBtn" : myElement = tReddetBtn; break;
            case "dahaSonraBtn" : myElement = dahaSonraBtn; break;
            case "loginBtn" : myElement = loginBtn; break;
            case "sepetBtn" : myElement = sepetBtn; break;
            case "filtre9PuanVeUzeri" : myElement = filtre9PuanVeUzeri; break;
            case "filtreUcretsizKargo" : myElement = filtreUcretsizKargo; break;
            case "filtreFiyatAraligiBtn" : myElement = filtreFiyatAraligiBtn; break;
            case "filtreUrunPuan5Yildiz" : myElement = filtreUrunPuan5Yildiz; break;
            case "popup_Oa" : myElement = popup_Oa; break;
            case "reject_Oa" : myElement = reject_Oa; break;
            case "searchClick_Oa" : myElement = searchClick_Oa; break;
            case "closePp" : myElement = closePp; break;
            case "addToCCCart" : myElement = addToCCCart; break;

        }

        clickFunction(myElement);
    }

    public void assertElementVisible(String strElement){
        switch (strElement)
        {
            case "dashboard" : myElement = dashboard ;break;

        }

        verifyElementVisible(myElement);
    }

    public WebElement getMyElement(String strElement) {
        switch (strElement){
            case "loginBtn":myElement=loginBtn;break;

        }
        return myElement;
    }


    }
