package com.zautomate.modules.user;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/users")
public class PersonResource {
	
	@Autowired
	PersonService personService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getPersons() {
		return personService.getAllUsers();
	}
}
