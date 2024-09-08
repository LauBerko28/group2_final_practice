package ext.group3.utilities.Utilities_API;

import ext.group3.utilities.Utilities_UI.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocuportAPITestBase {

    protected static RequestSpecification reqSpec;
    protected static ResponseSpecification resSpec;

    @BeforeAll
    public static void setUp () {
        //baseURI = "https://beta.docuport.app";
        baseURI = ConfigurationReader.getProperties("docuport.base.url");

        reqSpec = given().accept(ContentType.JSON)
                .and().header("Authorization", getAccessToken("employee"));

        resSpec = expect().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON);
    }


    public static String getAccessToken (String userType) {

        String userEmail = "";
        String userPassword = "";

        //TODO: add all other cases for each user type
        switch (userType){
            case "employee":
                userEmail = ConfigurationReader.getProperties("employee_username");  // b1g2_employee@gmail.com
                userPassword = ConfigurationReader.getProperties("employee_password");
                break;
            case "supervisor":
                userEmail = ConfigurationReader.getProperties("supervisor_username");
                userPassword = ConfigurationReader.getProperties("supervisor_password");
                break;
            case "advisor":
                userEmail = ConfigurationReader.getProperties("advisor_username");
                userPassword = ConfigurationReader.getProperties("advisor_password");
                break;
            default:
                throw new RuntimeException("Invalid user type: \'" + userType + "\' is not among the options or is not implemented in switch case");
        }


        String reqBody = "{\n" +
                "                 \"usernameOrEmailAddress\": \""+ userEmail +"\",\n" +
                "                 \"password\": \""+ userPassword +"\"\n" +
                "             }";

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and().body(reqBody)
                .when().post("/api/v1/authentication/account/authenticate");


        // System.out.println("Access Token: " + response.path("user.jwtToken.accessToken"));
        String accessToken = response.path("user.jwtToken.accessToken");
        assertTrue(accessToken != null && !accessToken.isEmpty());

        return "Bearer " + accessToken;
    }


}
