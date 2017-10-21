package com.zautomate.modules.login;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost:" + port + "/zportal";
    }

    @Test
    public void testLogin() {

        Map<String, String> params = new HashMap<>();

        params.put("username", "nithin.t@gslab.com");
        params.put("password", "Temp@123");
        params.put("grant_type", "password");


        given()
            .header("Authorization", "Basic emF1dG9tYXRlX3NlcnZpY2VzOiQ9PXphdXRvbWF0ZV9zZWNyZXQ9PSQ=")
            .params(params)
            .when().post("/oauth/token").then().statusCode(200);
    }
}
