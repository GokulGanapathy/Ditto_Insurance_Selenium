#Author: gokulganapathy9500@gmail.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Ditto Insurance Premium Check
  The Cucumber Framework to test the Insurance Products Premium

	Background: Open and close the browser
	Given Open the browser

  @tag1
  Scenario: Ditto Insurance Premium Test
    Given Select the desired Insurance Plan
    When Select to whom we need to buy Insurance
    And Enter the basic Details required
    Then click on Calculate the premium and select addons Required if any
    And Validate the Total premium with other premium sum
    Then Close the browser
    
