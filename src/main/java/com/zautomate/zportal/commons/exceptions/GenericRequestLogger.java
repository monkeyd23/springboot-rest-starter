package com.zautomate.zportal.commons.exceptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@Provider
public class GenericRequestLogger implements ContainerRequestFilter, ContainerResponseFilter {
	
	@Context
    private ResourceInfo resourceInfo;
    private static final Logger logger = LoggerFactory.getLogger(GenericRequestLogger.class);
    
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		// Note down the start request time...we will use to calculate the total execution time
		MDC.put("start-time", String.valueOf(System.currentTimeMillis()));
		
		// Log request path
		logger.debug("Entering in Resource : /{} ", requestContext.getUriInfo().getPath());
		// Log method name
		logger.debug("Method Name : {} ", resourceInfo.getResourceMethod().getName());
		// Log class name
		logger.debug("Class : {} ", resourceInfo.getResourceClass().getCanonicalName());
		// Log parameters
		logQueryParameters(requestContext);
		// Log method annotations
		logMethodAnnotations();
		// Log request headers
		logRequestHeaders(requestContext);
		// Log entity stream.
        String entity = readEntityStream(requestContext);
        if(StringUtils.isNotBlank(entity))
            logger.debug("Entity Stream : {}",entity);
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
        if(StringUtils.isBlank(MDC.get("start-time"))) return;
        logger.debug("Total request execution time : {} milliseconds", System.currentTimeMillis() - Long.parseLong(MDC.get("start-time")));
        // clear the context on exit
        MDC.clear();
	}
	
	private void logQueryParameters(ContainerRequestContext requestContext) {
		logger.debug("---- Start Query Parameters of resource ----");
		
		// Get parameter map and log it
		Map<String, List<String>> pathParams = requestContext.getUriInfo().getPathParameters();		
		pathParams.forEach( (key, value) -> logger.debug("Query Parameter Name: {}, Value :{}", key, value));
		
		logger.debug("---- End Query Parameters of resource ----");
	}
	
	private void logRequestHeaders(ContainerRequestContext requestContext) {
        logger.debug("---- Start Header Section of request ----");
        logger.debug("Method Type : {}", requestContext.getMethod());
   
        // Get header map and log it
        Map<String, List<String>> headers = requestContext.getHeaders();
        headers.forEach( (key, value) -> logger.debug("Header Name: {}, Header Value :{} ", key, value));
        
        logger.debug("---- End Header Section of request ----");
    }
	
	private void logMethodAnnotations() {
		logger.debug("----Start Annotations of resource ----");
		
		// Get annotation array and log it
		Annotation[] annotations = resourceInfo.getResourceMethod().getDeclaredAnnotations();
		for (Annotation annotation : annotations) logger.debug(annotation.toString());
		
		logger.debug("----End Annotations of resource----");
    }
	
	private String readEntityStream(ContainerRequestContext requestContext) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        
        try {
            ReaderWriter.writeTo(requestContext.getEntityStream(), outStream);
            byte[] requestEntity = outStream.toByteArray();
            return requestEntity.length == 0 ? "" : new String(requestEntity);
            
        } catch (IOException ex) {
            logger.debug("----Exception occurred while reading entity stream :{}", ex.getMessage());
            return "";
        }
    }

}
