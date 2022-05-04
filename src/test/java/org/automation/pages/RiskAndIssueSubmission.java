package org.automation.pages;


import org.automation.core.Browser;
import org.automation.core.Config;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RiskAndIssueSubmission extends Browser {

    private static String createRisk = "//div[text()='Create Risk/Issuejs']";
    private static String cancelButton = "//div[text()='Cancel']";
    private static String selectTracks = "//span[text()='Select Track']";
    private static String selectCommons = "//span[text()='Select Common']";
    private static String selectProgramRisks = "//div[text()='Select Program Riskjs']";

    private static String countryRisk = "//*[contains(text(),'Select Countryjs')]";
    private static String riskCompass = "//*[contains(text(),'Select Risk Compasjs')]";
    private static String riskissueStmt = "//*[contains(text(),'Risk/Issue Statementjs')]";
    private static String riskissueDesc = "//*[contains(text(),'Risk/Issue Descriptionjs')]//following-sibling::div";
    private static String riskAppetite = "//*[contains(text(),'Select Risk Appetitejs')]";
    private static String impactStmt = "//*[contains(text(),'Impact Statementjs')]//following-sibling::div";




    static WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(10));


    public static void createNewRisk()
    {
        JSONObject js = Config.testData("dummy");
        waitTime.until(ExpectedConditions.presenceOfElementLocated(By.xpath(createRisk)));
        selectLocator(selector(createRisk));
        waitTime.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(cancelButton)));
        selectValue(selector(selectTracks), js.get("track").toString());
        selectValue(selector(selectCommons), js.get("common").toString());
        selectValue(selector(selectProgramRisks), js.get("program_risk").toString());
        selectValue(selector(countryRisk), js.get("country").toString());
        selectChoice(js.get("country").toString());
        selectValue(selector(riskCompass), js.get("risk_compass").toString());
        selectValue(selector(riskissueStmt), js.get("risk_issue_statement").toString()+ getTimStamp());
        enterText(selector(riskissueDesc), js.get("risk_issue_description").toString()+getTimStamp());

    }


}
