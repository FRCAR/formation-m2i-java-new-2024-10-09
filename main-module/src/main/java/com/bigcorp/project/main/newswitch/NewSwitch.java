package com.bigcorp.project.main.newswitch;

/**
 * Démonstration des différentes syntaxes de switch
 */
public class NewSwitch {

	private enum Etat {
		ARRETE, DEMARRE, EN_PAUSE, AVANCE_RAPIDE
	}

	public static void main(String[] args) {

		//Ancien switch
		Etat etat = Etat.ARRETE;
		switch (etat) {
		case ARRETE:
			double vitesse = 0;
			break;
		case DEMARRE:
			vitesse = 1;
			break;
		case EN_PAUSE:
			vitesse = 0;
			break;
		case AVANCE_RAPIDE:
			vitesse = 2;
			System.out.println("La vitesse vaut : " + vitesse);
			break;
		}

		//Nouveau switch
		switch (etat) {
		case ARRETE -> {
			System.out.println("Arret");
		}
		case AVANCE_RAPIDE -> {
			System.out.println("Avance rapide");
		}
		default -> {
			System.out.println("Aucune action");
		}
		}

		
		//Switch renvoyant une valeur : une instruction, d'où le ; à la fin.
		int j = switch (etat) {
		case ARRETE -> 0;
		case DEMARRE -> 1;
		default -> {
			int k = etat.toString().length();
			yield k;
		}
		};
		System.out.println(j);

	}

}
