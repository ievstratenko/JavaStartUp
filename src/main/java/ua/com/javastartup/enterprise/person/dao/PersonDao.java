package ua.com.javastartup.enterprise.person.dao;

import ua.com.javastartup.enterprise.person.model.Person;

public interface PersonDao {

	Person findById(Long id);

	void save(Person person);

	void delete(Person person);

	void delete(Long id);

}