@tag
Feature: Purchase the order from Ecommerces website

Background: 
Given I landed on Ecommerce on page


  @Regression
  Scenario Outline: positive Test of submitting the order
    Given login with username<name> ans password<password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confrmPage

    Examples: 
      | name                      | password   | productName|
      | ashokpinninti77@gmail.com | Rahul@#123 | ZARA COAT 3|
      | sivaji88@gmail.com | Siva@#123 | ADIDAS ORIGINAL|
      