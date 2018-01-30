package com.quizemon.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/questions");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("[{\"question\":\"What color is the sun?\",\"alternatrives\":[{\"alternative\":\"yellow\",\"correct\":true},{\"alternative\":\"blue\",\"correct\":false},{\"alternative\":\"red\",\"correct\":false},{\"alternative\":\"black\",\"correct\":false}]},{\"question\":\"What is the currency in Sweden?\",\"alternatrives\":[{\"alternative\":\"Swedish Crown\",\"correct\":true},{\"alternative\":\"Dollar\",\"correct\":false},{\"alternative\":\"Euro\",\"correct\":false},{\"alternative\":\"Norweigan Crown\",\"correct\":false}]}]"));
    }
}