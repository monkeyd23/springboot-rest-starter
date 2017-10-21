package com.zautomate.commons.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@JsonRootName(value = "error")
@SuppressWarnings("WeakerAccess")
public class Error {
	
	// Properties
	private int statusCode;
    private String statusDescription;
    private String errorMessage;
    
    // Constructor with Throwable
    public Error(Throwable exception) {
    		
    		Response.StatusType type;
    		
    		// Use ternary operator to get status type
    		if(exception instanceof WebApplicationException)
    			type = ((WebApplicationException) exception).getResponse().getStatusInfo();
    		else
    			type = Response.Status.INTERNAL_SERVER_ERROR;
    		
    		// Populate properties
    		this.statusCode = type.getStatusCode();
    		this.statusDescription = type.getReasonPhrase();
    		this.errorMessage = exception.getLocalizedMessage();
    }
}
