
@errorValidation
Feature: Error validation

  @errorValidation
  Scenario Outline: Negative test to login with wrong username or password
    Given I open Login Page
    When I logged in with username <username> and password <password>
    Then I verify "Incorrect email or password." message is displayed

    Examples: 
      | username       | password |
      | test@test.org  | 2321Gbb  |
      | test3@test.org | 2321GbbO |
