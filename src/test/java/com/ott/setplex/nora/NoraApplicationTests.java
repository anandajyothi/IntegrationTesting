package com.ott.setplex.nora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.ott.setplex.nora.controller.UserController;
import com.ott.setplex.nora.model.User;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.junit.Before;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.UUID;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.web.client.HttpServerErrorException;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
class NoraApplicationTests {

	RestTemplate restTemplate;
	ResponseEntity response;

	WireMockServer wireMockServer;

	@Autowired
	UserController userController;

	@BeforeEach
	public void setup() {
		restTemplate = new RestTemplate();
		response = null;

		wireMockServer = new WireMockServer(8090);
		wireMockServer.start();
	}

	@AfterEach
	public void teardown() {
		wireMockServer.stop();
	}

	@Test
	void contextLoads() {

	}

	@Test
	@DisplayName("Should return user listing")
	public void shouldReturnUserListing() throws Exception {
		wireMockServer
				.givenThat(get(urlEqualTo("/users")).willReturn(WireMock.aResponse().withStatus(200).withBody("OK")));

		RestTemplate restTemplate = new RestTemplate();
		Integer serverPort = this.wireMockServer.port();
		String serverUrl = "http://localhost:" + serverPort + "/users";

		ResponseEntity<String> response = restTemplate.getForEntity(serverUrl, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	@DisplayName("Should return packages")
	void shouldReturnPackages() {

		wireMockServer.givenThat(
				get(urlEqualTo("/packages")).willReturn(WireMock.aResponse().withStatus(200).withBody("OK")));

		RestTemplate restTemplate = new RestTemplate();

		Integer serverPort = this.wireMockServer.port();
		String serverUrl = "http://localhost:" + serverPort + "/packages";

		ResponseEntity<String> response = restTemplate.getForEntity(serverUrl, String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	// Please refer below test cases for different conditions

	/*
	 * @Test
	 * 
	 * @DisplayName("Should return the HTTP status code OK") void
	 * shouldReturnHttpStatusCodeOk() {
	 * givenThat(get(urlEqualTo("/users")).willReturn(WireMock.aResponse().
	 * withStatus(200)));
	 * 
	 * String serverUrl = buildApiMethodUrl(); ResponseEntity<String> response =
	 * restTemplate.getForEntity(serverUrl, String.class);
	 * assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Should return the HTTP status code 500 and the correct message"
	 * ) void shouldReturnHttpStatusCodeOkIAmSorry() {
	 * givenThat(get(urlEqualTo("/users")).willReturn(WireMock.aResponse().
	 * withStatus(500)));
	 * 
	 * String serverUrl = buildApiMethodUrl(); Throwable thrown = catchThrowable(()
	 * -> restTemplate.getForEntity(serverUrl, String.class));
	 * assertThat(thrown).isExactlyInstanceOf(HttpServerErrorException.class).
	 * hasMessage("500 I am sorry"); }
	 */

	/*
	 * @Test
	 * 
	 * @DisplayName("Should return OK response for JSON") void
	 * shouldReturnOkResponseForJson() {
	 * givenThat(get(urlEqualTo("/api/message")).willReturn(
	 * okJson("{ \"message\": \"Hello World!\" }")));
	 * 
	 * String serverUrl = buildApiMethodUrl(); ResponseEntity<String> response =
	 * restTemplate.getForEntity(serverUrl, String.class);
	 * assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	 * assertThat(response.getHeaders().getContentType()).isEqualTo(MediaType.
	 * APPLICATION_JSON);
	 * assertThat(response.getBody()).isEqualTo("{ \"message\": \"Hello World!\" }"
	 * ); }
	 */

	private String buildApiMethodUrl() {
		return String.format("http://localhost:%d/users", this.wireMockServer.port());
	}

}