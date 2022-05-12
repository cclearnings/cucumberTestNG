package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import automation.core.Browser;
import automation.pages.HubStandard;
import automation.pages.RiskAndIssueSubmission;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiskSteps extends Browser{

    @Given("I navigated to hub standard home page")
    public void iNavigatedToHubStandardHomePage() {
        Assert.assertTrue(HubStandard.onPage());
        log.info("On Home Page after successful login");
    }

    @When("I selected risk and submission")
    public void iSelectedRiskAndSubmission() {
        HubStandard.navigateToRisk();

    }

    @Then("I should navigate to Risk home page")
    public void iShouldNavigateToRiskHomePage() {
        log.info("Navigate to Risk Page");
    }

    @When("I create new risk")
    public void iCreateNewRisk() {
        RiskAndIssueSubmission.createNewRisk();
        log.info("Created Risk Successfully");
    }

    @Then("I should see the risk is added to dashboard")
    public void iShouldSeeTheRiskIsAddedToDashboard() {
        Assert.assertTrue(RiskAndIssueSubmission.created);
    }

    @And("get all the risk details")
    public void getAllTheRiskDetails() {
      HubStandard.navigateToRisk();
      log.info("navigated to Risk section");
    }

    @When("I select filter on headers")
    public void iSelectFilterOnHeaders(Map<String, String> filters) {
       filters.forEach(RiskAndIssueSubmission::applyFilters
        );
    }

    @Then("I should see filtered details")
    public void iShouldSeeFilteredDetails() {
        List<HashMap<String, String>> afterFilers = RiskAndIssueSubmission.getTableDetails();
        System.out.println(afterFilers);
        for (HashMap<String, String> valid : afterFilers ) {
            Assert.assertEquals("Level 2", valid.get("Track"));
        }
    }

}
