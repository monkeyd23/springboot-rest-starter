package com.zautomate.commons.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	
	private static final Logger logger = LoggerFactory.getLogger(GenericExceptionMapper.class);
	
    @Override
    public Response toResponse(Throwable exception) {
    	
    		// Log Exception
    		logger.error("Exception occurred in application : {}", exception.getMessage());
    	
    		// Get error from throwable
        Error error = new Error(exception);
        
        // return response built with the error object
        return Response.status(error.getStatusCode()).entity(error).type(MediaType.APPLICATION_JSON).build();
    }
}
