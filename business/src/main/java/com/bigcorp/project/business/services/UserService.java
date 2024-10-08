package com.bigcorp.project.business.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bigcorp.project.business.exception.InvalidUserException;
import com.bigcorp.project.data.model.Subscription;
import com.bigcorp.project.data.model.User;
import com.bigcorp.project.data.repository.SubscriptionRepositoryImpl;
import com.bigcorp.project.data.repository.UserRepositoryImpl;

public class UserService {
	
	private UserRepositoryImpl userRepository;
	private SubscriptionRepositoryImpl subscriptionRepository;
	
	private Set<User> bannedUsers = new HashSet<>();
	
	/**
	 * Renvoie l'utilisateur loggé.
	 * Applique les contrôles de sécurité préalables et
	 * peut envoyer une {@link InvalidUserException} si ceux-ci échouent. 
	 * 
	 * @return
	 */
	public User getCurrentlyLoggedUser()  {
		User user = userRepository.getCurrentlyLoggedUser();
		if(user == null) {
			throw new InvalidUserException("No user currently logged");
		}
		if(this.bannedUsers.contains(user)) {
			throw new InvalidUserException("Banned user");			
		}
		return user;
	}
	
	/**
	 * Renvoie une liste d'abonnements de l'utilisateur loggé.
	 * Applique les contrôles de sécurité préalables et
	 * peut envoyer une {@link InvalidUserException} si ceux-ci échouent. 
	 * 
	 * @return
	 */
	public List<Subscription> getAllSubscriptionsFromCurrentlyLoggedUser(){
		User currentlyLoggedUser = getCurrentlyLoggedUser();
		return subscriptionRepository.getAllUserSubscriptions(currentlyLoggedUser.getId());
		
	}
	
	/**
	 * Supprime l'abonnement ayant l'id subscriptionId de l'utilisateur loggé.
	 * Applique les contrôles de sécurité préalables et
	 * peut envoyer une {@link InvalidUserException} si ceux-ci échouent. 
	 * 
	 * @return
	 */
	public void removeSubscription(Long subscriptionId) {
		subscriptionRepository.removeSubscription(subscriptionId);
	}

}
