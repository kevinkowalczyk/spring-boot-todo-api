package de.htwsaar.stl.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class DemoApplicationTests {

	@Test
	void migrationSuccessTest() {
		// Starts automatically, since Spring runs the Flyway scripts on startup
	}

}