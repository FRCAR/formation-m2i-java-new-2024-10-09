package com.bigcorp.project.main.correction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.bigcorp.project.main.concurrent.HeavyResource;

public class ConnectionPoolTpCorrection {

	private static final int NUMBER_OF_TRANSACTIONS_TO_PROCEED = 20;

	public static void main(String args[]) {
		HeavyResource heavyResourceA = new HeavyResource("A");
		HeavyResource heavyResourceB = new HeavyResource("B");
		HeavyResource heavyResourceC = new HeavyResource("C");

		// runSequentially(heavyResourceA, heavyResourceB, heavyResourceC);
		runConcurrently(heavyResourceA, heavyResourceB, heavyResourceC);
	}

	private static void runConcurrently(HeavyResource heavyResourceA, HeavyResource heavyResourceB,
			HeavyResource heavyResourceC) {

		Semaphore resourceLimiter = new Semaphore(4);
		HeavyResourceRunnable heavyResourceRunnableA = new HeavyResourceRunnable(heavyResourceA, resourceLimiter);
		HeavyResourceRunnable heavyResourceRunnableB = new HeavyResourceRunnable(heavyResourceB, resourceLimiter);
		HeavyResourceRunnable heavyResourceRunnableC = new HeavyResourceRunnable(heavyResourceC, resourceLimiter);
		int doneTransactions = 0;
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		long startTimeMillis = System.currentTimeMillis();
		while (doneTransactions < NUMBER_OF_TRANSACTIONS_TO_PROCEED) {
			HeavyResourceRunnable heavyResourceToTreat = chooseARandomRunnable(heavyResourceRunnableA,
					heavyResourceRunnableB, heavyResourceRunnableC);
			threadPool.execute(heavyResourceToTreat);
			doneTransactions++;
		}
		try {
			threadPool.shutdown();
			threadPool.awaitTermination(40, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTimeMillis = System.currentTimeMillis();
		double transactionsPerSeconds = 1000f * NUMBER_OF_TRANSACTIONS_TO_PROCEED
				/
				(endTimeMillis
						- startTimeMillis);
		System.out.println(String.format("Ai fait %1$.1f transactions par seconde", transactionsPerSeconds));
	}

	private static void runSequentially(HeavyResource heavyResourceA, HeavyResource heavyResourceB,
			HeavyResource heavyResourceC) {
		int doneTransactions = 0;
		long startTimeMillis = System.currentTimeMillis();
		while (doneTransactions < NUMBER_OF_TRANSACTIONS_TO_PROCEED) {
			HeavyResource heavyResourceToTreat = chooseARandomResource(heavyResourceA, heavyResourceB, heavyResourceC);
			heavyResourceToTreat.beginTransaction();
			heavyResourceToTreat.endTransaction();
			doneTransactions++;
		}
		long endTimeMillis = System.currentTimeMillis();
		double transactionsPerSeconds = 1000f * NUMBER_OF_TRANSACTIONS_TO_PROCEED
				/
				(endTimeMillis
						- startTimeMillis);
		System.out.println(String.format("Ai fait %1$.1f transactions par seconde", transactionsPerSeconds));

	}

	private static HeavyResource chooseARandomResource(HeavyResource heavyResourceA, HeavyResource heavyResourceB,
			HeavyResource heavyResourceC) {
		int resourceNumber = (int) (Math.random() * 3);
		switch (resourceNumber) {
		case 0:
			return heavyResourceA;
		case 1:
			return heavyResourceB;
		default:
			return heavyResourceC;
		}
	}

	private static HeavyResourceRunnable chooseARandomRunnable(HeavyResourceRunnable a, HeavyResourceRunnable b,
			HeavyResourceRunnable c) {
		int resourceNumber = (int) (Math.random() * 3);
		switch (resourceNumber) {
		case 0:
			return a;
		case 1:
			return b;
		default:
			return c;
		}
	}

	private static final class HeavyResourceRunnable implements Runnable {

		private HeavyResource heavyResource;
		private Semaphore resourceLimiter;
		private Semaphore internalSemaphore = new Semaphore(1);

		public HeavyResourceRunnable(HeavyResource heavyResource, Semaphore resourceLimiter) {
			super();
			this.heavyResource = heavyResource;
			this.resourceLimiter = resourceLimiter;
		}

		@Override
		public void run() {
			try {
//				this.resourceLimiter.acquire();
				this.internalSemaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.heavyResource.beginTransaction();
			this.heavyResource.endTransaction();

//			this.resourceLimiter.release();
			this.internalSemaphore.release();
		}

	}

}
