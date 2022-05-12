Feature: As a user, I should see Dashboard is loading with proper details.

  @regression @risk
  Scenario: Verify user is able to add new risk
    Given I navigated to hub standard home page
    When I selected risk and submission
    Then I should navigate to Risk home page
    When I create new risk
    Then I should see the risk is added to dashboard

#  @regression
#  Scenario: Verify filters are working
#    Given I navigated to hub standard home page
#    And get all the risk details
#    When I select filter on headers
#      | Track       | Level 2 |
#    Then I should see filtered details

  # Parameterized - This can be done
  # Lets start doing in Novartis - Need Support from Novartis
  # Analyze how RPA works here - Need support from Novartis
  # Trigger jobs one code pushed to GIT - This can be done
  # Same locators for all Hubs - Praveen
  # Emails - This can be done
