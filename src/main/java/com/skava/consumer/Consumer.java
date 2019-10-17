package com.skava.consumer;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@PropertySource("application.yaml")
public class Consumer {
	private final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@Autowired
    Environment env;

//	@KafkaListener(topics = "${topic.product}")
//	public void productConsumer(String message){
//		logger.info(String.format("$$ -> Consumed Message in product consumer -> %s",message));
//	}
//	
//	@KafkaListener(topics = "${topic.category}")
//	public void categoryConsumer(String message){
//		logger.info(String.format("$$ -> Consumed Message in category consumer -> %s",message));
//	}
	
	@KafkaListener(topics = {"${topic.category}","${topic.product}"})
	public void categoryConsumerAndProduct(String message){
		try {
			Map<String,String> data = new HashMap<String, String>();
			data.put("data", message);
			data.put("engineName", env.getProperty("configuration.engineName"));
			data.put("collectionName", env.getProperty("configuration.collectionName"));
			data.put("url", env.getProperty("configuration.url"));
			logger.info(String.format("$$ -> Consumed Message in category and product consumer partition 0 -> %s",data.toString()));
		} catch(Exception e) {
			logger.error(e.toString());
		}
		
	}
	
}
