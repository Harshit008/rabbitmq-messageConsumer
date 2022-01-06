package com.zensar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.zensar.config.SwaggerConfiguration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableAutoConfiguration
@Import(SwaggerConfiguration.class)
@EnableEurekaClient
@SpringBootApplication
public class MacysOrderMessageConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MacysOrderMessageConsumerServiceApplication.class, args);
	}

}
