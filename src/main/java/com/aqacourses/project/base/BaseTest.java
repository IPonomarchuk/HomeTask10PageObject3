package com.aqacourses.project.base;

import com.aqacourses.project.pages.HomePage;
import com.aqacourses.project.utils.YamlParser;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    // Instances of WebDriver and WebDriverWait
    private WebDriver driver;
    private WebDriverWait wait;

    // Logger
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    // Rule
    @Rule public RunTestRule runTestRule = new RunTestRule(this);

    /** Constructor */
    public BaseTest() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    /**
     * Return instance of Driver
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Open site and return instance of HomePage
     *
     * @return HomePage
     */
    public HomePage openSite() {
        driver.get(YamlParser.getYamlData().getUrl());
        return new HomePage(this);
    }

    /** Close site with driver.quit() */
    public void closeSite() {
        driver.quit();
    }

    /**
     * Write down info message
     *
     * @param message
     */
    public void log(String message) {
        logger.info(message);
    }

    /**
     * Write down error message
     *
     * @param error
     */
    public void error(String error) {
        logger.error(error);
    }

    /**
     * Get current date and time
     *
     * @return current date and time
     */
    public String getDateTime() {
        return new SimpleDateFormat("YYYY-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
    }

    /**
     * Wait till element is visible
     *
     * @param element
     */
    public void waitTillElementIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait till list of elements are visible
     *
     * @param elements
     */
    public void waitTillListOfElementsAreVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * Wait till value of element is changed
     *
     * @param element
     * @param quantity
     */
    public void waitTillValueOfElementIsChanged(WebElement element, int quantity) {
        wait.until(
                new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        int newQuantity = Integer.parseInt(element.getAttribute("value"));
                        if ((newQuantity == quantity + 1) || (newQuantity == quantity - 1)) {
                            return true;
                        } else return false;
                    }
                });
    }

    /**
     * Wait till value of element is increased by one
     *
     * @param locator
     * @param expectedValue
     */
    /*public void waitTillValueOfElementIsIncreasedByOne(WebElement element, int expectedValue) {
        wait.until(
                ExpectedConditions.textToBePresentInElementValue(
                        element, String.valueOf(expectedValue)));
    }*/

    public void waitTillValueOfElementIsIncreasedByOne(String locator, int expectedValue) {
        wait.until(
                ExpectedConditions.textToBePresentInElementValue(
                        By.xpath(locator), String.valueOf(expectedValue)));
    }
}
