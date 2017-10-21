package com.zautomate.modules.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonService implements UserDetailsService {

	private PersonDao personDao;

	@Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    /*
	 * Method declared in the spring security framework.
	 * Method used to load a UserDetails object by username
	 * @param email of the user.
	 * @return Person object if its exists in the database.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return personDao.findFirstByEmail(username);
	}

	/*
	 * Method used to get all users of the system
	 * @param
	 * @return List of Person objects.
	 */
	List<Person> getAllUsers() {
		return personDao.findAll();
	}

}
