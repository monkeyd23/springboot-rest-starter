package com.zautomate.zportal.modules.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;


@Component
@Path("/test")
public class Test {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response welcome() {
		return Response.ok("{\"message\": \"Welcome to jersey starter\"}", MediaType.APPLICATION_JSON).build();
	}
	
}
