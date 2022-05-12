package automation.pages;

import automation.core.Browser;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramSubmission extends Browser {

    public static String title = "Program Submission";
    public static String search = "//input[@placeholder='Searchjs']";
    public static String consolidatedView = "//span[text()='Consolidated View']";
    public static String psLevel2 = "//span[text()='Select PS_Level 2js']//parent::span//parent::div";
    public static String clear = "//div[text()='Clear']";

    public static boolean onPage()
    {
        return title.equals(driver.getTitle());
    }

    static WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(10));


    public static List<HashMap<String, String>> getTableDetails()
    {
        String  table = "//table";
        List<HashMap<String , String>> details = new ArrayList<>();
        HashMap<String, String> eachRow = new HashMap<>();
        WebElement tableContent = getElement(selector(table));
        assert tableContent != null;
        List<WebElement> rows = tableContent.findElements(By.xpath("//tr"));
        List<String> headers = rows.get(0).findElements(By.xpath("//th")).stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println(headers);
        for(int i = 1 ;  i < rows.size()-1 ;  i ++) {
            eachRow = new HashMap<>();
            for (int j = 2; j < headers.size() ; j++) {
                String dx = "//table/tbody/tr["+i+"]/td["+j+"]";
                eachRow.put(headers.get(j-1), getElement(selector(dx)).getText());
            }
            details.add(eachRow);
        }
        return details;
    }

    public static void searchFor(String searchKey, String value)
    {
         enterText(selector(search), value);
    }


    public static void verifyFilters(String filterName, String value)
    {
        Assert.assertFalse(getElement(selector(psLevel2)).isEnabled());
        selectValueFromDropDown(selector(consolidatedView), "Level 2");
        Assert.assertTrue(getElement(selector(psLevel2)).isEnabled());
        selectValueFromDropDown(selector(psLevel2), "Level 2-1");
        selectLocator(selector(clear));
        Assert.assertFalse(getElement(selector(psLevel2)).isEnabled());
    }

}
