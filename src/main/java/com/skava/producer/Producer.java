package com.skava.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	public void sendMessage(String message, String topicName){
		logger.info(String.format("$$ -> Producing message --> %s", message));
		this.kafkaTemplate.send(topicName, message);
	}
}
