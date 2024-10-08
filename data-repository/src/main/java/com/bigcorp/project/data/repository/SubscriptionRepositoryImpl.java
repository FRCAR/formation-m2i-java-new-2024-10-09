package com.bigcorp.project.data.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bigcorp.project.data.model.Subscription;

/**
 * Gère les opérations sur les abonnements
 */
public class SubscriptionRepositoryImpl  {
	
	private Map<Long, Subscription> subscriptions = new  HashMap<>();
	
	public SubscriptionRepositoryImpl() {
		Subscription subscription1 = new Subscription();
		subscription1.setId(1l);
		subscription1.setUserId(1l);
		subscription1.setStartDate(LocalDateTime.now().minusYears(1));
		subscription1.setEndDate(LocalDateTime.now());
		subscription1.setProductId(1l);
		this.subscriptions.put(subscription1.getId(), subscription1);
		

		Subscription subscription2 = new Subscription();
		subscription2.setId(2l);
		subscription2.setUserId(2l);
		subscription2.setStartDate(LocalDateTime.now().minusYears(1));
		subscription2.setEndDate(LocalDateTime.now());
		subscription1.setProductId(14l);
		this.subscriptions.put(subscription2.getId(), subscription2);
		

		Subscription subscription3 = new Subscription();
		subscription3.setId(3l);
		subscription3.setUserId(1l);
		subscription3.setStartDate(LocalDateTime.now().minusYears(1));
		subscription3.setEndDate(LocalDateTime.now());
		subscription3.setProductId(2l);
		this.subscriptions.put(subscription3.getId(), subscription3);
	}

	/**
	 * Renvoie tous les abonnements de l'utilisateur avec l'id userId
	 * @param userId
	 * @return
	 */
	public List<Subscription> getAllUserSubscriptions(Long userId) {
		return this.subscriptions.values().stream().filter(
					s -> s.getUserId().equals(userId)).toList();
	}

	/**
	 * Supprime l'abonnement ayant l'id id
	 * @param id
	 */
	public void removeSubscription(Long id) {
		this.subscriptions.remove(id);
	}
	
	
}
