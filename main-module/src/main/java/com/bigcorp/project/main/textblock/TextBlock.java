package com.bigcorp.project.main.textblock;

import java.text.NumberFormat;

public class TextBlock {

	public static void main(String[] args) {
		// Syntaxe recommandée
		String premierTextBlock = """
				Le Bret.
				Si tu laissais un peu ton âme mousquetaire
				La fortune et la gloire…
				Cyrano.
				                        Et que faudrait-il faire ?
				Chercher un protecteur puissant, prendre un patron,
				Et comme un lierre obscur qui circonvient un tronc
				Et s’en fait un tuteur en lui léchant l’écorce,
				Grimper par ruse au lieu de s’élever par force ?
				Non, merci. Dédier, comme tous ils le font,
				Des vers aux financiers ? Se changer en bouffon
				Dans l’espoir vil de voir, aux lèvres d’un ministre,
				Naître un sourire, enfin, qui ne soit pas sinistre ?
				Non, merci.(...)
				""";
		System.out.println(premierTextBlock);

		// Introduit un décalage : le nombre d'espaces entre la ligne 1 et la ligne 2
		String decalage = """
						Hé bonjour, comment allez-vous ?
					""";
		System.out.println(decalage);

		// Nouvelle méthode formatted
		String output = """
				Nom : %1$s
				Téléphone : %2$s
				Adresse : %3$s
				Salaire : %4$.2f€
				""".formatted("Dupont", "Jean", "5 rue tabaga", 12300.2);
		System.out.println(output);

		// Aucun rapport, mais intéressant depuis Java 12
		NumberFormat fmt = NumberFormat.getCompactNumberInstance();
		String result = fmt.format(13_250_350);
		System.out.println(result);
		
	}

}
