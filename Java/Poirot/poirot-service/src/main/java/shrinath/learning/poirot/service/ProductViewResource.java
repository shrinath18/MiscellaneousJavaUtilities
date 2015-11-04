package shrinath.learning.poirot.service;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import shrinath.learning.poirot.client.models.request.ProductViewEventRequest;
import shrinath.learning.poirot.client.models.response.ProductViewEventResponse;
import shrinath.learning.poirot.controller.ProductViewController;

@Path("/poirot/product")
@Produces("application/json")
@Singleton
public class ProductViewResource {
	
	@POST
	@Path("/productViewEvent")
	public ProductViewEventResponse publishProductViewEvent(ProductViewEventRequest request){
		ProductViewEventResponse response = ProductViewController.INSTANCE.publishProductViewEvent(request);
		return response;
	}
	
}
