
@tag
  Feature: ErrorValiadtion
   
  @tag2
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce on page
    When login with username<name> ans password<password>
    Then "Incorrect email or password." message is displayed


     Examples:
     
      | name                      | password    | 
      | ashokpinninti77@gmail.com | Rahul@#1234 |
      
