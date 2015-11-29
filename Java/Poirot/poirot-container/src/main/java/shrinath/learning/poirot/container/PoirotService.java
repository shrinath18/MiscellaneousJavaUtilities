package shrinath.learning.poirot.container;

import com.google.inject.Guice;
import com.google.inject.Injector;

import shrinath.learning.poirot.config.PoirotConfiguration;
import shrinath.learning.poirot.controller.ProductViewController;
import shrinath.learning.poirot.module.PoirotModule;
import shrinath.learning.poirot.service.ProductViewResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PoirotService extends Application<PoirotConfiguration>
{
    
	public static void main( String[] args ) throws Exception
    {
        new PoirotService().run(args);
    }

	@Override
	public void run(PoirotConfiguration configuration, Environment environment)
			throws Exception {
		Injector injector = Guice.createInjector(new PoirotModule());
		environment.jersey().register(injector.getInstance(ProductViewResource.class));
		ProductViewController.INSTANCE.setup();
	}
	
	@Override 
	public void initialize(Bootstrap<PoirotConfiguration> bootstrap){
		
	}
}
