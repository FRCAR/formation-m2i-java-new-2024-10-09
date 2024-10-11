package com.bigcorp.project.main.correction;

/**
 * Démonstration des différentes syntaxes de switch
 */
public class SwitchExercice {

	private enum Etat {
		OK, KO, UNDEFINED
	}

	public static void main(String[] args) {

		int monEntier = 2;
		boolean activation = false;

		// Avec le nouveau Switch
		// en prenant en compte une variable int statut , et un booléen.
		// l’algorithme met à jour une variable de type enum
		// l’enum a trois valeurs : OK, KO, UNDEFINED
		// si statut == 1, enum = OK, si statut ==0, enum = KO
		// si statut == 2, et que le booléen vaut true, enum = OK
		// enum = UNDEFINED sinon
		Etat monResultat = switch (monEntier) {
		case 0 -> Etat.KO;
		case 1 -> Etat.OK;
		case 2 -> {
			if (activation) {
				yield Etat.OK;
			} else {
				yield Etat.KO;
			}
		}
		default -> {
			yield Etat.UNDEFINED;
		}
		};

		System.out.println(monResultat);

		// Avec l'ancien switch
		Etat monResultatAvecAncienSwitch = null;
		switch (monEntier) {
		case 0:
			monResultatAvecAncienSwitch = Etat.KO;
			break;
		case 1:
			monResultatAvecAncienSwitch = Etat.OK;
			break;
		case 2:
			if (activation) {
				monResultatAvecAncienSwitch = Etat.OK;
			} else {
				monResultatAvecAncienSwitch = Etat.KO;
			}
			break;
		default :
			monResultatAvecAncienSwitch = Etat.UNDEFINED;
			break;
		}

	}

}
