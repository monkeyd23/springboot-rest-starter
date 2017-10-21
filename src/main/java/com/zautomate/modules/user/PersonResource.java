package com.zautomate.modules.user;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@SuppressWarnings("WeakerAccess")
public class PersonResource {

	private PersonService personService;

	@Inject
	PersonResource(PersonService personService) {
		this.personService = personService;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getPersons() {
		return personService.getAllUsers();
	}
}
