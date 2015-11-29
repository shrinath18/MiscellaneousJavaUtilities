package shrinath.learning.poirot.controller;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import shrinath.learning.poirot.client.models.request.ProductViewEventRequest;
import shrinath.learning.poirot.client.models.response.ProductViewEventResponse;

public enum ProductViewController 
{
    INSTANCE;
    KafkaProducer<String, String> producer;
    String topic;
    
    public void setup(){
    	Properties properties = new Properties();
    	properties.put("metadata.broker.list", "localhost:9092");
    	properties.put("bootstrap.servers", "localhost:9092");
    	properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    	properties.put("request.required.acks", "1");
    	producer = new KafkaProducer<String, String>(properties);
    	topic = "test_topic_2";
    }
    public ProductViewEventResponse publishProductViewEvent(ProductViewEventRequest request){
    	System.out.println("Publish event to kafka!!"+request.toString()); 
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, request.productId);
    	producer.send(producerRecord);
    	ProductViewEventResponse response = new ProductViewEventResponse();
    	response.status = "SUCCESS";
    	return response;
    }
}
