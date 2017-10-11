package com.example.springbootdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import java.net.URL;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SpringbootdemoApplicationTests {

	@LocalServerPort
	private int port;

	private URL base;

	private String usersURL = "users/";

	private int baseId = 1;

	User user = new User();

	@Autowired
	private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}

	@Test
	public void sayHello() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString(),
				String.class);
		assertThat(response.getBody(), equalTo("Hello Users"));
	}

	@Test
	public void getUser() throws Exception {
		ResponseEntity<User> response = template.getForEntity(base.toString() + usersURL + baseId,
				User.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		user = response.getBody();
		assertThat(baseId, equalTo(user.getUserId()));
	}

	@Test
	public void postUser() throws Exception {
		User newUser = new User();
		newUser.setFirstName("Alice");
		ResponseEntity<?> response = template.postForEntity(base.toString() + usersURL, newUser, User.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
	}


}
