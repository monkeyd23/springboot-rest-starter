package com.zautomate.zportal.configs;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.zautomate.zportal.commons.exceptions.GenericExceptionMapper;

@Configuration
@ApplicationPath("")
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {}
	
	@PostConstruct
	public void setUp() {
		packages("com.zautomate.zportal.endpoints");
		register(GenericExceptionMapper.class);
	}
}