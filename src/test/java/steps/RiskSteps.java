package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.automation.core.Browser;
import org.automation.pages.HubStandard;
import org.automation.pages.RiskAndIssueSubmission;
import org.junit.Assert;

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
        log.info("TODO");
    }
}
