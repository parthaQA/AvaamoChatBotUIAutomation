package com.chatbot;

import com.pages.BotGetStartedWindow;
import com.helper.LogUtil;
import com.helper.ReadConfigData;
import com.helper.TestData;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatbotTest  {


    WebDriver driver;
    BotGetStartedWindow botGetStartedWindow;
    String expectedResponseForOneAlpha ="Oh no! Did you miss some letters?";
    String non_veg = "non-veg";
    String expected_response_cancelOrder = "Thank You ! For Your Time";


    public ChatbotTest()
    {
        driver = new ChromeDriver();
        botGetStartedWindow = new BotGetStartedWindow(driver);


    }

    @BeforeClass
    public void setup() throws IOException {

        driver.get(ReadConfigData.readData("BASEURL"));
        Reporter.log("Browser Launched");


    }

    @AfterMethod(alwaysRun=true) //AfterMethod annotation - This method executes after every test execution
    public void screenShot(ITestResult result) throws IOException {
        //using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition

        Reporter.setCurrentTestResult(result);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        try {
        if(result.getStatus() == 2) { //failed scenaario
            Reporter.log("This is failed log from reporter.log", true);

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") + "/screenshots/" + result.getName() + formatter.format(date)+".png");
            FileUtils.copyFile(src, destination);
            Reporter.log("</br><font color='#73a9d0'>***************Screenshot Of the error****************</font>");
            Reporter.log("</br><img id='ErrorResult' src='" + destination.getPath() + "height='800', width='800'/>");
        }

        } catch (Exception e) {
            Reporter.log("Exception while taking screenshot " + e.getMessage());
        }

        }


    @AfterClass
    public void tearDown()
    {
        driver.quit();
        Reporter.log("Browser Closed");
    }



    @Test(priority = 1, description = "Verify that user can order  a non veg pizza", dataProvider = "orderPizza", dataProviderClass = TestData.class)
    public void verifyThatUserCanOrderPizzas(String firstName, String emailId,String query, String pizzaType, String crustType, String size, String feedback) throws IOException, InterruptedException {

        Assert.assertTrue(botGetStartedWindow.verifyNoteIsDisplayed());
        botGetStartedWindow.click_avaamoIcon();
        botGetStartedWindow.selectGetStatedButton();
        Reporter.log("Get Started button is clicked");
        botGetStartedWindow.enterFirstName(firstName);
        botGetStartedWindow.enterEmail(emailId);
        botGetStartedWindow.clickNext();
        Assert.assertTrue(botGetStartedWindow.verifyLoginSuccess());
        Reporter.log("Login Successful");
        Assert.assertTrue(botGetStartedWindow.verifyBotWelcomeNote());
        botGetStartedWindow.typeAMessage(query);
        botGetStartedWindow.clickSendButton();
        Assert.assertTrue(botGetStartedWindow.verifyMessageReceivedByBot(query));
        Reporter.log("message sent");
        Assert.assertTrue(botGetStartedWindow.selectToppings());
        botGetStartedWindow.pizzaType(pizzaType);
        botGetStartedWindow.clickOnSubmit();
        Assert.assertTrue(botGetStartedWindow.validationCheck());
        Reporter.log("pizza type is selected");
        Assert.assertTrue(botGetStartedWindow.botResponseOnPizzaTypeSelection(pizzaType));
        botGetStartedWindow.navigate();
        botGetStartedWindow.chooseCrustType(crustType);
        Assert.assertTrue(botGetStartedWindow.setPizzaSize());
        botGetStartedWindow.selectPizzaSize(size);
        LogUtil.logDebug(botGetStartedWindow.orderSummary());
        botGetStartedWindow.orderSummary().contains(non_veg);
        botGetStartedWindow.orderSummary().contains(pizzaType);
        botGetStartedWindow.orderSummary().contains(crustType);
        botGetStartedWindow.orderSummary().contains(size);
        botGetStartedWindow.confirmOrder("Yes");
        Assert.assertTrue(botGetStartedWindow.orderPlacedConfirmation());
        botGetStartedWindow.feedback("ThumpsUp");
        botGetStartedWindow.selectFeedBack(feedback);
        Assert.assertTrue(botGetStartedWindow.submitFeedBack());
        botGetStartedWindow.typeAMessage("reset");
        botGetStartedWindow.clickSendButton();
        Assert.assertTrue(botGetStartedWindow.verifyBotWelcomeNote());
        Reporter.log("conversation reset");



    }

    @Test(priority = 2, description = "Verify bot response when pizza type is not listed", dataProvider = "pizzaType", dataProviderClass = TestData.class)
    public void verifyBotResponseWhenPizzaTypeIsNotListed(String firstName, String emailId,String query, String pizzaType, String botResponse) throws IOException, InterruptedException {

        botGetStartedWindow.typeAMessage(query);
        botGetStartedWindow.clickSendButton();
        Assert.assertTrue(botGetStartedWindow.verifyMessageReceivedByBot(query));
        Reporter.log("message sent");
        Assert.assertTrue(botGetStartedWindow.selectToppings());
        botGetStartedWindow.typeAMessage(pizzaType);
        botGetStartedWindow.clickSendButton();
        Assert.assertTrue(botGetStartedWindow.verifyMessageReceivedByBot(query));
        Reporter.log("message sent");
        Assert.assertEquals(botGetStartedWindow.verifyBotResponse(pizzaType),botResponse); //Test is failed intentionally.


    }

    @Test( priority = 3, description = "Verify that user can cancel order", dataProvider = "orderPizza", dataProviderClass = TestData.class)
    public void verifyThatUserCanCancelOrder(String firstName, String emailId,String query, String pizzaType, String crustType, String size, String feedback) throws IOException, InterruptedException {

        botGetStartedWindow.typeAMessage(query);
        botGetStartedWindow.clickSendButton();
        Assert.assertTrue(botGetStartedWindow.verifyMessageReceivedByBot(query));
        Reporter.log("message sent");
        Assert.assertTrue(botGetStartedWindow.selectToppings());
        botGetStartedWindow.typeAMessage(pizzaType);
        botGetStartedWindow.clickSendButton();
        Assert.assertTrue(botGetStartedWindow.validationCheck());
        Reporter.log("pizza type is selected");
        Assert.assertTrue(botGetStartedWindow.botResponseOnPizzaTypeSelection(pizzaType));
        botGetStartedWindow.navigate();
        botGetStartedWindow.chooseCrustType(crustType);
        Assert.assertTrue(botGetStartedWindow.setPizzaSize());
        botGetStartedWindow.selectPizzaSize(size);
        LogUtil.logDebug(botGetStartedWindow.orderSummary());
        botGetStartedWindow.orderSummary().contains(non_veg);
        botGetStartedWindow.orderSummary().contains(pizzaType);
        botGetStartedWindow.orderSummary().contains(crustType);
        botGetStartedWindow.orderSummary().contains(size);
        botGetStartedWindow.confirmOrder("No");
        botGetStartedWindow.confirmOrder("no");
        Assert.assertEquals(botGetStartedWindow.verifyBotResponse("no"),expected_response_cancelOrder);


    }

    @Test(priority = 4, description = "Verify that user can reset context in between order", dataProvider = "pizzaType", dataProviderClass = TestData.class)
    public void verifyThatUserCanResetContextIinBetweenOrder(String firstName, String emailId,String query, String pizzaType, String botResponse) throws IOException, InterruptedException {

        botGetStartedWindow.typeAMessage(query);
        botGetStartedWindow.clickSendButton();
        Assert.assertTrue(botGetStartedWindow.verifyMessageReceivedByBot(query));
        Reporter.log("message sent");
        Assert.assertTrue(botGetStartedWindow.selectToppings());
        botGetStartedWindow.typeAMessage("bacon");
        botGetStartedWindow.clickSendButton();
        Reporter.log("pizza type is selected");
        botGetStartedWindow.typeAMessage("reset");
        botGetStartedWindow.clickSendButton();
        Assert.assertTrue(botGetStartedWindow.verifyBotWelcomeNote());
        Reporter.log("conversation reset");

    }

    @Test(priority = 5, description = "Verify bot response by sending just one alphabetic character", dataProvider = "botResponse", dataProviderClass = TestData.class)
    public void verifyBotResponseBySendingOneAlphabeticCharacter(String query) throws IOException, InterruptedException {

        botGetStartedWindow.typeAMessage(query);
        botGetStartedWindow.clickSendButton();
        Assert.assertTrue(botGetStartedWindow.verifyMessageReceivedByBot(query));
        Reporter.log("Login Successful");
        LogUtil.logDebug(botGetStartedWindow.verifyBotResponse(query));
        Thread.sleep(2000);
        Assert.assertEquals(botGetStartedWindow.verifyBotResponse(query),expectedResponseForOneAlpha);
        botGetStartedWindow.typeAMessage("reset");
        botGetStartedWindow.clickSendButton();
        Assert.assertTrue(botGetStartedWindow.verifyBotWelcomeNote());
        Reporter.log("conversation reset");



    }
}
