package automation.pages;


import automation.core.Browser;
import automation.core.Config;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class  RiskAndIssueSubmission extends Browser {

    private  static String title = "";
    public  static boolean created = false;


    private static String createRisk = "//div[text()='Create Risk/Issuejs']";
    private static String cancelButton = "//div[text()='Cancel']";
    private static String selectTracks = "//label[text()='Track']//following-sibling::div";
    private static String selectCommons = "//label[text()='Common']//following-sibling::div";
    private static String selectProgramRisks = "//div[text()='Select Program Riskjs']";
    private static String selectProgramRisks2 = "//div[@class=' css-1hwfws3']//input";
    private static String level3 = "//label[text()='Level 3js']//following-sibling::div";
    private static String selectOnEmptySpace = "//p[text()='Add New Itemjs']";
    private static String countryRisk = "//label[text()='Countryjs']//following-sibling::div";
    private static String riskCompass = "//label[text()='Risk Compasjs']//following-sibling::div";
    private static String riskStmt =  "Risk/Issue Statementjs";
    private static String riskissueDesc = "Risk/Issue Descriptionjs";
    private static String riskAppetite = "//label[text()='Risk Appetitejs']//following-sibling::div";
    private static String impactStmt = "Impact Statementjs";
    private static String financial_impact= "Financial Impact (Million USD)js";
    private static String impact = "//label[text()='Impactjs']//following-sibling::div";
    private static String likely_hood = "//label[text()='Likelihoodjs']//following-sibling::div";
    private static String risk_issue_owner = "//input[@placeHolder='Please add Owner']";
    private static String risk_issue_status = "//label[text()='Risk/Issue Statusjs']//following-sibling::div";
    private static String risk_issue_strategy = "//label[text()='Risk/Issue Strategyjs']//following-sibling::div";
    private static String response_plan = "Response Planjs";
    private static String response_owner = "//input[@placeHolder='Please add Response Ownersjs']";
    private static String  response_date = "//label[text()='Response Datejs']//following-sibling::div";
    private static String  revised_response_date = "//label[text()='Revised Response Datejs']//following-sibling::div";
    private static String  tracking_comments = "Tracking Commentjs";
    private static String  risk_issue_closure_date = "//label[text()='Risk/Issue Closure Datejs']//following-sibling::div";
    private static String  reason_for_closing= "Reasons For Closing Risk/Issuejs";
    private static String  closing_comment= "Risk/Issue Closing Commentsjs";
    private static String  issue_raised_by = "//input[@placeHolder='Please add Risk/Issue Raised byjs']";
    private static String  save_risk = "//div[text()='Save']";
    private static String  saveButton = "//div[text()='Save']//preceding::button[1]//following-sibling::button";
    private static String  program_risk = "//div[@class=' css-1uccc91-singleValue']";



    public static boolean onPage()
    {
        return title.equals(driver.getTitle());
    }

    static WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static void createNewRisk() {
        JSONObject js = Config.testData("dummy");
        waitTime.until(ExpectedConditions.presenceOfElementLocated(By.xpath(createRisk)));
        selectLocator(selector(createRisk));
        waitTime.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(cancelButton)));
        selectValueFromDropDown(selector(selectTracks), js.get("track").toString());
        selectValueFromDropDown(selector(selectCommons), js.get("common").toString());
        selectValueFromDropDown(selector(level3), js.get("level_3").toString());
        setTextToLocator(selector(selectProgramRisks2), js.get("program_risk").toString());
        setTextToLocator(selector(selectProgramRisks),js.get("program_risk").toString());
        selectValueFromDropDown(selector(countryRisk), js.get("country").toString());
        selectLocator(selector(selectOnEmptySpace));
        selectValueFromDropDown(selector(riskCompass), js.get("risk_compass").toString());
        selectChoice(js.get("event_type").toString());
        enterTextByLabel(riskStmt, js.get("risk_issue_statement").toString()+ getTimStamp());
        enterTextByLabel(riskissueDesc, js.get("risk_issue_description").toString()+ getTimStamp());
        selectValueFromDropDown(selector(riskAppetite), js.get("risk_appetite").toString());
        enterTextByLabel(impactStmt, js.get("impact_statement").toString()+ getTimStamp());
        enterTextByLabel(financial_impact, js.get("financial_impact_million_USD").toString());
        selectValueFromDropDown(selector(impact), js.get("Impact").toString());
        selectValueFromDropDown(selector(likely_hood), js.get("Likelihood").toString());
        enterUser(selector(risk_issue_owner), js.get("risk_issue_owner").toString());
        selectValueFromDropDown(selector(risk_issue_status), js.get("risk_issue_status").toString());
        selectValueFromDropDown(selector(risk_issue_strategy), js.get("risk_issue_strategy").toString());
        enterTextByLabel(response_plan, js.get("response_plan").toString());
        enterUser(selector(response_owner), js.get("response_owners").toString());
        enterTextByLabel(tracking_comments, js.get("tracking_comment").toString()+ getTimStamp());
        selectValueFromDropDown(selector(reason_for_closing), js.get("reasons_dor_closing_risk_issue").toString()+ getTimStamp());
        enterTextByLabel(closing_comment, js.get("risk_issue_closing_comments").toString()+ getTimStamp());
        enterUser(selector(issue_raised_by), js.get("risk_issue_raised_by").toString()+ getTimStamp());
        selectDate(selector(response_date),"May 22, 2022");
        selectDate(selector(revised_response_date),"May 28, 2022");
        selectDate(selector(risk_issue_closure_date),"May 29, 2022");
        if (getElement(selector(saveButton)).isEnabled()) {
            selectLocator(selector(save_risk));
            created =  true;
        }else
        {
            log.info("Unable to Save the Risk as button disabled");
        }
    }


    public static void applyFilters(String filterName, String value)
    {
        filterName = filterName.toLowerCase();
        switch(filterName) {
            case "track":
                String path = "//span[text()='Select Track']";
                selectValueFromDropDown(selector(path), value);
                break;
            default:
                log.info("Will implement later");
        }

    }

    public static List<HashMap<String, String>>  getTableDetails()
    {
        String  table = "//table";
        List<HashMap<String , String>> details = new ArrayList<>();
        HashMap<String, String> eachRow = new HashMap<>();
        WebElement tableContent = getElement(selector(table));
        assert tableContent != null;
        List<WebElement> rows = tableContent.findElements(By.xpath("//tr"));
        List<String> headers = rows.get(0).findElements(By.xpath("//th")).stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println(headers);
        for(int i = 2 ;  i < rows.size()-1 ;  i ++) {
            eachRow = new HashMap<>();
            for (int j = 2; j < headers.size() - 1 ; j++) {
                String dx = "//table/tr["+i+"]/td["+j+"]";
                eachRow.put(headers.get(j-1), getElement(selector(dx)).getText());
            }
            details.add(eachRow);
        }
        System.out.println(details);
        return details;
    }



}
