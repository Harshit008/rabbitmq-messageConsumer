package com.zensar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import com.zensar.config.SwaggerConfiguration;
@EnableAutoConfiguration
@Import(SwaggerConfiguration.class)
@EnableEurekaClient
@SpringBootApplication
public class MacysOrderMessageConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacysOrderMessageConsumerServiceApplication.class, args);
	}

}
