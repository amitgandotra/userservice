package com.ibps.openapi;

import com.ibps.api.usersvcs.model.CreateUserRequest;
import com.ibps.api.usersvcs.model.User;
import com.ibps.openapi.exception.Errors;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.with;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
public class UserServiceTest extends BaseTest {

    @Test
    /*
     *
     */
    public void testCreateUser_happy_path() throws Exception {
        User user = createUser("create-user-1", "create-user-1");
        CreateUserRequest request = new CreateUserRequest();
        request.setUser(user);

       System.out.println(getJsonFromObject(request));

        with()
                .body(request)
                .when()
                .post(resource.getUserApiLocation())
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("userId", equalTo(user.getUserId()))
                .body("email", equalTo(user.getEmail()))
                .body("username", equalTo(user.getUsername()))
                .body("firstName", equalTo(user.getFirstName()))
                .body("lastName", equalTo(user.getLastName()))
                .body("locale", equalTo(user.getLocale()))
                .body("phones", Matchers.hasSize(2))
                .body("phones[0].phoneNo", equalTo(user.getPhones().get(0).getPhoneNo()));


    }
    @Test
    /*
     *
     */
    public void testGetUser_happy_path() throws Exception {
        User user=createUser("john1","John Smith");
        with()
                .when()
                .get(resource.getUserApiLocation("john1") )
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("userId", equalTo(user.getUserId()))
                .body("email", equalTo(user.getEmail()))
                .body("username", equalTo(user.getUsername()))
                .body("firstName", equalTo(user.getFirstName()))
                .body("lastName", equalTo(user.getLastName()))
                .body("locale", equalTo(user.getLocale()))
                .body("phones", Matchers.hasSize(2))
                .body("phones[0].phoneNo", equalTo(user.getPhones().get(0).getPhoneNo()));


    }

    @Test
    /*
     *
     */
    public void testDeleteUser_happy_path() throws Exception {
        with()
                .when()
                .delete(resource.getUserApiLocation("john1" ))
                .then()
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    /*
     *
     */
    public void testGetUsers_happy_path() throws Exception {
        User user=createUser("john1","John Smith");
        with()
                .when()
                .get(resource.getUserApiLocation() )
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("users.findall.size()", is(3))
                .body("users.find { it.userId == 'john1' }.username", Matchers.equalTo("John Smith"))
                .body("users.findAll{it.userId == \"john1\"}.username", Matchers.hasItem("John Smith"));
                //.body("users.findAll{it.userId == \"john1\"}.username", Matchers.empty());



    }
    @Test
    /*
     *
     */
    public void testCreateUser_user_already_exist() throws Exception {
        User user = createUser("john1", "john1");
        CreateUserRequest request = new CreateUserRequest();
        request.setUser(user);

        with()
                .body(request)
                .when()
                .post(resource.getUserApiLocation())
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("code", equalTo(Errors.USER_ALREADY_EXISTS.code))
                .body("message", equalTo(Errors.USER_ALREADY_EXISTS.message));


    }
//    @Test
//    /*
//     *
//     */
//    public void testCreateUser_happy_path() throws Exception {
//        User user = createUser("create-user-1", "create-user-1");
//        CreateUserRequest request = new CreateUserRequest();
//        request.setUser(user);
//
//        with()
//                .contentType("application/json")
//                .header("Accept", "application/json")
//                .body(request)
//                .when()
//                .post(resource.getUserApiLocation())
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body("userId", equalTo(user.getUserId()))
//                .body("email", equalTo(user.getEmail()))
//                .body("username", equalTo(user.getUsername()))
//                .body("firstName", equalTo(user.getFirstName()))
//                .body("lastName", equalTo(user.getLastName()))
//                .body("locale", equalTo(user.getLocale()))
//                .body("phones", Matchers.hasSize(2))
//                .body("phones[0].phoneNo", equalTo(user.getPhones().get(0).getPhoneNo()));
//
//
//    }

}
