package io.swagger.petstore.crudtest;

import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.steps.UsersSteps;
import io.swagger.petstore.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UsersCrudTest extends TestBase {
    static String  id = "12";
    static String userName = "Anya129";
    static String firstName = "Anya";
    static String lastName = "Akk";
    static String email = "Anu@example.com";
    static String password = "test123";
    static String phone = "9876543212";
    static String userStatus = "1";
    static int userID;

    @Steps
    UsersSteps usersSteps;

    @Title("This test will Create a new User")
    @Test
    public void test001(){
        ValidatableResponse response = usersSteps.createNewUser(id, userName,firstName,lastName,email,password,phone,userStatus);
        response.log().all().statusCode(200);

    }
    @Title("Verify user was added")
    @Test
    public void test002() {
        HashMap<String, Object> userMap = usersSteps.getUserByUserName(userName);
        Assert.assertThat(userMap, hasValue(userName));
    }
    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        userName = userName + "_updated";
        usersSteps.updateUser(id,userName,firstName,lastName,email,password,phone,userStatus);
        HashMap<String, Object> userMap = usersSteps.getUserByUserName(userName);
        Assert.assertThat(userMap, hasValue(userName));
    }
    @Title("Verify user was deleted")
    @Test
    public void test004() {
        usersSteps.deleteUser(userName).statusCode(200).log().all();

    }



}
