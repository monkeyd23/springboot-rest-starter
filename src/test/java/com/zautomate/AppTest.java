package com.zautomate;

import com.zautomate.helpers.YAMLHelper;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

    private YAMLHelper yamlHelper;

    @LocalServerPort
    int port;

    @Before
    public void setUp() throws IOException {
        yamlHelper = new YAMLHelper("test-data.yml");
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost:" + port + "/zportal";
    }

    @Test
    public void testLogin() {

        Map<String, String> params = new HashMap<>();
        String concatenatedKeySecret = yamlHelper.getValue("client.id") + ":" + yamlHelper.getValue("client.secret");
        String clientToken = new String(Base64.getEncoder().encode(concatenatedKeySecret.getBytes()));

        params.put("username", yamlHelper.getValue("login.username"));
        params.put("password", yamlHelper.getValue("login.password"));
        params.put("grant_type", yamlHelper.getValue("login.grant_type"));


        given()
                .header("Authorization", "Basic " + clientToken)
                .params(params)
                .when().post("/oauth/token").then().statusCode(200);
    }

}
