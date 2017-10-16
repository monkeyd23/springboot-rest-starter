package com.zautomate.commons.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "error")
public class Error {
	
	// Properties
	private int statusCode;
    private String statusDescription;
    private String errorMessage;
    
    // Default Constructor
    public Error() {}
    
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
    
    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatusDescription() {
        return statusDescription;
    }
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
