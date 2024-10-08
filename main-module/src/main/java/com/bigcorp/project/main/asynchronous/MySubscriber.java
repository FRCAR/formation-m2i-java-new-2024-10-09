package com.bigcorp.project.main.asynchronous;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * Un Subscriber : consommateur d'instances de T
 *
 * @param <T>
 */
public class MySubscriber<T> implements Subscriber<T> {

	private Subscription subscription;

	private int counter;
	private String name;

	public MySubscriber(String name) {
		this.name = name;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		System.out.println(name + " a souscrit à " + subscription);
		this.subscription = subscription;
		this.subscription.request(1);

	}

	@Override
	public void onNext(T item) {
		System.out.println(name + " a reçu : " + item);
		counter++;
		this.subscription.request(1);

	}

	@Override
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		System.out.println(name + " a fini !");
	}

	public int getCounter() {
		return counter;
	}

}
