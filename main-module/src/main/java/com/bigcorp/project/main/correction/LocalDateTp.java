package com.bigcorp.project.main.correction;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTp {

	public static void main(String[] args) {
		// Afficher tous les mois d’une année et leur durée.
		int year = 2000;
		System.out.println("\r\nAffichage de tous les mois de l'année : %1$s".formatted(year));
		for (int month = 1; month <= 12; month++) {
			YearMonth yearMonth = YearMonth.of(year, month);
			System.out.println("La durée de %1$s est de %2$s jours".formatted(yearMonth, yearMonth.lengthOfMonth()));
		}

		//Afficher tous les lundis d’un mois donné et d’une année donnée
		Month month = Month.JUNE;
		int yearValue = 2023;
		System.out.println("\r\nAffichage de tous les lundis du mois : %1$s/%2$s".formatted(month, yearValue));
		LocalDate localDate = LocalDate.of(yearValue, month, 1);
		LocalDate nextMonday = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
		while (nextMonday.getMonth() == month) {
			System.out.println(nextMonday);
			nextMonday = nextMonday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		}
		
		
	}

}
