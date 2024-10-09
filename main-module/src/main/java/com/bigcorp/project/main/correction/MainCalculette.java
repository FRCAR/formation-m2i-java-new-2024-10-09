package com.bigcorp.project.main.correction;

/**
 * Classe de démonstration des lambdas et de la programmation 
 * fonctionnelle en Java.
 */
public class MainCalculette {

	public static void main(String[] args) {
		MainCalculette mainCalculette = new MainCalculette();

		// Utilisation de classes implémentant l'interface
		Operation addition = new Addition();
		double resultat = mainCalculette.calcule(3, 4, addition);

		Operation multiplication = new Multiplication();
		resultat = mainCalculette.calcule(6, 2, multiplication);

		// Utilisation d'une classe définie en ligne
		Operation division = new Operation() {
			@Override
			public double apply(double d1, double d2) {
				return d1 / d2;
			}
		};
		resultat = mainCalculette.calcule(6, 2, division);

		// Utilisation d'une lambda pour implémenter une interface
		// Ici, la syntaxe est encore trop verbeuse ...
		
		Operation modulo = (double d1, double d2) -> {
			System.out.println("Salut");
			double res = d1 % d2;
			return res;
		};
		resultat = mainCalculette.calcule(6, 2, modulo);
		resultat = mainCalculette.calcule(3, 5, (double d1, double d2) -> {
			return d1 % d2;
		});

		
		resultat = mainCalculette.calcule(3, 5, (d1, d2) -> d1 % d2);

		System.out.println(resultat);

	}

	public double calcule(double d1, double d2, Operation operation) {
		System.out.println("Début de l'opération");
		if (d1 < 0) {
			throw new IllegalArgumentException("d1 est négatif");
		}
		if (d2 < 0) {
			throw new IllegalArgumentException("d2 est négatif");
		}
		double resultat = operation.apply(d1, d2);
		return resultat;
	}

}
