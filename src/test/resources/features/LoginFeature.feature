@smoke
Feature: Docuport Login Logout and Negative Testing Feature

   #UI SCENARIO
  @docuportLoginForALlUsers
  Scenario Outline: Login Functionality for multiple users
    When user enters credentials as "<username>" "<password>"
    Then user should see the "Batch1 Group3" displayed

    Examples:
      | username                  | password |
      | b1g3_client@gmail.com     | Group3   |
      | b1g3_advisor@gmail.com    | Group3   |
      | b1g3_supervisor@gmail.com | Group3   |
      | b1g3_employee@gmail.com   | Group3   |


  #UI SCENARIO
  @docuportLoginNegativeForALlUsers
  Scenario Outline: Negative Login Functionality for multiple users
    When user enters credentials as "<username>" "<password>"
    Then user should see the "Batch1 Group3" displayed

    Examples:
      | username                 | password |
      | b1g3_@gmail.com          | Group3   |
      | b1g3_advisor@gmail.com   | Group5   |
      | b1g3_supervisorgmail.com | Group3   |
      | 123                      | Group3   |




  #UI SCENARIO - NOT SURE AS IT CAN BE PART OF THE LOGIN AS WELL JUST THE LAST STEP, KIND OF LIKE E2E Testing
  @docuportLogoutFunctionality
  Scenario: Verify that the user can logout successfully