package com.zautomate.zportal.module.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person, String> {
	
	/*
	 * Find a person by username
	 * No need to add implementation. Spring JPA will add it based on username
	 * @param email of the user.
	 * @return Person object if its exists in the database.
	 */
	Person findOneByEmail(String email);
}
