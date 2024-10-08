package com.bigcorp.project.main.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Simule une ressource 'lourde', qui met du temps à répondre à ses méthodes.
 *
 */
public class HeavyResource {

	private static final int OPERATION_DURATION = 100;
	private String name;
	private AtomicBoolean transactionInit = new AtomicBoolean(false);

	public HeavyResource(String name) {
		super();
		this.name = name;
	}

	/**
	 * Commence une transaction. Cette opération dure 100ms. Cette méthode lance une
	 * exception si une transaction est toujours en cours.
	 */
	public void beginTransaction() {
		if (!this.transactionInit.compareAndSet(false, true)) {
			throw new RuntimeException(
					"Une transaction est déjà démarrée, il faut la terminer avant d'en démarrer une nouvelle.");
		}
		System.out.println(String.format("La ressource %1$s démarre une transaction.", this.name));
		try {
			TimeUnit.MILLISECONDS.sleep(OPERATION_DURATION);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Termine une transaction. Cette opération dure 100ms. Cette méthode lance une
	 * exception si aucune transaction n'est en cours.
	 */
	public void endTransaction() {
		if (!this.transactionInit.compareAndSet(true, false)) {
			throw new RuntimeException(
					"Aucune transaction n'est déjà démarrée, il faut en démarrer une avant d'en commencer une autre.");
		}
		System.out.println(String.format("La ressource %1$s termine sa transaction.", this.name));
		try {
			TimeUnit.MILLISECONDS.sleep(OPERATION_DURATION);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
