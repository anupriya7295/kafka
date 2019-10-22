package com.skava.producer;

import java.util.UUID;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;


@Service
public class Producer {
	
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	public void sendMessage(String message, String topicName){
		logger.info(String.format("$$ -> Producing message --> %s", message));
		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topicName, message);
		
		try {
			SendResult<String, String> metaData = future.get();
			
			System.out.println("Topic Name --- "+metaData.getRecordMetadata().topic());
			System.out.println("Producer Topic Offset"+metaData.getRecordMetadata().offset());
		} catch(Exception e) {
			
		}
	}
}
