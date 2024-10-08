package com.bigcorp.project.main.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Simule une course , où chaque participant attend un signal 
 * pour démarrer simultanément. Chaque participant met un certain 
 * temps à arriver. La gestion des différents participants se fait grâce à 
 * des threads séparés.
 */
public class CreateCyclicBarrier {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Démarrage Thread principal");
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Announcement());
		new Thread(new Runner(cyclicBarrier)).start();
		new Thread(new Runner(cyclicBarrier)).start();
		new Thread(new Runner(cyclicBarrier)).start();
		new Thread(new Runner(cyclicBarrier)).start();
		Thread.sleep(3000);
		new Thread(new Runner(cyclicBarrier)).start();
		System.out.println("Fin Thread principal");
	}

	private static final class Runner implements Runnable {

		private CyclicBarrier barrier;

		public Runner(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			System.out.println("Démarrage Runner");
			try {
				System.out.println("Le coureur se place sur la piste");
				//Cette instruction met en wait le thread en cours, tant
				//que this.barrier n'a pas atteint sa limite. 
				//Le thread en cours passe alors en runnable, ainsi
				//que tous les threads qui attendaient que la barrière "s'ouvre"
				this.barrier.await();
				System.out.println("Il court !");
				Thread.sleep((long) (Math.random() * 10000));
				System.out.println("Il arrive !");
			} catch (InterruptedException | BrokenBarrierException e) {
				return;
			}
			System.out.println("Fin Runner");
		}
	}

	private static final class Announcement implements Runnable {

		@Override
		public void run() {
			System.out.println("La course démarre.");
		}
	}

}
