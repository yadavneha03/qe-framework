package com.nehayadav.qe.tests.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UserApiTest extends BaseApiTest {

 @Test(description = "POST /users creates a user and returns 201 with a valid schema")
 public void createUserSucceeds() {
 Map<String, String> body = new HashMap<>();
 body.put("name", "Neha Yadav");
 body.put("job", "SDET");

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/users")
 .then()
 .statusCode(201)
 .body(matchesJsonSchemaInClasspath("schemas/create-user-schema.json"))
 .body("name", org.hamcrest.Matchers.equalTo("Neha Yadav"))
 .body("job", org.hamcrest.Matchers.equalTo("SDET"));
 }

 @Test(description = "GET /users/{id} for an existing user returns 200 with a valid schema")
 public void getExistingUserSucceeds() {
 given()
 .when()
 .get("/users/2")
 .then()
 .statusCode(200)
 .body(matchesJsonSchemaInClasspath("schemas/single-user-schema.json"))
 .body("data.id", org.hamcrest.Matchers.equalTo(2));
 }

 @Test(description = "GET /users/{id} for a non-existent user returns 404")
 public void getNonExistentUserReturns404() {
 given()
 .when()
 .get("/users/9999")
 .then()
 .statusCode(404);
 }

 @Test(description = "PUT /users/{id} updates a user and returns 200 with updated fields")
 public void updateUserSucceeds() {
 Map<String, String> body = new HashMap<>();
 body.put("name", "Neha Yadav");
 body.put("job", "Senior SDET");

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .put("/users/2")
 .then()
 .statusCode(200)
 .body("job", org.hamcrest.Matchers.equalTo("Senior SDET"));
 }

 @Test(description = "DELETE /users/{id} removes a user and returns 204")
 public void deleteUserSucceeds() {
 given()
 .when()
 .delete("/users/2")
 .then()
 .statusCode(204);
 }

 @Test(description = "POST /register without a password returns 400 with an error message")
 public void registerWithoutPasswordFails() {
 Map<String, String> body = new HashMap<>();
 body.put("email", "sydney@fife");
 // intentionally omitting "password" to trigger the negative case

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/register")
 .then()
 .statusCode(400)
 .body("error", org.hamcrest.Matchers.notNullValue());
 }
}

