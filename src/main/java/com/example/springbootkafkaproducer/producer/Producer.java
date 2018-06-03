package com.example.springbootkafkaproducer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootkafkaproducer.entity.User;

@RestController
@RequestMapping("/producer")
@CrossOrigin
public class Producer {
	
	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;
	
	private static final String TOPIC = "kafka-topic";
	
	
	@GetMapping("/message/{message}")
	public String getResponse(@PathVariable("message") String message) {
		User user = getSampleUser(message);
		kafkaTemplate.send(TOPIC, user);
		return "Successfully Published";
	}


	private User getSampleUser(String message) {
		User user = new User();
		user.setEmailId("new@gmail.com");
		user.setFirstName("User");
		user.setMessage(message);
		return user;
	}

}
