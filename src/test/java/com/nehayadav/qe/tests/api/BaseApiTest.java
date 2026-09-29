package com.nehayadav.qe.tests.api;

import com.nehayadav.qe.config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

 @BeforeClass
 public void setBaseUri() {
 RestAssured.baseURI = ConfigReader.get("api.baseUrl");
 }
}

