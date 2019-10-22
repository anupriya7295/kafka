package com.skava.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("application.yaml")
public class Consumer {
	private final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@Autowired
    Environment env;
	
	@KafkaListener(topics = {"${topic.category}","${topic.product}"})
	public void categoryConsumerAndProduct1(ConsumerRecord<?, ?> consumerRecord, Acknowledgment ack){
		try {
//			Map<String,String> data = new HashMap<String, String>();
//			data.put("data", message);
//			data.put("engineName", env.getProperty("configuration.engineName"));
//			data.put("collectionName", env.getProperty("configuration.collectionName"));
//			data.put("url", env.getProperty("configuration.url"));
//			System.out.println("Consumer topic"+message.topic());
//			System.out.println("Consumer topic offset "+message.offset());
			
			logger.info(String.format("$$ -> Consumed Message in other consumer -> %s",consumerRecord.value().toString()));
			Thread.sleep(10 * 1000);
			System.out.println("Acknowledgment provided");
			ack.acknowledge();
		} catch(Exception e) {
			logger.error(e.toString());
		}
		
	}
	
}
