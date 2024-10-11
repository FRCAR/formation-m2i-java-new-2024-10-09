package com.bigcorp.project.main.correction;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjusterExercice {

	public static void main(String[] args) {
		//Trouver et afficher le dernier mardi précédant aujourd’hui.
		LocalDate lastTuesday = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.TUESDAY));
		System.out.println("Le dernier mardi est : " + lastTuesday);

		//Trouver le premier jour (de la semaine) de la quatrième année après celle actuelle.
		DayOfWeek firstDayInFourYear = LocalDate.now().plusYears(4).with(TemporalAdjusters.firstDayOfYear()).getDayOfWeek();
		// Ou ...
		// DayOfWeek firstDayInFourYear = Year.now().plusYears(4).atDay(1).getDayOfWeek();
		System.out.println("Le premier jour de la quatrième année après celle actuelle sera : " + firstDayInFourYear);
	}

}
