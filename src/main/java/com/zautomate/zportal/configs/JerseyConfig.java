package com.zautomate.zportal.configs;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.zautomate.zportal.commons.exceptions.GenericExceptionMapper;
import com.zautomate.zportal.commons.exceptions.GenericRequestLogger;
import com.zautomate.zportal.modules.test.Test;

@Configuration
@ApplicationPath("")
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
		register(Test.class);
	}
	
	private void registerCommons() {
		register(GenericRequestLogger.class);
		register(GenericExceptionMapper.class);
	}
}