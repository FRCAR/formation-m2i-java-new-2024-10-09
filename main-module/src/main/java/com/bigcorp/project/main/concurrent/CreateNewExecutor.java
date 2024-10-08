package com.bigcorp.project.main.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Utilise un ExecutorService pour lancer en parallèle 5 tâches sur
 * 3 threads.
 */
public class CreateNewExecutor {

	public static void main(String[] args) {

		System.out.println("Démarrage Thread principal");
		// Ci-dessous, ne démarre pas un Thread
		Runnable runnable = new LongTaskRunnable();
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		executorService.execute(runnable);
		executorService.execute(runnable);
		executorService.execute(runnable);
		executorService.execute(runnable);
		executorService.shutdown();

		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			try {
				Thread.sleep(2000);
				// TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				return;
			}

			System.out.println("Fin LongTaskRunnable");
		}
	}

}
