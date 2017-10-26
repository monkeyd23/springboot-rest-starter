package com.zautomate.modules.login;

import com.zautomate.AppTest;
import com.zautomate.helpers.YAMLHelper;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest extends AppTest {

    private YAMLHelper yamlHelper;

    @LocalServerPort
    int port;

    @Before
    public void setUp() throws IOException {
        yamlHelper = new YAMLHelper("test-data.yml");
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost:" + port + "/zportal";
    }
}
