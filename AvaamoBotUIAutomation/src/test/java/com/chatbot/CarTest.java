package com.chatbot;

import com.pages.Cars24Home;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CarTest {

    public WebDriver driver;
    public Cars24Home home;
    public String text = "Maruti";
    public String loc = "USE CURRENT LOCATION";

    @BeforeMethod
            public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://www.cars24.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        home = new Cars24Home(driver);

    }

    @Test(priority = 1)
    public void verfiyInputGivern() throws Exception {
        home.searchACar(text);
        home.selectOptionFromDropDown();
        home.useCurrentLocation(loc);
      //We can add assertion based on our requirements"


    }

   @AfterMethod
    public void tearDown()
    {
        driver.quit();

    }
}
