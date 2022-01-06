package com.zensar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageConsumerController {
	@GetMapping("/testApi")
	public String testApi() {
		return "Welcome to MacysOrder-message-producer app!";
	}
}
