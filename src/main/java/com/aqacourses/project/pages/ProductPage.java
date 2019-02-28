package com.aqacourses.project.pages;

import com.aqacourses.project.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends AbstractPage {

    // Web elements
    @FindBy(xpath = "//h1[@itemprop='name']")
    private WebElement nameOfProduct;

    @FindBy(xpath = "//div[@class='breadcrumb clearfix']")
    private WebElement fullBreadcrumb;

    @FindBy(xpath = "//span[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
    private WebElement proceedToCheckoutButton;

    /**
     * Constructor
     *
     * @param testClass
     */
    public ProductPage(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(divPage);
    }

    /** Verify the last breadcrumb on the ProductPage */
    public void verifyLastBreadcrumb() {
        String sFullBreadcrumb = fullBreadcrumb.getText();
        int lastIndex = sFullBreadcrumb.lastIndexOf(">");
        String lastBreadcrumb = sFullBreadcrumb.substring(lastIndex + 1);
        Assert.assertEquals(
                "Last breadcrumb isn't correct", nameOfProduct.getText(), lastBreadcrumb);
    }

    /**
     * Add product to shopping cart and proceed to checkout
     *
     * @return new instance of ShoppingCartPage
     */
    public ShoppingCartPage addProductToShoppingCartAndProceedToCheckout() {
        testClass.waitTillElementIsVisible(addToCartButton);
        addToCartButton.click();
        testClass.waitTillElementIsVisible(proceedToCheckoutButton);
        proceedToCheckoutButton.click();
        return new ShoppingCartPage(testClass);
    }
}
