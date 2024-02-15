@purchaseOrder
Feature: Purchase the order from Ecommerce

Background:
Given I open Login Page

  @regression
  Scenario Outline: Positive test of submitting the order
    Given I logged in with username <username> and password <password>
    When I add product <productName> to cart
    And I checkout product <productName> and submit the order
    Then I verify "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples: 
      | username       | password | productName     |
      | test@test.org  | 2321GbbO | ADIDAS ORIGINAL |
      | test2@test.org | 2321GbbO | ZARA COAT 3     |
      