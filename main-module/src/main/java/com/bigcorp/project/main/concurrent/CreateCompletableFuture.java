package com.bigcorp.project.main.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CreateCompletableFuture {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return "Formation";
		}).thenApply(name -> {
			return "Préfixe " + name;
		}).thenApply(greeting -> {
			return greeting + " suffixe";
		}).exceptionally(ex -> {
			System.out.println("Exception happened - " + ex.getMessage());
			return "Unknown!";
		});

		System.out.println(welcomeText.get());
		System.out.println("coucou");
	}

}
