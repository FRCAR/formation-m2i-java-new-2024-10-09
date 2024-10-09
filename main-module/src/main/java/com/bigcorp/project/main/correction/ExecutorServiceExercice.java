package com.bigcorp.project.main.correction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Exercice ExecutorsService : Créer un Callable renvoyant un nombre aléatoire ,
 * et qui attend entre 5 et 10 secondes avant de le renvoyer. Grâce à un
 * ExecutorService, lancer 10 fois le callable et afficher la première valeur
 * retournée. Utiliser un FixedThreadPool pour créer l’ExecutorService.
 */
public class ExecutorServiceExercice {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		System.out.println("Démarrage Thread principal");
		long startTime = System.currentTimeMillis();

		//Préparation des callables
		List<Callable<Long>> callables = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			callables.add(new LongTaskCallable());
		}

		//Préparation de l'ExecutorService
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		
		//Exécution d'invokeAny, qui renvoie le premier résultat trouvé
		Long premierLongTrouve = executorService.invokeAny(callables);
		executorService.shutdown();

		long endTime = System.currentTimeMillis();
		System.out.println("Premier long trouvé : " + premierLongTrouve + " en  " + (endTime - startTime) + "ms.");

		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskCallable implements Callable<Long> {
		@Override
		public Long call() {
			System.out.println("Démarrage LongTaskCallable");
			try {
				int sleepTime = new Random().nextInt(5, 11);
				TimeUnit.SECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {
				return 0l;
			}
			System.out.println("Fin LongTaskCallable");
			return (long) (Math.random() * 50);
		}
	}

}
