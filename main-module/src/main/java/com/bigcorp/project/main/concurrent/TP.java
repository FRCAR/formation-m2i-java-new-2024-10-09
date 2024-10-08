package com.bigcorp.project.main.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TP {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Démarrage Thread principal");
		Travailleur travailleur = new Travailleur();
		Runnable runnable1 = new Fournisseur("fournisseur 1", travailleur);
		Runnable runnable2 = new Fournisseur("fournisseur 2", travailleur);
		Runnable runnable3 = new Fournisseur("fournisseur 3", travailleur);
		Runnable runnable4 = new Fournisseur("fournisseur 4", travailleur);
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
		executorService.scheduleAtFixedRate(runnable1, 0, 2, TimeUnit.SECONDS);
		executorService.scheduleAtFixedRate(runnable2, 0, 2, TimeUnit.SECONDS);
		executorService.scheduleAtFixedRate(runnable3, 0, 2, TimeUnit.SECONDS);
		executorService.scheduleAtFixedRate(runnable4, 0, 2, TimeUnit.SECONDS);

		Thread.sleep(60000);
		executorService.shutdown();

		System.out.println("Fin Thread principal");
	}

	private static final class Fournisseur implements Runnable {

		private Travailleur travailleur;
		private String name;

		public Fournisseur(String name, Travailleur travailleur) {
			this.name = name;
			this.travailleur = travailleur;
		}

		@Override
		public void run() {
			System.out.println("Démarrage " + name);

			if (Math.random() > 0.5) {
				this.travailleur.travailler(new Tache());
			}

			System.out.println("Fin " + name);
		}
	}

	private static final class Tache {

		public void effectuer() {
			System.out.println(">>>Démarrage Tache");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				return;
			}

			System.out.println(">>>Fin Tache");
		}
	}

	private static final class Travailleur {

		private Semaphore semaphore = new Semaphore(1, true);

		public void travailler(Tache tache) {
			try {
				this.semaphore.acquire();
				System.out.println(">Démarrage travail");
				tache.effectuer();
			} catch (InterruptedException e) {
				return;
			} finally {
				this.semaphore.release();
			}

			System.out.println(">Fin travail");
		}
	}
}
