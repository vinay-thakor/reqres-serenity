package com.req.res.testsuite;

import com.req.res.model.UserPojo;
import com.req.res.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;


@RunWith(SerenityRunner.class)

public class StudentCURDTest extends TestBase {

    static String name = "morpheus";
    static String job = "leader";

    @Title("This test will create a new user")
    @Test
    public void test001() {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setJob(job);

        SerenityRest.rest()
                .given()
                .header("Content-type", "application/json")

                .when()
                .body(userPojo)
                .post("/users")

                .then()
                .log()
                .body()
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"))
                .statusCode(201);
    }


    @Title("This test will Verify an existing user of the application")
    @Test
    public void test002() {

        SerenityRest.rest()
                .given()
                .queryParam("page", "1")

                .when()
                .get("/users")

                .then()
                .log()
                .body()
                .body("data[1].first_name", equalTo("Janet"))
                .statusCode(200);
    }

    @Title("This test will check ")
    @Test

    public void test003() {

        UserPojo userPojo = new UserPojo();

        userPojo.setName(name);
        userPojo.setJob("zion resident");

        SerenityRest.rest()
                .given()
                .header("Content-Type", "application/json")

                .when()
                .body(userPojo)
                .put("/2")

                .then()
                .log()
                .body()
                .log()
                .status()
                .body("name", equalTo(name))
                .body("job", equalTo("zion resident"))
                .statusCode(200);
    }

    @Title("This test will Delete a user")
    @Test
    public void test004() {

        SerenityRest.rest()
                .given()

                .when()
                .delete("/users/2")

                .then()
                .log()
                .status()
                .statusCode(204);


    }

}