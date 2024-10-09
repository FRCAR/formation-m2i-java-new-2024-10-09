package com.bigcorp.project.main.correction;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Démonstration de streams
 */
public class MainStream {

	public static void main(String[] args) {
		List<String> maListe = List.of("Salut", "Coucou", "super", "youpi");

		System.out.println("Affichage avec une boucle for");
		for (String string : maListe) {
			if (string.toLowerCase().startsWith("s")) {
				System.out.println(string);
			}
		}

		System.out.println("Affichage avec un stream");
		maListe.stream()
				.filter(s -> s.toLowerCase().startsWith("s"))
				.forEach(System.out::println);

		System.out.println("Affichage avec un stream plus complexe");
		maListe.stream()
				.filter(s -> s.toLowerCase().startsWith("s"))
				.map(s -> s.length())
				.map(i -> i * 2)
				.forEach(System.out::println);

		System.out.println("Affichage avec un stream de comptage de caractère");
		int sommeDesLongueursDeMesChainesQuiCommencentParS = maListe.stream()
				.filter(s -> s.toLowerCase().startsWith("s"))
				.mapToInt(String::length).sum();

		System.out.println("La taille vaut : " + sommeDesLongueursDeMesChainesQuiCommencentParS);

		System.out.println("Création d'une liste à partir du résultat d'un stream");
		List<String> maListeFiltree = maListe.stream()
				.filter(s -> s.length() > 2)
				.collect(Collectors.toList());

	}

}
