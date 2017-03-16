package com.ibps.openapi;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibps.api.usersvcs.model.User;
import com.ibps.api.usersvcs.model.UserPhone;
import com.ibps.openapi.config.TestConfig;
import com.ibps.openapi.util.ResourceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ws.test.client.MockWebServiceServer;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

//import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

@SpringBootTest(classes = {TestConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BaseTest {
    private static final Log log = LogFactory.getLog(BaseTest.class);
    //@ClassRule public static WireMockClassRule wireMock = new WireMockClassRule(9990);
    @Autowired protected ObjectMapper objectMapper;
    @Autowired protected ResourceLocator resource;
  //  protected MockWebServiceServer mockServer;
    // @Autowired private Map<String, JpaRepository<?, Serializable>> repositories;
   // @Autowired private DataSource ds;
    @LocalServerPort
    private String serverPort;


    @PostConstruct
    public void setUpValues() {
        resource.setPort(serverPort);
    }

    @Before
    public void beforeTest() {
        resetMockWebServiceServer();
    }

    protected void resetMockWebServiceServer() {
        // mockServer = MockWebServiceServer.createServer(certManagementService);
        // wireMock.resetMappings();
    }

    public User createUser(String userId, String username) {
        User user = new User();
        user.setUserId(userId);
      //  user.setUsername(username);
      //  user.setEmail(userId + "@ibps.com");
        user.setFirstName(username);
        user.setLastName(username);
        user.setLocale("en_US");
        user.setPhones(new ArrayList<UserPhone>());

        UserPhone p1 = new UserPhone();
        p1.setPhoneNo("111-222-3333");
        p1.setPhoneType("H");
        user.getPhones().add(p1);

        UserPhone p2 = new UserPhone();
        p2.setPhoneNo("555-222-3333");
        p2.setPhoneType("M");
        user.getPhones().add(p2);
        return user;
    }
    public String getJsonFromObject(Object obj) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        objectMapper.writeValue(os, obj);
        return new String(os.toByteArray(), "UTF-8");
    }
}