package com.aqacourses.project.pages;

import com.aqacourses.project.base.BaseTest;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    // Web Elements
    @FindBy(xpath = "//div/a[@class='login']")
    private WebElement signInLink;

    @FindBy(xpath = "//div/a[@class='logout']")
    private WebElement singOutButton;

    @FindBy(xpath = "//div[@id='block_top_menu']/ul/li/a[@title='Dresses']")
    private WebElement dressesButton;

    @FindBy(xpath = "//div[@id='block_top_menu']/ul/li/a[@title='T-shirts']")
    private WebElement tShirtsButton;

    /*@FindBy(xpath = "//span[contains(text(),'More')]")
    private WebElement moreButton;*/

    @FindBy(xpath = "//div[@class='product-container']")
    protected List<WebElement> listOfProducts;

    @FindBy(xpath = "//span[@class='heading-counter']")
    protected WebElement counterOfProducts;

    @FindBy(xpath = "//div[@id='page']")
    protected WebElement divPage;

    private String PRODUCT_DETAIL_LINK_XPATH = "//h5/a[@title='%s']";

    // Instances of BaseTest
    protected BaseTest testClass;

    /**
     * Constructor
     *
     * @param testClass
     */
    public AbstractPage(BaseTest testClass) {
        this.testClass = testClass;
        PageFactory.initElements(testClass.getDriver(), this);
        testClass.waitTillElementIsVisible(divPage);
    }

    /**
     * Click on the "Sign in" link
     *
     * @return new instance of LoginPage
     */
    public LoginPage clickSignInLink() {
        testClass.waitTillElementIsVisible(signInLink);
        signInLink.click();
        return new LoginPage(testClass);
    }

    /**
     * Click on the "Sign out" button
     *
     * @return new instance of LoginPage
     */
    public LoginPage logout() {
        testClass.waitTillElementIsVisible(singOutButton);
        singOutButton.click();
        return new LoginPage(testClass);
    }

    /**
     * Click on the "Dresses" button
     *
     * @return new instance of DressesPage
     */
    public DressesPage openDressesPage() {
        testClass.waitTillElementIsVisible(dressesButton);
        dressesButton.click();
        return new DressesPage(testClass);
    }

    /**
     * Click on the "T-Shirts" button
     *
     * @return new instance of TShirtsPage
     */
    public TShirtsPage openTShirtsPage() {
        testClass.waitTillElementIsVisible(tShirtsButton);
        tShirtsButton.click();
        return new TShirtsPage(testClass);
    }

    /** Hover over the product */
    /*private void hoverOverTheProduct() {
        Actions actions = new Actions(testClass.getDriver());
        actions.moveToElement(listOfProducts.get(0)).perform();
    }*/

    /**
     * Open the product
     *
     * @return new instance of ProductPage
     */
    /*public ProductPage openProduct() {
        testClass.waitTillListOfElementsAreVisible(listOfProducts);
        hoverOverTheProduct();
        moreButton.click();
        return new ProductPage(testClass);
    }*/

    /**
     * Open the product
     *
     * @return new instance of ProductPage
     */
    public ProductPage openProductByTitle(String title) {
        testClass.waitTillListOfElementsAreVisible(listOfProducts);
        WebDriver driver = testClass.getDriver();
        driver.findElement(By.xpath(String.format(PRODUCT_DETAIL_LINK_XPATH, title))).click();
        return new ProductPage(testClass);
    }
}
