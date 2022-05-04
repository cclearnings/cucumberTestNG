package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SampleTest {
    @Given("I navigate to home page")
    public void iNavigateToHomePage() {
        System.out.println("Hello");
    }

    @When("I enter valid cred")
    public void iEnterValidCred() {
        System.out.println("Hello 2");
    }

    @Then("I should navigate to admin page")
    public void iShouldNavigateToAdminPage() {
        System.out.println("Hello 3");
    }


}
