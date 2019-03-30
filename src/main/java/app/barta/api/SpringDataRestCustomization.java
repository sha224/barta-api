package app.barta.api;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component
public class SpringDataRestCustomization implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		/*
		config.setDefaultMediaType(MediaType.APPLICATION_JSON);
		config.useHalAsDefaultJsonMediaType(false);
		config.setExposeRepositoryMethodsByDefault(false);
		ExposureConfiguration exposureConfig = config.getExposureConfiguration();
		exposureConfig.forDomainType(User.class).withItemExposure((metadata, httpMethods) -> httpMethods.enable(HttpMethod.GET));
		exposureConfig.forDomainType(User.class).withCollectionExposure((metadata, httpMethods) -> httpMethods.enable(HttpMethod.POST));
		*/
		
	}
}
