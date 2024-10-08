package com.bigcorp.project.main.concurrent;

public class ConnectionPoolTp {

	private static final int NUMBER_OF_TRANSACTIONS_TO_PROCEED = 20;

	public static void main(String args[]) {
		HeavyResource heavyResourceA = new HeavyResource("A");
		HeavyResource heavyResourceB = new HeavyResource("B");
		HeavyResource heavyResourceC = new HeavyResource("C");

		runSequentially(heavyResourceA, heavyResourceB, heavyResourceC);

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

}
