package org.automation.core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class Browser {

    public static WebDriver driver = null;
    public static final Logger log = LoggerFactory.getLogger(Browser.class);

    public static void startDriver() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(Config.url);
        driver.manage().window().maximize();
        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("loginfmt"))));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public static synchronized Object execJavascript(String script, Object... args) {
        try {
            JavascriptExecutor scriptExe = ((JavascriptExecutor) driver);
            return scriptExe.executeScript(script, args);
        } catch (Exception e) {
           return "";
        }
    }

    public static byte[] getScreenShot() {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return null;
        }
    }

    public static void waitUntil(Boolean condition, int seconds) {
        new WebDriverWait(Browser.driver,  Duration.ofSeconds(seconds)).until((WebDriver driver) -> condition);
    }

    public static void waitUntil(Boolean b) {
        waitUntil(b, 30);
    }

    public static boolean isPageLoaded() {
        String state = (String) Browser.execJavascript("return document.readyState;");
        return state.matches("complete|loaded|interactive");
    }

    public static By selector(String value) {
        // Will update later
        String byType = "xpath";
        switch (byType) {
            case "id":
                return By.id(value);
            case "linkText":
            case "link text":
                return By.linkText(value);
            case "name":
                return By.name(value);
            case "partialLinkText":
            case "partial link text":
                return By.partialLinkText(value);
            case "tagName":
            case "tag name":
                return By.tagName(value);
            case "xpath":
                return By.xpath(value);
            case "className":
            case "class":
            case "class name":
                return By.className(value);
            case "cssSelector":
            case "css selector":
            case "css":
                return By.cssSelector(value);
            default:
                return null;
        }
    }


    public static WebElement getElement(By b)
    {
        waitUntil(driver.findElement(b).isDisplayed(),10);
        List<WebElement> elems = new ArrayList<>();
        try {
            elems = driver.findElements(b);
        }catch (NoSuchElementException e)
        {
            log.info("Element not found");
        }
        return elems.get(0);
    }


    public static List<WebElement> getElements(By b)
    {
        waitUntil(driver.findElement(b).isDisplayed(),10);
        List<WebElement> elems = new ArrayList<>();
        try {
            elems = driver.findElements(b);
        }catch (NoSuchElementException e)
        {
            log.info("Element not found");
        }
        return elems;
    }



    public void login() {
        WebElement username = driver.findElement(By.name("loginfmt"));
        username.sendKeys("chandra.chandragiri@sticsoft.io");
        try {
            Thread.sleep(2000);
            WebElement next = driver.findElement(By.xpath("//input[@type='submit']"));
            next.click();
            Thread.sleep(2000);
            WebElement password = driver.findElement(By.name("passwd"));
            password.sendKeys("Stic@2022");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@type='submit']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@type='submit']")).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void selectLocator(By b) {
        WebElement objSelect = getElement(b);
        try {
            objSelect.click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static String getTimStamp()
    {
        Timestamp instant= Timestamp.from(Instant.now());
        return instant.toString();
    }


    // As of now only xpath
    public static void selectValue(By b, String optionToSelect) {
        try{
            selectLocator(b);
        }catch (Exception e)
        {
            getElements(b).get(1).click();
        }
        String dxpath = "//*[contains(text()='" + optionToSelect + "')]";
        WebElement objSelect = getElement(By.xpath(dxpath));
        try {
            objSelect.click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void selectChoice(String optionToSelect) {

        String dxpath = "//*[contains(text(),'"+optionToSelect+"') and contains(@class,'ChoiceField')]";
        WebElement objSelect = getElement(By.xpath(dxpath));
        try {
            objSelect.click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void selectChoice(WebElement ele) {

        try {
            ele.click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void enterText(By b, String textToEnter) {
        WebElement ele = getElement(b);
        try {
            ele.sendKeys(textToEnter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void enterText(WebElement ele, String textToEnter) {
        try {
            ele.sendKeys(textToEnter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public static void enterUser(WebElement ele, String userToSelect) {


    }

    public static void selectNewTab() {
        waitUntil(driver.getWindowHandles().size() == 2);
        ArrayList<String> windows = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
    }

    public static void selectWindow(String title) {

    }

    public static boolean isStarted() {
        return driver != null;
    }




}

