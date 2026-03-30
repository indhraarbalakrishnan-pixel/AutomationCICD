@tag
Feature: Error Validation


@tag2
Scenario Outline: Login Error Validation
Given I landed on Ecommerce page
When Logged in with username <name> and password <password>
Then verify <message> message is displayed
Examples:
|name                         |password |message                     |
|indhraabalakrishnan@gmail.com|Hanan@143|Incorrect email or password.|

