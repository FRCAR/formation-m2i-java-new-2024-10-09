package com.bigcorp.project.main.correction;

import java.util.function.Predicate;

public class StringExercice {
	
	public static void main(String[] args) {
		//Créer une chaîne de caractères assez longue, contenant des caractères blancs, des retours chariot ...
		String superString = "Ma ligne commence ainsi    \r\n elle contient des retours chariots \r\n      et des caractères blancs\r\n";

		//La dupliquer 3 fois.
		String superStringFois3 = superString.repeat(3);
		
		//Utiliser les nouvelles méthodes de String pour en extraire toutes les lignes,
		//sans caractère blanc au début (ou à la fin). 
		//Affichez toutes les lignes 
		superStringFois3.lines().map(String::strip).forEach(System.out::println);
		
	}
	

}
