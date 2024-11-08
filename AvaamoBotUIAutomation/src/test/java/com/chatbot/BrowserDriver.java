package com.chatbot;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Set;

public class BrowserDriver implements WebDriver {


        private WebDriver driver;
        private final String browserName;
        private final int timeout = 30;

        public BrowserDriver(String browserName) {
            this.browserName = browserName;
            this.driver = createDriver(browserName);
        }

        private WebDriver createDriver(String browserName) {
            if (browserName.toUpperCase().equals("FIREFOX"))
            return firefoxDriver();

            if (browserName.toUpperCase().equals("CHROME"))
            return chromeDriver();

            throw new RuntimeException ("invalid browser name");
        }

        private WebDriver chromeDriver() {
            try {

                return new ChromeDriver();
            } catch (Exception ex) {
                throw new RuntimeException
                        ("could not create chrome driver");
            }
        }


            private WebDriver firefoxDriver() {
            try {
                return new FirefoxDriver();
            }
            catch (Exception ex) {
                throw new RuntimeException
                        ("could not create the firefox driver");
            }
        }

        @Override
        public String toString() {
            return this.browserName;
        }

        public WebDriver driver() {
            return this.driver;
        }

        @Override
        public void close() {
            driver().close();
        }

        @Override
        public WebElement findElement(By locator) {
            return driver().findElement(locator);
        }

        @Override
        public List findElements(By arg0) {
            return driver().findElements(arg0);
        }

        @Override
        public void get(String arg0) {
            driver().get(arg0);
        }

        @Override
        public String getCurrentUrl() {
            return driver().getCurrentUrl();
        }

        @Override

        public String getPageSource() {
            return driver().getPageSource();
        }

        @Override
        public String getTitle() {
            return driver().getTitle();
        }


    @Override
        public String getWindowHandle() {
            return driver().getWindowHandle();
        }

        @Override
        public Set<String> getWindowHandles() {
            return driver().getWindowHandles();
        }

        @Override
        public Options manage() {
            return driver().manage();
        }

        @Override
        public Navigation navigate() {
            return driver().navigate();
        }

        @Override
        public void quit() {
            driver().quit();
        }

        @Override
        public TargetLocator switchTo() {
            return driver().switchTo();
        }


    }

