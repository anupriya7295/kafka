package com.skava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skava.producer.Producer;

@RestController
@RequestMapping(value = "/kafka")
public class BatchProcessing {
	private final Producer producer;

	@Autowired
	public BatchProcessing(Producer producer) {
		this.producer = producer;
	}
	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestBody Object obj, @RequestParam("type") String type) throws JsonProcessingException{
		this.producer.sendMessage(new ObjectMapper().writeValueAsString(obj), type);
	}
}
