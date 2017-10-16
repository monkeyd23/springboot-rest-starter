package com.zautomate.modules.user;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/users")
public class PersonResource {
	
	@Autowired
	PersonService personService;
	
	@GET
	public List<Person> getPersons() {
		return personService.getAllUsers();
	}
}
