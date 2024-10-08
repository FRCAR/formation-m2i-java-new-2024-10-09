package com.bigcorp.project.main.concurrent;

public class ThreadPriority {

	public static void main(String[] args) {

		System.out.println("Démarrage Thread principal");
		LongTaskRunnable runnable1 = new LongTaskRunnable("Priorité 1");
		Thread thread1 = new Thread(runnable1);
		thread1.setPriority(Thread.MIN_PRIORITY);

		LongTaskRunnable runnable2 = new LongTaskRunnable("Priorité 5");
		Thread thread2 = new Thread(runnable2);
		thread2.setPriority(Thread.NORM_PRIORITY);

		LongTaskRunnable runnable3 = new LongTaskRunnable("Priorité 10");
		Thread thread3 = new Thread(runnable3);
		thread3.setPriority(Thread.MAX_PRIORITY);

		thread1.start();
		thread2.start();
		thread3.start();
		System.out.println("Fin Thread principal");
	}

	private static final class LongTaskRunnable implements Runnable {

		private String name;

		public LongTaskRunnable(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println("Démarrage LongTaskRunnable " + name);
			for (int i = 0; i < 10000; i++) {
				int j = i * i;
				System.out.println(this.name + " - opération " + i);
			}
			System.out.println("Fin LongTaskRunnable " + name);
		}
	}

}
