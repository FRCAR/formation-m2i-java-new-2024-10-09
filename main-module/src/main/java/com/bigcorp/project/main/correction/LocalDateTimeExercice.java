package com.bigcorp.project.main.correction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTimeExercice {

	public static void main(String[] args) {

		// Créer un programme qui instancie une LocalDate (avec .of()) et affiche si
		// cette dernière est dans une année bissextile.
		LocalDate maDate = LocalDate.of(2022, 12, 2);
		System.out.println("L'année de la date %1$s est bissextile : %2$b".formatted( maDate, maDate.isLeapYear()));

		// Créer un programme en Java qui prend un paramètre au lancement. Ce paramètre
		// représente une date au format ISO-8601 (AAAA-MM-JJ) .
		String dateArg = args[0];
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy");
		try {
			LocalDate date = LocalDate.parse(dateArg, DateTimeFormatter.ISO_DATE);
			// S’il parvient à transformer ce paramètre en LocalDate, il affiche si oui ou
			// non,
			// la date est une année bissextile.
			System.out.println("L'année de la date entrée au clavier est bissextile : " + date.isLeapYear());
			// Faire en sorte qu’il affiche le mois de l’année et le jour de la semaine.
			System.out.println("Mois : " + date.getMonth());
			System.out.println("Jour de la semaine : " + date.getDayOfWeek());
			String dateForHuman = date.format(dateTimeFormatter);
			// Bonus : afficher de manière lisible pour un humain la date
			System.out.println("La date entrée est : " + dateForHuman);
		} catch (DateTimeParseException dtpe) {
			// Sinon, affichage d'une erreur
			System.out.println("Impossible de transformer la date : " + dateArg + " . Cause : ");
			dtpe.printStackTrace();
			System.exit(1);
		}

	}

}
