package com.skava;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;

@Configuration
@EnableKafka
public class KafkaConfiguration {
	 @Bean
	 KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		 ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
		 factory.setConsumerFactory(consumerFactory());
		 factory.setConcurrency(10);
		 factory.getContainerProperties().setPollTimeout(3000);
		 /*
		  AckMode.MANUAL_IMMEDIATE will commit the offsets to kafka immediately, without waiting for any
	      other kind of events to occur.
	        
	      But AckMode.MANUAL will work similar to AckMode.BATCH, which means after the acknowledge() method
	      is called on a message, the system will wait till all the messages received by the poll() method have
	      been acknowledged. This could take a long time, depending on your setup.
	     */
		 factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);
		 factory.getContainerProperties().setSyncCommits(true);
		 return factory;
		 }
	
	 @Bean
	 public ConsumerFactory<String, String> consumerFactory() {
		 return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	 }
	 
	 @Bean
	 public Map<String, Object> consumerConfigs() {
		 Map<String, Object> props = new HashMap<>();
		 props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		 props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
		 props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		 props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		 props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		 return props;
	 }
}
