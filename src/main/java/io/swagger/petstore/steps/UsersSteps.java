package io.swagger.petstore.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.constants.EndPoints;
import io.swagger.petstore.model.UsersPojo;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UsersSteps {
   @Step("Create user with userName : {1}, firstName:{2}, lastName: {3},email:{4}")
   public ValidatableResponse createNewUser(String id, String userName, String firstName, String lastName, String email, String password, String phone, String userStatus) {

      UsersPojo usersPojo = new UsersPojo();
      usersPojo.setId(id);
      usersPojo.setUsername(userName);
      usersPojo.setFirstName(firstName);
      usersPojo.setLatName(lastName);
      usersPojo.setEmail(email);
      usersPojo.setPassword(password);
      usersPojo.setPhone(phone);
      usersPojo.setUserStatus(userStatus);

      return SerenityRest.given().log().all()
              .header("Content-Type", "application/json")
              //   .header("accept", "application/json")
              //   .header("Authorization", "special-key")
              .body(usersPojo)
              .when()
              .post(EndPoints.CREATE_USER)
              .then();
   }

   @Step("Getting the user information with firstName: {0}")
   public HashMap<String, Object> getUserByUserName(String username) {
//        String p1 = "findAll{it.username == '";
//        String p2 = "'}.get(0)";
      return SerenityRest.given().log().all()
              .when()
              .pathParam("username", username)
              .get(EndPoints.GET_USER_BY_USERNAME)
              .then()
              .statusCode(200)
              .extract().path("");
   }

   @Step("Updating User information with userName: {0}, firstName: {1}, lastName: {2}, email: {3}, programme: {4} and courses: {5}")

   public ValidatableResponse updateUser(String id, String userName, String firstName, String lastName, String email, String password, String phone, String userStatus) {
      UsersPojo usersPojo = new UsersPojo();
      usersPojo.setId(id);
      usersPojo.setUsername(userName);
      usersPojo.setFirstName(firstName);
      usersPojo.setLatName(lastName);
      usersPojo.setEmail(email);
      usersPojo.setPassword(password);
      usersPojo.setPhone(phone);
      usersPojo.setUserStatus(userStatus);

      return SerenityRest.given().log().all()
              .contentType(ContentType.JSON)
              .pathParam("username", userName)
              .when()
              .body(usersPojo)
              .put(EndPoints.UPDATE_USER_BY_USERNAME)
              .then();

   }

   @Step("Deleting User information with userName: {0}")

   public ValidatableResponse deleteUser(String username) {
      return SerenityRest.given().log().all()
              .pathParam("username", username)
              .when()
              .delete(EndPoints.DELETE_USER_BY_USERNAME)
              .then();

   }
}