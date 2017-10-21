package com.zautomate.configs;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.zautomate.commons.exceptions.GenericExceptionMapper;
import com.zautomate.commons.server.ActuatorResource;
import com.zautomate.modules.user.PersonResource;

@Configuration
@EnableResourceServer
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {}
	
	@PostConstruct
	public void setUp() {	
		// Register all end points
		registerEndpoints();
		// Register all common components
		registerCommons();
	}
	
	private void registerEndpoints() {		
		register(PersonResource.class);
	}
	
	private void registerCommons() {
		// Register Logging Feature
		//register(new LoggingFeature(Logger.getLogger(getClass().getName()), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, Integer.MAX_VALUE));
		
		// Register Actuator Endpoints
		register(ActuatorResource.class);
		
		// Register generic exception handler
		register(GenericExceptionMapper.class);
	}
}