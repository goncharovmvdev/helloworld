package goncharovmvdev.tests.rest;

import goncharovmvdev.IT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author gonch
 * @since 5/28/2023, 7:58 PM
 */
class HelloWorldRESTControllerTest extends IT {
    private final Map<String, ResponseEntity<String>> testcasesExpects;

    @Autowired
    HelloWorldRESTControllerTest(@LocalServerPort Integer port) {
        this.testcasesExpects = Map.of(
                "http://localhost:" + port + "/badRq", ResponseEntity.badRequest().build(),
                "http://localhost:" + port + "/api/greeting/v0", ResponseEntity.ok("Hello, World!"),
                "http://localhost:" + port + "/api/greeting/v0?name=Polina", ResponseEntity.ok("Hello, Polina!")
        );
    }

    @Test
    void test_helloWorldGET() {
        RestTemplate restTemplate = new RestTemplate();

        for (var testcaseExpect : testcasesExpects.entrySet()) {
            String testcase = testcaseExpect.getKey();
            ResponseEntity<String> expect = testcaseExpect.getValue();

            ResponseEntity<String> rs;
            try {
                rs = restTemplate.getForEntity(testcase, String.class);
            } catch (Exception e) {
                Assertions.assertEquals(HttpStatus.BAD_REQUEST, expect.getStatusCode());
                Assertions.assertFalse(expect.hasBody());
                continue;
            }

            Assertions.assertEquals(expect.getStatusCode(), rs.getStatusCode());
            Assertions.assertEquals(expect.getBody(), rs.getBody());
        }
    }

    @Test
    void fail() {
        Assertions.fail();
    }
}
