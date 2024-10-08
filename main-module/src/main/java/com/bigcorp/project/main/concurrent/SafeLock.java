package com.bigcorp.project.main.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeLock {
	static class PersonnePolie {
		private final String name;
		private final Lock lock = new ReentrantLock();

		public PersonnePolie(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public boolean initialiseSalut(PersonnePolie autre) {
			Boolean myLock = false;
			Boolean yourLock = false;
			try {
				myLock = lock.tryLock();
				yourLock = autre.lock.tryLock();
			} finally {
				if (!(myLock && yourLock)) {
					if (myLock) {
						lock.unlock();
					}
					if (yourLock) {
						autre.lock.unlock();
					}
				}
			}
			return myLock && yourLock;
		}

		public void saluer(PersonnePolie autre) {
			if (initialiseSalut(autre)) {
				try {
					System.out.format("%s: %s m'a salué !%n",
							this.name, autre.getName());
					autre.repondreAuSalut(this);
				} finally {
					lock.unlock();
					autre.lock.unlock();
				}
			} else {
				System.out.format("%s: %s a commencé à me saluer, mais a vu que je commençais à le saluer.%n",
						this.name, autre.getName());
			}
		}

		public void repondreAuSalut(PersonnePolie bower) {
			System.out.format("%s: %s " +
					" m'a rendu mon salut !%n",
					this.name, bower.getName());
		}
	}

	static class BoucleSalut implements Runnable {
		private PersonnePolie gaston;
		private PersonnePolie alphonse;

		public BoucleSalut(PersonnePolie gaston, PersonnePolie alphonse) {
			this.gaston = gaston;
			this.alphonse = alphonse;
		}

		public void run() {
			Random random = new Random();
			for (;;) {
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException e) {
				}
				alphonse.saluer(gaston);
			}
		}
	}

	public static void main(String[] args) {
		final PersonnePolie alphonse = new PersonnePolie("Alphonse");
		final PersonnePolie gaston = new PersonnePolie("Gaston");
		new Thread(new BoucleSalut(alphonse, gaston)).start();
		new Thread(new BoucleSalut(gaston, alphonse)).start();
	}
}