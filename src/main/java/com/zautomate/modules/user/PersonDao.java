package com.zautomate.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person, String> {
	
	/*
	 * Find list of person by email
	 * No need to add implementation. Spring JPA will add it based on username
	 * @param email of the user.
	 * @return List of Person object if its exists in the database.
	 */
	Person findFirstByEmail(String email);
}
