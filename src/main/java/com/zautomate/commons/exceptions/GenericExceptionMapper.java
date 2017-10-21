package com.zautomate.commons.exceptions;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	
    @Override
    public Response toResponse(Throwable exception) {
    	
        // Log Exception
        log.error("Exception occurred in application : {}", exception.getMessage());
    	
    		// Get error from throwable
        Error error = new Error(exception);
        
        // return response built with the error object
        return Response.status(error.getStatusCode()).entity(error).type(MediaType.APPLICATION_JSON).build();
    }
}
