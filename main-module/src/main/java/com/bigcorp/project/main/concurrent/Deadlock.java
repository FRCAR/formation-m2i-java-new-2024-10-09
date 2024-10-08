package com.bigcorp.project.main.concurrent;

import com.bigcorp.project.main.concurrent.Deadlock.PersonnePolie;

public class Deadlock {
	static class PersonnePolie {
		private final String name;

		public PersonnePolie(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public synchronized void saluer(PersonnePolie autre) {
			System.out.format("%s: je salue %s ! %n",
					this.name, autre.getName());
			autre.repondreAuSalut(this);
		}

		public synchronized void repondreAuSalut(PersonnePolie autre) {
			System.out.format("%s:je réponds au salut de : %s !%n",
					this.name, autre.getName());
		}
	}

	public static void main(String[] args) {
		final PersonnePolie alphonse = new PersonnePolie("Alphonse");
		final PersonnePolie gaston = new PersonnePolie("Gaston");
		new Thread(new Runnable() {
			public void run() {
				alphonse.saluer(gaston);
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				gaston.saluer(alphonse);
			}
		}).start();
	}
}