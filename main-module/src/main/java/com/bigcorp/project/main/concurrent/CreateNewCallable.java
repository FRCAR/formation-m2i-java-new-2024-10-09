package com.bigcorp.project.main.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Crée et lance un Callabale grâce à une FutureTask
 */
public class CreateNewCallable {

	public static void main(String[] args) {

		System.out.println("Démarrage Thread principal");
		// Ci-dessous, ne démarre pas un Thread
		// new LongTaskRunnable().run();
		Callable<Long> callable = new LongTaskCallable();
		FutureTask<Long> futureTask = new FutureTask<>(callable);
		new Thread(futureTask).start();
		try {
			Long result = futureTask.get();
			System.out.println(String.format("Le résultat vaut : %s", result));
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}

		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskCallable implements Callable<Long> {
		@Override
		public Long call() {
			System.out.println("Démarrage LongTaskCallable");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return 0l;
			}
			System.out.println("Fin LongTaskCallable");
			return 42l;
		}
	}

}
