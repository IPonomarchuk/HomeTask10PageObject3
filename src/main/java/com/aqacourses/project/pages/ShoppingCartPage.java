package com.aqacourses.project.pages;

import com.aqacourses.project.base.BaseTest;
import java.math.BigDecimal;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends AbstractPage {

    // Web elements
    @FindBy(xpath = "//a[@title='Add']//span")
    private WebElement plusButton;

    @FindBy(xpath = "//td[@class='cart_unit']/span")
    private WebElement unitPrice;

    @FindBy(xpath = "//td[@class='cart_total']/span")
    private WebElement totalPrice;

    @FindBy(xpath = "//td[@class='cart_quantity text-center']/input[@type='hidden']")
    private WebElement quantityOfProducts;

    @FindBy(xpath = "//i[@class='icon-trash']")
    private WebElement deleteButton;

    @FindBy(xpath = "//p[@class='alert alert-warning']")
    private WebElement emptyShoppingCart;

    private final String MESSAGE_EMPTY_SHOPPING_CART = "Your shopping cart is empty.";

    /**
     * Constructor
     *
     * @param testClass
     */
    public ShoppingCartPage(BaseTest testClass) {
        super(testClass);
    }

    /** Increase quantity of product by one */
    public void increaseQuantityOfProductByOne() {
        String stringQuantity = quantityOfProducts.getAttribute("value");
        int intQuantity = Integer.parseInt(stringQuantity);
        plusButton.click();
        testClass.waitTillValueOfElementIsChanged(quantityOfProducts, intQuantity);
    }

    /** Validate total price */
    public void validateTotalPrice() {

        String stringUnitPrice = unitPrice.getText();
        BigDecimal bigDecimalUnitPrice = new BigDecimal(stringUnitPrice.replaceAll("[^0-9,.]", ""));
        String stringTotalPrice = totalPrice.getText();
        BigDecimal bigDecimalTotalPrice =
                new BigDecimal(stringTotalPrice.replaceAll("[^0-9,.]", ""));
        String stringQuantity = quantityOfProducts.getAttribute("value");
        int intQuantity = Integer.parseInt(stringQuantity);
        Assert.assertEquals(
                "Total price isn't correct",
                bigDecimalTotalPrice,
                (bigDecimalUnitPrice.multiply(new BigDecimal(intQuantity))));
    }

    /** Delete product and verify that message is displayed */
    public void deleteProduct() {
        deleteButton.click();
        testClass.waitTillElementIsVisible(emptyShoppingCart);
        Assert.assertEquals(
                "Message is not the same as expected",
                MESSAGE_EMPTY_SHOPPING_CART,
                emptyShoppingCart.getText());
    }
}
