package shrinath.learning.poirot.controller;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import shrinath.learning.poirot.client.models.request.ProductViewEventRequest;
import shrinath.learning.poirot.client.models.response.ProductViewEventResponse;

public enum ProductViewController 
{
    INSTANCE;
    public ProductViewEventResponse publishProductViewEvent(ProductViewEventRequest request){
    	System.out.println("Publish event to kafka!!"+request.toString());
    	Properties properties = new Properties();
    	properties.put("metadata.broker.list", "localhost:9092");
    	properties.put("serializer.class", "kafka.serializer.StringEncoder");
    	properties.put("request.required.acks", "1");
    	
    	Producer<Integer, String> producer = new Producer<Integer, String>(new ProducerConfig(properties));
    	KeyedMessage<Integer, String> message = new KeyedMessage<Integer, String>("test_topic", "Hello Kafka!!!");
    	producer.send(message);
    	producer.close();
    	ProductViewEventResponse response = new ProductViewEventResponse();
    	response.status = "SUCCESS";
    	return response;
    }
}
