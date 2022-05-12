package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import automation.core.Browser;
import automation.pages.HubStandard;
import automation.pages.ProgramSubmission;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProgramSubmissionSteps extends Browser {

    private static String global_var1 = "";
    private static String global_var2 = "";
    private static String global_var3 = "";
    private static String global_var4 = "";
    private static String global_var5 = "";
    private static String global_var6 = "";


    @And("I navigated to Progress Summary page")
    public void iNavigatedToProgressSummaryPage() {
        HubStandard.navigateToProgressSummaryDashboard();
        log.info("Navigate to Program Summary Page");
    }

    @When("I search with search_keywords words")
    public void iSearchWithSearch_keywordsWords(Map<String, String> filters) {
        global_var1 = filters.get("search_word_1");
        filters.forEach(ProgramSubmission::searchFor);
    }

    @Then("I should see table should filter based on search results")
    public void iShouldSeeTableShouldFilterBasedOnSearchResults() {
        List<HashMap<String, String>> searchResults = ProgramSubmission.getTableDetails();
        for (HashMap<String, String> valid : searchResults) {
            Assert.assertTrue(valid.containsValue(global_var1));
        }
    }

    @And("I see filters are empty")
    public void iSeeFiltersAreEmpty() {
        Assert.assertEquals("true", getAttribute(selector(ProgramSubmission.psLevel2), "aria-disabled"));
    }

    @When("I select filters")
    public void iSelectFilters() {
        selectValueFromDropDown(selector(ProgramSubmission.consolidatedView), "Level 2");
    }

    @Then("I should see other filters are enabled to select")
    public void iShouldSeeOtherFiltersAreEnabledToSelect() {
        Assert.assertEquals("false", getAttribute(selector(ProgramSubmission.psLevel2), "aria-disabled"));

    }

    @When("I clear the filters")
    public void iClearTheFilters() {
        selectLocator(selector(ProgramSubmission.clear));
    }

    @Then("I should see filters are empty")
    public void iShouldSeeFiltersAreEmpty() {
        Assert.assertEquals("true", getAttribute(selector(ProgramSubmission.psLevel2), "aria-disabled"));
    }
}
