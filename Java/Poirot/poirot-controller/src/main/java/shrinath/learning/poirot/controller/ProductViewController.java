package shrinath.learning.poirot.controller;

import shrinath.learning.poirot.client.models.request.ProductViewEventRequest;
import shrinath.learning.poirot.client.models.response.ProductViewEventResponse;

public enum ProductViewController 
{
    INSTANCE;
    public ProductViewEventResponse publishProductViewEvent(ProductViewEventRequest request){
    	System.out.println("Publish event to kafka!!"+request.toString());
    	ProductViewEventResponse response = new ProductViewEventResponse();
    	response.status = "SUCCESS";
    	return response;
    }
}
