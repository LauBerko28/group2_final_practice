@smoke
Feature: Docuport New Client Creation Feature Login as an Advisor

  #UI SCENARIO - Anna - I have started this a while ago but haven't finished it
  @docuportCreateNewClient
  Scenario Outline: Create a new "Personal" client and login as a new client

    Given the user is logged in as an advisor
    When the user creates a new client with the following details:
      | First name   | Last name   | Email address  | Phone number   | Password          | Confirm password|
      | <First Name> | <Last Name> | <Client Email> | <Phone Number> | <Client Password> |<Client Password>|
    And the user validates that new client was created
    And the user logs out as an advisor
    Then the user should be able to log in as a new client using:
      | Email address  | Password          |
      | <Client Email> | <Client Password> |
    Examples:
      | First Name | Last Name | Client Email         | Phone Number | Client Password |
      | John       | Doe       | john.doe@example.com | 3453457898   | Password1       |
      | Mickey     | Rourke    | mickeyRou@gml.com    | 2346745345   | Pw123RD         |
      | Bruce      | Willis    | bruce@lead.com       | 6789341212   | Bruce1267       |


  #DB SCENARIO
  @docuportDatabaseClientVerification
  Scenario: Login as and advisor to Docuport and verify the client was created



  #UI SCENARIO
  @docuportClientSearchFunctionality
  Scenario: Verify Client Search functionality, login as an advisor