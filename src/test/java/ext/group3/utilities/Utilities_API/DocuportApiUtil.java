package ext.group3.utilities.Utilities_API;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class DocuportApiUtil {


    public static String getAccessToken(String email, String password){

        String jsonBody = "{\n" +
                "\"usernameOrEmailAddress\": \"" +email+"\",\n" +
                "\"password\": \"" + password + "\"\n" +
                "}";

        String accessToken = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post(Environment.BASE_URL + "/api/v1/authentication/account/authenticate")
                .then().assertThat().statusCode(200)
                .and().extract().path("user.jwtToken.accessToken");

//       System.out.println("accessToken = " + accessToken);
//        assertThat("accessToken is empty or null", accessToken, not(emptyOrNullString()));

        return "Bearer " + accessToken;
    }

}
