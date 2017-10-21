package com.zautomate.commons.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.DumpEndpoint;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.management.ThreadInfo;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class ActuatorResource {

	  private HealthEndpoint health;
	  private MetricsEndpoint metrics;
	  private DumpEndpoint dump;

	  @Autowired
      public ActuatorResource(HealthEndpoint health, MetricsEndpoint metrics, DumpEndpoint dump) {
        this.health = health;
        this.metrics = metrics;
        this.dump = dump;
      }

      @GET
	  @Path("/health")
	  public Object getHealth() {
	    return health.invoke();
	  }
	  
	  @GET
	  @Path("/metrics")
	  public Object getMetrics() {
	    return this.metrics.invoke();
	  }
	  
	  @GET
	  @Path("/metrics/{name:.*}")
	  public Object getMetric(@PathParam("name") final String name) {
	    final Object value = this.metrics.invoke().get(name);
	    if (value == null)
	      throw new NotFoundException("No such metric: " + name);
	    
	    return value;
	  }
	  
	  @GET
	  @Path("/dump")
	  @Produces(MediaType.TEXT_PLAIN)
	  public Object getThreadDump() {
		  return (StreamingOutput) output -> {
              final Writer writer = new BufferedWriter(new OutputStreamWriter(output));
              for (final ThreadInfo thread : dump.invoke())
                  writer.write(thread.toString());
              writer.flush();
          };
	  }
	  
}