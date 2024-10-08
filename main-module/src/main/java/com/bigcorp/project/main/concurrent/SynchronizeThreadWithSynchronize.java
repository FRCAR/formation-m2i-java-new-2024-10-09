package com.bigcorp.project.main.concurrent;

import java.util.concurrent.TimeUnit;

public class SynchronizeThreadWithSynchronize {

	public static void main(String[] args) {

		System.out.println("Démarrage Thread principal");
		LongTaskRunnable runnable = new LongTaskRunnable();
		new Thread(runnable).start();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		runnable.shouldStop = true;
		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskRunnable implements Runnable {

		public volatile boolean shouldStop;

		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			int i = 0;
			while (!shouldStop) {
				i++;
			}
			System.out.println("Fin LongTaskRunnable");
		}

	}

}
