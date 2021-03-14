package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient(timeout = "120000")
public class MyTaskDemoTests {

	@Autowired
	private WebTestClient webClient;

    @Test
    public void getPing() throws Exception {
		this.webClient.get().uri(uriBuilder -> uriBuilder.path("/health/ping").build()).exchange().expectStatus().isOk();
    }

    @Test
    public void getSurveyOne() throws Exception {
		this.webClient.get().uri(uriBuilder -> uriBuilder.path("/survey/list/TS-001").build()).exchange().expectStatus().isOk();
    }

}
