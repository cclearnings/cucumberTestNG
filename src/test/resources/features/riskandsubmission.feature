Feature: As a user, I should see Dashboard is loading with proper details.

  @regression
  Scenario: Verify user is able to add new risk
    Given I navigated to hub standard home page
    When I selected risk and submission
    Then I should navigate to Risk home page
    When I create new risk
    Then I should see the risk is added to dashboard

  @wip
  Scenario: Verify user is able to all details in progress summary
    Given I navigated to hub standard home page
    When I selected progress summary
    Then I should navigate to project home page


  @wip
  Scenario: Verify user is able to all details in progress summary
    Given I navigated to hub standard home page
    When I selected progress summary
    Then I should navigate to project home page
