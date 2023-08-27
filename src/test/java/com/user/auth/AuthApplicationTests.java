package com.user.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class AuthApplicationTests {
	@Container
	static RabbitMQContainer container = new RabbitMQContainer(
			DockerImageName.parse("rabbitmq").withTag("3.9.11-management-alpine")
	);

	@DynamicPropertySource
	static void configure(DynamicPropertyRegistry registry){
		registry.add("spring.rabbitmq.host",container::getHost);
		registry.add("spring.rabbitmq.username",container::getAdminUsername);
		registry.add("spring.rabbitmq.port",container::getAmqpPort);
	}

	@Test
	void contextLoads() {
	}

}
