package com.helper;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CommonHelper  {

    WebDriverWait webDriverWait;
    WebDriver driver;

    public CommonHelper(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    public WebElement findElement(By locator)
    {
        return driver.findElement(locator);

    }

    public List<WebElement> findElements(By locator)
    {
        return driver.findElements(locator);

    }

    public String getTextOfElement(By locator)
    {
        return  driver.findElement(locator).getText();
    }

    public void clickOnElement(WebElement element)
    {
        element.click();
    }

    public WebElement waitForPresenceOfElement(By locator)
    {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    public WebElement waitForVisibilityOfElement(WebElement element)
    {
        return webDriverWait.until(ExpectedConditions.visibilityOf(element));

    }

    public WebElement waitTillElementIsClickable(By locator)
    {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));

    }

    public boolean waitTillElementIsDisplayed(By locator)
    {
        return driver.findElement(locator).isDisplayed();

    }

    public void switchToIframe(WebElement element)
    {
       driver.switchTo().frame(element);


    }

    public void waitTillPageLoads()
    {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

    }

    public void enterQuery(WebElement element, String query)
    {
        element.sendKeys(query);

    }


}
