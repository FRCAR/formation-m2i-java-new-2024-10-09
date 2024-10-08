package com.bigcorp.project.main.string;

public class StringUtils {

	public static void main(String[] args) {
		String multilineString = "Voilà mon conseil : \n \n découper ses phrases \n en lignes.";

		// Crée un stream pour chaque ligne de multilineString
		multilineString.lines().forEach(System.out::println);

		// Un caractère blanc de la taille du M majuscule
		char emSpace = '\u2003';
		System.out.println("Ci après le caractère espace em -" + emSpace + "-");

		// Utilisons le dans une phrase
		String phrase = emSpace + "Salut!" + emSpace;
		System.out.println("Avec strip : début-" + phrase.strip() + "-fin");
		System.out.println("Avec trim : début-" + phrase.trim() + "-fin");
		String phrase2 = " " + emSpace;
		System.out.println("La phrase suivante est blank : " + phrase2.isBlank() + ", et est empty : " + phrase2
				.isEmpty());

		System.out.println("Avec stripLeading : début-" + phrase.stripLeading() + "-fin");
		System.out.println("Avec stripTrailing : début-" + phrase.stripTrailing() + "-fin");

		System.out.println(phrase.repeat(3));

		// Comparaison de StringBuilders
		var sb1 = new StringBuilder().append("coucou").append("coucou");
		var sb2 = new StringBuilder().append("coucou".repeat(2));
		System.out.println("La comparaison sb1,sb2 donne " + sb1.compareTo(sb2));

		// On aurait pareil avec StringBuffer, mais il est assez rare qu'on l'utilise
		var sbuf1 = new StringBuffer().append("coucou").append("coucou");
		var sbuf2 = new StringBuffer().append("coucou".repeat(2));
		System.out.println("La comparaison sbuf1,sbuf2 donne " + sbuf1.compareTo(sbuf2));

	}

}
