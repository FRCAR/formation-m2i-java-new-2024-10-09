package com.bigcorp.project.main.concurrent;

public class SynchronizeThreadWithInterrupt {

	public static void main(String[] args) {

		System.out.println("Démarrage Thread principal");
		LongTaskRunnable runnable = new LongTaskRunnable();
		Thread thread = new Thread(runnable, "Thread de calcul");
		thread.start();
		try {
			// Le thread courant est WAITING jusqu'à ce que thread ait fini.
			Thread.sleep(1000);
			thread.interrupt();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Le résultat vaut : " + runnable.getLongComputationResult());
		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskRunnable implements Runnable {

		private int longComputationResult = -1;

		public int getLongComputationResult() {
			return longComputationResult;
		}

		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				return;
			}
			this.longComputationResult = (int) (Math.random() * 654654);

			System.out.println("Fin LongTaskRunnable");
		}

	}

}
