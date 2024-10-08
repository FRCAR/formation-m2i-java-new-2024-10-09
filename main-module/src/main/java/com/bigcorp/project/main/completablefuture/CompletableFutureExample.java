package com.bigcorp.project.main.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

	private String compute() {
		System.out.println("Début de la méthode compute");
		try {
			Thread.sleep(2300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Fin de la méthode compute");
		return "résultat";
	}

	/**
	 * Méthode utilitaire renvoyant un CompletableFuture renvoyant lui 
	 * même un Integer valant 42
	 * @param pool
	 * @return
	 */
	private CompletableFuture<Integer> computeInteger(ExecutorService pool) {
		return CompletableFuture.supplyAsync(() -> 42,
				pool);
	}
	
	/**
	 * Méthode utilitaire renvoyant un CompletableFuture renvoyant lui 
	 * même si i > 100
	 * @param pool
	 * @return
	 */
	private CompletableFuture<Boolean> computeBoolean(Integer i, ExecutorService pool) {
		return CompletableFuture.supplyAsync(() -> i > 100, pool);
	}
	
	/**
	 * Méthode utilitaire renvoyant un CompletableFuture renvoyant lui 
	 * même un Integer valant 3 * i
	 * @param pool
	 * @return
	 */
	private CompletableFuture<Integer> computeInteger(int i, ExecutorService pool) {
		return CompletableFuture.supplyAsync(() -> 3 * i, pool);
	}

	public static void main(String[] args) throws Exception {

		CompletableFutureExample cfe = new CompletableFutureExample();

		cfe.runCompletableFutureWithJoin();
		cfe.runCompletableFutureWithThenRun();
		cfe.runCompletableFutureWithCompose();
		cfe.runCompletableFutureWithAll();
	}

	/**
	 * Utilise join : bloque le Thread en cours tant que le résultat n'a pas été calculé
	 * @throws Exception
	 */
	public void runCompletableFutureWithJoin() throws Exception {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);

		CompletableFuture<String> result = CompletableFuture.supplyAsync(this::compute,
				newFixedThreadPool);
		System.out.println("\r\nDémarrage de la méthode runCompletableFutureWithJoin ... ");
		try {
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Le Thread main continue ... ");
		System.out.println(result.join());
		System.out.println("Le Thread main est fini ... ");
		newFixedThreadPool.shutdown();
	}

	/**
	 * Utilise thenRun() : ne bloque pas le Thread principal : la lambda représente
	 * le code exécuté dans l'autre Thread quand result est terminé.
	 * @throws Exception
	 */
	private void runCompletableFutureWithThenRun() throws Exception {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);

		CompletableFuture<String> result = CompletableFuture.supplyAsync(this::compute,
				newFixedThreadPool);
		System.out.println("\r\nDémarrage de la méthode runCompletableFutureWithThenRun ... ");
		try {
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Le Thread main continue ... ");
		result.thenRun(() -> {
			System.out.println("Action rajoutée avec thenRun terminée.");
		});
		System.out.println("Le Thread main est fini ... ");
		newFixedThreadPool.shutdown();

	}

	/**
	 * Compose puis utilise get : les calculs composés sont exécutés dans un (ou
	 * plusieurs autres Threads) puis le thread principal attend que le calcul
	 * soit terminé, à cause de get(), pour reprendre.
	 * @throws Exception
	 */
	private void runCompletableFutureWithCompose() throws Exception {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);

		CompletableFuture<Boolean> result = computeInteger(newFixedThreadPool)
				.thenCompose(i -> computeInteger(i, newFixedThreadPool))
				.thenCompose(i -> computeBoolean(i, newFixedThreadPool));
		System.out.println("\r\nDémarrage de la méthode runCompletableFutureWithCompose ... ");
		try {
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Le Thread main continue ... ");
		System.out.println("On renvoie le résultat final : " + result.get());
		System.out.println("Le Thread main est fini ... ");
		newFixedThreadPool.shutdown();
	}

	/**
	 * Utilise allOf() puis thenRun() pour effectuer trois opérations en parallèle, puis afficher un message
	 * 
	 * Utilise ensuite un combined CompletableFuture pour joindre le résultat de deux auxtres Completables Futures
	 * @throws Exception
	 */
	private void runCompletableFutureWithAll() throws Exception {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

		CompletableFuture.allOf(computeInteger(newFixedThreadPool),
				computeInteger(3, newFixedThreadPool),
				computeBoolean(2000, newFixedThreadPool)).thenRun(() -> {
					System.out.println("Tous les CompletableFuture de allOf sont finis");
				});

		CompletableFuture<String> combinedCf = computeInteger(newFixedThreadPool).thenCombine(computeInteger(3,
				newFixedThreadPool), (Integer i1,
						Integer i2) -> {
					return "i1 vaut : " + i1 + " et i2 vaut :" + i2;
				});
		System.out.println(combinedCf.get());

		System.out.println("\r\nDémarrage de la méthode runCompletableFutureWithAll ... ");
		try {
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Le Thread main continue ... ");
		System.out.println("Le Thread main est fini ... ");
		newFixedThreadPool.shutdown();
	}

}
