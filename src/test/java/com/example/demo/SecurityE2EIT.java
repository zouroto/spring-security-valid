package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpStatus.*;

@SpringBootTest(classes = {DemoApplication.class},
        webEnvironment = RANDOM_PORT)
public class SecurityE2EIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void withSecurityAndUnauthorizedUserAndValidation() {
        Data data = new Data();
        HttpEntity<Data> httpEntity = new HttpEntity<>(data, createHeaders());
        String url = "http://localhost:" + port + "/rest/api/security/with-valid";
        ResponseEntity<String> response = this.restTemplate
                .withBasicAuth("U809584", "password")
                .exchange(url, HttpMethod.POST, httpEntity, String.class);
        assertThat(response.getStatusCode()).isEqualTo(FORBIDDEN);
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(ACCEPT, "application/json;application/problem+json");
        return headers;
    }

}
