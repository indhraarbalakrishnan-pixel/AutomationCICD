@tag
Feature: Purchase the Order for Ecommerce Website





@Regression
Scenario Outline: Positive test of submitting the order
Given I landed on Ecommerce page
Given Logged in with username <name> and password <password>
When I add product <productname> and <productname1> from cart
And  Checkout <productname> and submit the order
Then verify <message> is displayed on the Confirmationpage
Examples:
|name                         |password   |productname|productname1   |message|
|indhraabalakrishnan@gmail.com|Hanuman@143|ZARA COAT 3|ADIDAS ORIGINAL|THANKYOU FOR THE ORDER.|