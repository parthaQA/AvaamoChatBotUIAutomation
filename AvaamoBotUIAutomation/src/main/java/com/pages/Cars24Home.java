package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cars24Home {


    public WebDriver driver;
    WebDriverWait wait;



    @FindBy(css = "input[type='text']")
            public WebElement searchBox;


    public Cars24Home(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
    }



    public void searchACar(String car){
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.sendKeys(car);
    }


    public void selectOptionFromDropDown()  {
        By element = By.xpath("//div/ul/li[1]/span/span[1]");
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void useCurrentLocation(String text) throws Exception {
        By currLoc = By.xpath("//*[text()='"+text+"']/img");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(currLoc)).click();

        }
        catch (Exception e)
        {
            throw new Exception("element not found "+ currLoc+" ");
        }

    }
}
