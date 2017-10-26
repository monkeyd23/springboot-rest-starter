package com.zautomate.zportal;

import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

    @LocalServerPort
    int port;

    String baseURL = "http://localhost:" + port + "/zportal";

    String username = "nithin.t@gslab.com";
    String password = "Temp@123";
    String grantType = "password";

    String clientID = "zautomate_services";
    String secret = "$==zautomate_secret==$";


}
