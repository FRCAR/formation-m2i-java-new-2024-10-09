package com.bigcorp.project.main.concurrent;

import java.util.concurrent.TimeUnit;

public class SynchronizationIssues {

	public static void main(String[] args) {

		System.out.println("Démarrage Thread principal");
		LongTaskRunnable runnable = new LongTaskRunnable();
		new Thread(runnable).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		runnable.orderStop();
		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskRunnable implements Runnable {

		private boolean stopOrdered = false;

		public void orderStop() {
			this.stopOrdered = true;
		}

		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			int i = 0;
			while (!stopOrdered) {
				i++;
			}
			System.out.println("Fin LongTaskRunnable");
		}
	}

	private static final class Counter {

		private int counter = 0;

		public void increment() {
			counter++;
		}

		public void decrement() {
			counter--;
		}

		public int value() {
			return counter;
		}

	}

}
