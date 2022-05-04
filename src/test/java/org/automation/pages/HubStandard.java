package org.automation.pages;

import org.automation.core.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HubStandard extends Browser {

    private static String title =  "Home";
    static WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static boolean onPage()
    {
        return title.equals(driver.getTitle());
    }


    public static void navigateToRisk()
    {
       waitTime.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Risk and Issue Submission']")));
       driver.findElement(By.xpath("//p[text()='Risk and Issue Submission']")).click();
       Browser.selectNewTab();

    }



}
