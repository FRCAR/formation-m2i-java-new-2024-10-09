package com.bigcorp.project.data.repository;

import java.util.HashMap;
import java.util.Map;

import com.bigcorp.project.data.model.User;

/**
 * Gère les opérations sur les User
 */
public class UserRepositoryImpl  {
	
	private Map<Long, User> users = new  HashMap<>();
	
	public UserRepositoryImpl() {
		User user1 = new User();
		user1.setId(1l);
		user1.setFirstName("Jean");
		user1.setLastName("Dupont");
		users.put(user1.getId(), user1);
	}

	public User getCurrentlyLoggedUser() {
		return users.get(1l);
	}
	
}
