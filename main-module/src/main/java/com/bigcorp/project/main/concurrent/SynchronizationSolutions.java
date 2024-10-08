package com.bigcorp.project.main.concurrent;

import java.util.concurrent.TimeUnit;

public class SynchronizationSolutions {

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

		SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
		new Thread(new CounterRunnable(synchronizedCounter)).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		synchronizedCounter.decrementAndGet();

		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskRunnable implements Runnable {

		private volatile boolean stopOrdered = false;

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

	private static final class SynchronizedCounter {

		private int counter = 0;

		public synchronized int incrementAndGet() {
			System.out.println("Démarrage incrementAndGet");
			counter++;
			System.out.println("Fin incrementAndGet");
			return counter;

		}

		public synchronized int decrementAndGet() {
			System.out.println("Démarrage decrementAndGet");
			counter--;
			System.out.println("Fin decrementAndGet");
			return counter;
		}

		public int value() {
			return counter;
		}

	}

	private static final class CounterRunnable implements Runnable {

		private SynchronizedCounter synchronizedCounter;

		public CounterRunnable(SynchronizedCounter synchronizedCounter) {
			this.synchronizedCounter = synchronizedCounter;
		}

		@Override
		public void run() {
			this.synchronizedCounter.incrementAndGet();
		}

	}

}
