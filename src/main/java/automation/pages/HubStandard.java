package automation.pages;

import automation.core.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HubStandard extends Browser {

    private static String  riskPage = "//p[text()='Risk and Issue Submission']";
    private static String  progressSubmission = "//p[text()='Program Progress  Submission']";


    private static String title =  "Home";
    static WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static boolean onPage()
    {
        return title.equals(driver.getTitle());
    }

    public static void navigateToRisk()
    {
       waitTime.until(ExpectedConditions.presenceOfElementLocated(selector(riskPage)));
       selectLocator(selector(riskPage));
       Browser.selectNewTab();
       waitTime.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table")));

    }

    public static void navigateToProgressSummaryDashboard()
    {
        waitTime.until(ExpectedConditions.presenceOfElementLocated(selector(progressSubmission)));
        selectLocator(selector(progressSubmission));
        Browser.selectNewTab();
        waitTime.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table")));
    }

}
