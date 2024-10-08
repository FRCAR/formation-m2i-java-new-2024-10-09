package com.bigcorp.project.main.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CreateNewExecutorWithInvoke {

	public static void main(String[] args) {

		System.out.println("Démarrage Thread principal");
		List<Callable<Long>> callables = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			callables.add(new LongTaskCallable());
		}
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		List<Future<Long>> futures = Collections.emptyList();
		try {
			futures = executorService.invokeAll(callables);
			for (Future<Long> future : futures) {
				System.out.println("Ai récupéré : " + future.get() + " à " + System.currentTimeMillis());
			}
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
		executorService.shutdown();

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
		ScheduledFuture<?> scheduleAtFixedRate = scheduledExecutorService.scheduleAtFixedRate(new LongTaskRunnable(), 0,
				3, TimeUnit.SECONDS);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			return;
		}
		scheduleAtFixedRate.cancel(false);
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
			return (long) (Math.random() * 100);
		}
	}

	private static final class LongTaskRunnable implements Runnable {
		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			try {
				Thread.sleep(500);
				// TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				return;
			}

			System.out.println("Fin LongTaskRunnable");
		}
	}

}
