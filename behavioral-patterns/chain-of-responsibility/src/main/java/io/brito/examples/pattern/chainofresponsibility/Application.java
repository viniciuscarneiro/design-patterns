package io.brito.examples.pattern.chainofresponsibility;

import io.brito.examples.pattern.chainofresponsibility.resource.model.AuthenticationRequest;
import io.brito.examples.pattern.chainofresponsibility.resource.AuthenticationResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		AuthenticationResource authenticationResource = new AuthenticationResource();
		authenticationResource.doAuthentication(
				AuthenticationRequest
						.builder()
						.password("userPassword")
						.username("user")
						.build());
	}
}
