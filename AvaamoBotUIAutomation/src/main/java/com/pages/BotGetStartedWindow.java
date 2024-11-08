package com.pages;


import com.helper.CommonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.Arrays;
import java.util.List;

public class BotGetStartedWindow {


    public WebDriver driver;
    public CommonHelper commonHelper;

    @FindBy(xpath = "//div[@class='avaamo__icon']/a")
    public WebElement avaamo_icon;
    @FindBy(xpath = "//a[text()='Get Started']")
    public WebElement getstartedButton;
    @FindBy(id="avaamo__popup")
    public WebElement avamoPopup;
    @FindBy(id = "first_name")
    public WebElement firstName;
    @FindBy(id="email")
    public WebElement email;
    @FindBy(name = "avaamoIframe")
    public WebElement avaamo_frame;
    @FindBy(xpath = "//button[text()='Next']")
    public WebElement nextButton;
    @FindBy(className = "default_card_image")
    public WebElement card_image;
    @FindBy(name = "message")
    public WebElement enterDetails;
    @FindBy(xpath = "//button[text()='Send']")
    public WebElement sendButton;
    @FindBy(xpath = "//small[text()='Welcome to Pizza Shoppe']")
    public WebElement welcomeNote;
    @FindBy(xpath = "//p[text()='Welcome to McPizza Booking Journey']")
    public WebElement botWelcomeNote;
    @FindBy(xpath = "//label[contains(text(),' Select Your topping')]")
    public WebElement selectToppings;
    @FindBy(xpath = "//*[text()='Submitted successfully']")
    public WebElement validationcheck;
    @FindBy(xpath = "//button[@data-ele-name='scroll_right']")
    public WebElement navigate;
    @FindBy(xpath = "//div[contains(text(),'Select Your Pizza Size')]")
    public WebElement selectPizzaSize;
    @FindBy(xpath = "//*[contains(text(),'Your Pizza Will Look like this')]//parent::p")
    public WebElement orderSummary;
    @FindBy(xpath ="//p[contains(text(),'CONGRATS ! ORDER PLACED .')]" )
    public WebElement orderPlaced;
    @FindBy(css = "button.thumbs-up.locale-trans")
    public WebElement thumpsUp;
    @FindBy(css = "input[role='combobox']")
    public WebElement combobox;
    @FindBy(xpath = "//*[@id='custom_feedback_body']/div/div/button[2]")
    public WebElement submitFeedBack;
    @FindBy(xpath = "//div[@data-ele-name='feedback_sent']")
    public WebElement support;

    public BotGetStartedWindow(WebDriver driver) {
        this.driver = driver;
        commonHelper = new CommonHelper(this.driver);
        PageFactory.initElements(this.driver, this);

    }


    public boolean verifyNoteIsDisplayed()
    {

        return commonHelper.waitForVisibilityOfElement(welcomeNote).isDisplayed();

    }


    public void click_avaamoIcon()
    {
        commonHelper.waitTillPageLoads();
        avaamo_icon.click();
    }

    public void selectGetStatedButton()
    {
        commonHelper.waitForVisibilityOfElement( getstartedButton);
        getstartedButton.click();

    }

    public void enterFirstName(String first_name)
    {
        commonHelper.switchToIframe(avaamo_frame);
        commonHelper.waitForVisibilityOfElement(firstName);
        commonHelper.enterQuery(firstName, first_name);
    }

    public void enterEmail(String e_mail)
    {

        commonHelper.waitForVisibilityOfElement(email);
        commonHelper.enterQuery(email, e_mail);
    }

    public void clickNext()
    {
        commonHelper.clickOnElement(nextButton);
    }

    public boolean verifyLoginSuccess()
    {
        return  commonHelper.waitForVisibilityOfElement(card_image).isDisplayed();
    }

    public boolean verifyBotWelcomeNote()
    {
        return commonHelper.waitForVisibilityOfElement(botWelcomeNote).isDisplayed();
    }
    public void typeAMessage(String query)
    {
        commonHelper.enterQuery(enterDetails,query);
    }

