package com.bigcorp.project.main.concurrent;

public class CreateNewThread {

	public static void main(String[] args) {

		System.out.println("Démarrage Thread principal");
		// Ci-dessous, ne démarre pas un Thread
		// new LongTaskRunnable().run();
		new Thread(new LongTaskRunnable()).start();
		// Ci-dessous, ne démarre pas un Thread
		// new LongTaskThread().run();
		LongTaskThread longTaskThread = new LongTaskThread();
		longTaskThread.start();
		// longTaskThread.join();
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

	private static final class LongTaskThread extends Thread {
		@Override
		public void run() {
			System.out.println("Démarrage LongTaskThread");
			try {
				Thread.sleep(2000);
				// TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				return;
			}
			System.out.println("Fin LongTaskThread");
		}

	}

}
