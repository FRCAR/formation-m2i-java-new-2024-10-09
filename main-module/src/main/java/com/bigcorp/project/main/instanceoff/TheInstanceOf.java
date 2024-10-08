package com.bigcorp.project.main.instanceoff;

/**
 * Classe de démonstration du nouvel instanceof
 */
public class TheInstanceOf {

	public static void main(String[] args) {

		Meuble meuble = new Meuble();

		// instanceof, cast et création d'une nouvelle variable en une ligne
		if (meuble instanceof Chaise c) {
			System.out.println("La chaise a " + c.pieds + " pieds.");
		} else if (meuble instanceof Table t) {
			System.out.println("La table a " + t.places + " places.");
		}

		// La nouvelle fonctionnalité n'a pas de sens si instanceof renvoie false
		if (!(meuble instanceof Chaise c)) {
			// c ne peut être utilisé ici.
			return;
		}

		// Utilisation de la nouvelle variable dans la suite de l'expression booléenne.
		if (meuble instanceof Table t && t.places > 3) {
			System.out.println("La table a " + t.places + " places.");
		}
		System.out.println("La chaise a " + c.pieds + " pieds.");

	}
}

class Meuble {
	public String nom;
}

class Chaise extends Meuble {
	public int pieds;
}

class Table extends Meuble {
	public int places;
}