    public void clickSendButton()
    {
        commonHelper.clickOnElement(sendButton);

    }

    public boolean verifyMessageReceivedByBot(String message) throws InterruptedException {
        Thread.sleep(2000);
        WebElement element1 = driver.findElement(By.xpath("//div[@aria-label='You sent, Text. "+message+"']/descendant::i[2]"));
        if(element1.isDisplayed())
        {
            return true;


        }
        else
            return false;
    }

    public String verifyBotResponse(String message) throws InterruptedException {
        Thread.sleep(2000);
        WebElement element1 = driver.findElement(By.xpath("//div[@aria-label='You sent, Text. "+message+"']/following::div/descendant::p"));
        return commonHelper.waitForVisibilityOfElement(element1).getText();

    }

    public void pizzaType(String type)
    {
        By pizzaT = By.xpath("//*[text()='"+type+"']");
        commonHelper.waitTillElementIsClickable(pizzaT).click();
    }

    public boolean selectToppings()
    {
        return commonHelper.waitForVisibilityOfElement(selectToppings).isDisplayed();

    }


    public void clickOnSubmit()
    {
        By submitButton = By.xpath("//button[text()='Submit']");
        commonHelper.waitTillElementIsClickable(submitButton).click();
    }

    public boolean validationCheck()
    {
        if(commonHelper.waitForVisibilityOfElement(validationcheck).isDisplayed())
        {
            return true;
        }
        else
            return false;
    }

    public boolean botResponseOnPizzaTypeSelection(String type)
    {
        By pizzat = By.xpath("//p[text()='Great ! You have Selected "+type.toLowerCase()+"']");
        return  commonHelper.waitForPresenceOfElement(pizzat).isDisplayed();
    }

    public void navigate()
    {
        commonHelper.waitForVisibilityOfElement(navigate).isDisplayed();
        commonHelper.clickOnElement(navigate);
    }

    public void chooseCrustType(String crust) throws InterruptedException {
        Thread.sleep(2000);
        By type = By.xpath("//*[text()='"+crust+"']");
        commonHelper.waitTillElementIsClickable(type).click();
    }

    public boolean setPizzaSize()
    {
        return commonHelper.waitForVisibilityOfElement(selectPizzaSize).isDisplayed();

    }

    public void selectPizzaSize(String size)
    {
        By selectSize = By.xpath("//*[text()='"+size+"']");
        commonHelper.waitTillElementIsClickable(selectSize).click();


    }

    public List<String> orderSummary() {
        commonHelper.waitForVisibilityOfElement(orderSummary).isDisplayed();
        String arr[] = orderSummary.getText().split(":");
        return Arrays.asList(arr);
    }

    public void confirmOrder(String confirm)
    {
        if(confirm.contains("yes"))
        {
            By select = By.xpath("//*[text()='"+confirm+"']");
            commonHelper.waitTillElementIsClickable(select).click();
        }
        else {
            By select = By.xpath("//*[text()='"+confirm+"']");
            commonHelper.waitTillElementIsClickable(select).click();
        }

    }

    public boolean orderPlacedConfirmation()
    {
        return commonHelper.waitForVisibilityOfElement(orderPlaced).isDisplayed();
    }


    public void feedback(String feed)
    {
        if(feed.contains("ThumpsUp"))
        {
            commonHelper.waitForVisibilityOfElement(thumpsUp).isDisplayed();
            commonHelper.clickOnElement(thumpsUp);
        }

    }

    public void selectFeedBack(String feedback)
    {
        commonHelper.waitForVisibilityOfElement(combobox).isDisplayed();
        commonHelper.clickOnElement(combobox);
        By feed = By.xpath("//*[text()='"+feedback+"']");
        commonHelper.waitForPresenceOfElement(feed).click();

    }

    public boolean submitFeedBack() throws InterruptedException {
        Thread.sleep(2000);
        commonHelper.waitForVisibilityOfElement(submitFeedBack).isDisplayed();
        commonHelper.clickOnElement(submitFeedBack);
        return commonHelper.waitForVisibilityOfElement(support).isDisplayed();
    }



}
