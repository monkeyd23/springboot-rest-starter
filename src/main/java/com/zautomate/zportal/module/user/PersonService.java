package com.zautomate.zportal.module.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonService implements UserDetailsService {
	
	@Autowired
	PersonDao personDao;
	
	/*
	 * Method declared in the spring security framework.
	 * Method used to load a UserDetails object by username
	 * @param email of the user.
	 * @return Person object if its exists in the database.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return personDao.findOneByEmail(username);
	}

}
