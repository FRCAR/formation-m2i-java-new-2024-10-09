package com.bigcorp.project.main.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Utilisation de multiples barrières pour gérer
 * une boucle infinie de courses. {@link CreateCyclicBarrier}
 */
public class CreateCyclicBarrierLoop {

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

		System.out.println("Démarrage Thread principal");
		BarrierResetter startBarrierResetter = new BarrierResetter();
		BarrierResetter stopBarrierResetter = new BarrierResetter();
		CyclicBarrier startBarrier = new CyclicBarrier(6, startBarrierResetter);
		CyclicBarrier stopBarrier = new CyclicBarrier(6, stopBarrierResetter);
		while (true) {
			new Thread(new Runner(startBarrier, stopBarrier)).start();
			new Thread(new Runner(startBarrier, stopBarrier)).start();
			new Thread(new Runner(startBarrier, stopBarrier)).start();
			new Thread(new Runner(startBarrier, stopBarrier)).start();
			Thread.sleep(1000);
			new Thread(new Runner(startBarrier, stopBarrier)).start();
			startBarrier.await();
			startBarrier.reset();
			stopBarrier.await();
			stopBarrier.reset();
		}
	}

	private static final class Runner implements Runnable {

		private CyclicBarrier startBarrier;
		private CyclicBarrier stopBarrier;

		public Runner(CyclicBarrier startBarrier, CyclicBarrier stopBarrier) {
			this.startBarrier = startBarrier;
			this.stopBarrier = stopBarrier;
		}

		@Override
		public void run() {
			try {
				System.out.println("Le coureur se place sur la piste");
				this.startBarrier.await();
				System.out.println("Il court !");
				Thread.sleep((long) (Math.random() * 10000));
				System.out.println("Il arrive !");
				this.stopBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				return;
			}
		}
	}

	private static final class BarrierResetter implements Runnable {

		@Override
		public void run() {
			System.out.println("Barrière reset.");
		}
	}

}
