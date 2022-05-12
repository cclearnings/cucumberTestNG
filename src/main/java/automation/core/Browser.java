package automation.core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.security.Key;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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
        WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement elem = null;
        try {
            w.until(ExpectedConditions.visibilityOf(driver.findElement(b)));
            elem = driver.findElement(b);
        }catch (NullPointerException np)
        {
            w.until(ExpectedConditions.elementToBeClickable(driver.findElement(b)));
            elem = driver.findElement(b);
        }catch (NoSuchElementException e)
        {
            log.info("Element not found");
        } catch (Exception ste)
        {
            elem = driver.findElement(b);
        }
        return elem;
    }


    public static List<WebElement> getElements(By b)
    {
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

        WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
        String username = "//*[@name='loginfmt']";
        String next = "//input[@type='submit']";
        String password = "//*[@name='passwd']";
        try {
             enterText(selector(username),"chandra.chandragiri@sticsoft.io");
             selectLocator(selector(next));
             enterText(selector(password),"Stic@2022");
             selectLocator(selector(next));
             selectLocator(selector(next));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectLocator(By b) {
        WebElement objSelect = getElement(b);
        WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
        w.until(ExpectedConditions.elementToBeClickable(objSelect));
        try {
            objSelect.click();
        } catch (Exception ex) {
            if(getElements(b).size() > 1)
            {
              getElements(b).get(1).click();
            }
        }
    }


    public static String getTimStamp()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }


    // As of now only xpath
    public static void selectValueFromDropDown(By b, String optionToSelect) {
        try{
          selectLocator(b);
        }catch (Exception ed)
        {
           if(getElements(b).size() > 0)
           {
               getElements(b).get(1).click();
           }
        }
        String dxpath = "//*[contains(text(),'"+optionToSelect+"') and contains(@class,'Dropdown-optionText')]";
        try {
          Thread.sleep(100);
            selectLocator(selector(dxpath));
        } catch (Exception ex) {
            if(getElements(selector(dxpath)).size() > 1)
            {
                getElements(selector(dxpath)).get(1).click();
            }
        }
    }

    // As of now only xpath
    public static void selectValueFromDropDownAfterVisibleText(By b, String optionToSelect) {
       WebElement e = getElement(selector("//div[@class=' css-1hwfws3']//input"));
       try {
           e.click();
           Thread.sleep(500);
           e.sendKeys(optionToSelect,Keys.ARROW_DOWN, Keys.TAB);
       }catch (Exception ee)
       {
           ee.printStackTrace();
       }
    }




    public static void selectValue(String optionToSelect) {
        String dxpath = "//*[contains(text(),'" + optionToSelect + "')]";
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


    public static void selectTabKey(By b) {

        Actions act = new Actions(driver);
        try {
            act.sendKeys(Keys.TAB);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void enterTextByLabel(String label, String textToEnter) {
        String dxpath = "//*[contains(text(),'" + label + "')]//parent::div//input";
        String dxpath1 = "//*[contains(text(),'" + label + "')]//parent::div//textarea";
        try {
            getElement(selector(dxpath)).sendKeys(textToEnter);
        } catch (NoSuchElementException | NullPointerException ex) {
            getElement(selector(dxpath1)).sendKeys(textToEnter);
        }
    }

    public static void enterText(WebElement ele, String textToEnter) {
        try {
            ele.sendKeys(textToEnter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



    public static void enterText(By b, String textToEnter) {

        try {
            getElement(b).sendKeys(textToEnter);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setTextToLocator(By b, String testToSet) {
        try {
            WebElement i = getElement(b);
            JavascriptExecutor j = (JavascriptExecutor)driver;
            j.executeScript("arguments[0].innerHTML='"+testToSet+"';", i);
            i.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.TAB);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void selectDate(By b, String dateToSelect) {

        try {
            selectLocator(b);
            Thread.sleep(100);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(dateToSelect, formatter);
            String month = date.getMonth().toString();
            int year = date.getYear();
            int dateNumber = date.getDayOfMonth();
            String xpath = "//span[text()="+dateNumber+"]";
            selectLocator(selector(xpath));
            Thread.sleep(100);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void enterUser(By b, String userToSelect) {
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            selectLocator(b);
            selectLocator(b);
            // TODO
            Thread.sleep(100);
            getElement(b).sendKeys(userToSelect);
            Thread.sleep(1000);
            w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ms-Persona-primaryText')]")));
            getElement(By.xpath("//div[contains(@class,'ms-Persona-primaryText')]")).click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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


    public static String getAttribute(By b, String atr) {
        String attr = "";
        try{
            attr = getElement(b).getAttribute(atr);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return attr;
    }




}

