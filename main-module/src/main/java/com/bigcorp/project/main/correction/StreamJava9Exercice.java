package com.bigcorp.project.main.correction;

import java.util.stream.Stream;

public class StreamJava9Exercice {

	public static void main(String[] args) {

		System.out.println("\r\nUtilisation de Stream.iterate pour générer un stream infini et takeWhile pour le stopper");
		Stream
			.iterate(3,  i -> i + 3)
			.takeWhile(e -> e <= 30)
			.forEach(System.out::println);

	}

}
