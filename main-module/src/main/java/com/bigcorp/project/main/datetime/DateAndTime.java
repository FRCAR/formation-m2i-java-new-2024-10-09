package com.bigcorp.project.main.datetime;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class DateAndTime {

	public static void main(String[] args) {

		// Date locale actuelle
		LocalDate now = LocalDate.now();
		// Date locale du 12 janvier 1975
		LocalDate parameterDate = LocalDate.of(1975, 1, 12);
		// Compare uniquement les années de parameterDate et now()
		Year.from(parameterDate).isAfter(Year.now());
		// Création d'un formatteur de dateTime
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
		System.out.println("Le départ aura lieu le " + LocalDateTime.of(2022, 7, 3, 10, 23).format(dateTimeFormatter));
		// affiche : Le départ aura lieu le 03/07/2022 à 10:23

		// Parsing d'une chaîne de caractère représentant une date en ISO-8601
		LocalDate date = LocalDate.parse("2029-03-28", DateTimeFormatter.ISO_DATE);

		Boolean isYearAfter2000 = now.query(t -> t.get(ChronoField.YEAR) > 2000);

		// Trouver le dernier jour d'une année
		LocalDate lastDayOfYear = LocalDate.of(2000, 1, 15).with(TemporalAdjusters.lastDayOfYear());
		System.out.println("Le dernier jour de l'année est : " + lastDayOfYear);

		LocalDateTime birthDay = LocalDateTime.of(1981, 12, 12, 10, 15);

		// Trouver le dernier mardi précédant aujourd'hui
		System.out.println(now.with(TemporalAdjusters.previous(DayOfWeek.TUESDAY)));

		// Trouver le nombre de jours entre le dernier mercredi et aujourd'hui
		LocalDate dernierMercredi = now.with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY));
		Period.between(dernierMercredi, now).getDays();

		// Trouver le nombre de secondes entre deux dateTimes
		System.out.println(Duration.between(LocalDateTime.of(2000, 1, 1, 12, 30), LocalDateTime.now()).toSeconds());

		// D'une date du calendrier grégorien à une date japonaise
		LocalDateTime occidentalDate = LocalDateTime.of(2013, 01, 20, 19, 30);
		JapaneseDate japaneseDate = JapaneseDate.from(occidentalDate);
		LocalDate.from(japaneseDate);

		DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  HH:mm");
		LocalDateTime heureDepart = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
		ZoneId zoneParis = ZoneId.of("Europe/Paris");
		ZoneId zonePorto = ZoneId.of("Europe/Lisbon");
		ZonedDateTime heureDepartZoneParis = ZonedDateTime.of(heureDepart, zoneParis);
		ZonedDateTime heureDepartZonePorto = heureDepartZoneParis.withZoneSameInstant(zonePorto);

		try {
			String out1 = heureDepartZoneParis.format(format);
			System.out.printf("DEPART :  %s (%s)%n", out1, zoneParis);
			String out2 = heureDepartZonePorto.format(format);
			System.out.printf("DEPART :  %s (%s)%n", out2, zonePorto);
		} catch (DateTimeException exc) {
			System.out.printf("%s can't be formatted!%n", heureDepartZoneParis);
			throw exc;
		}

		// Le vol dure 10 heures et 50 minutes
		ZoneId arrivingZone = ZoneId.of("Asia/Tokyo");
		ZonedDateTime arrival = heureDepartZoneParis.withZoneSameInstant(arrivingZone)
				.plusMinutes(650);

		try {
			String out2 = arrival.format(format);
			System.out.printf("ARRIVEE : %s (%s)%n", out2, arrivingZone);
		} catch (DateTimeException exc) {
			System.out.printf("%s can't be formatted!%n", arrival);
			throw exc;
		}
		System.out.println(arrival.toInstant());

		printMonths(2022);
		printMondays(2022, 11);
		System.out.println(isFriday13th(LocalDate.of(2023, 1, 13)));

	}
	

	public static void printMonths(int year) {
		LocalDate newYearsEve = LocalDate.of(year, 1, 1);
		boolean isLeapYear = newYearsEve.isLeapYear();
		for (int i = 0; i < 12; i++) {

			Month month = newYearsEve.getMonth().plus(i);
			int monthLength = month.length(isLeapYear);
			System.out.printf("Le mois %1$s a %2$s jours. \n", month.getDisplayName(TextStyle.FULL, Locale.FRENCH),
					monthLength);
		}

	}

	public static void printMondays(int year, int month) {
		LocalDate firstMonthDay = LocalDate.of(year, month, 1);

		LocalDate nextMonday = firstMonthDay.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
		System.out.printf("Le premier lundi est le %1$s.\n", nextMonday);
		while (true) {
			nextMonday = nextMonday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
			if (nextMonday.getMonth() != firstMonthDay.getMonth()) {
				break;
			}
			System.out.printf("Le lundi suivant est le %1$s.\n", nextMonday);

		}

	}

	public static boolean isFriday13th(LocalDate date) {
		return date.getDayOfMonth() == 13 && date.getDayOfWeek() == DayOfWeek.FRIDAY;
	}

}
