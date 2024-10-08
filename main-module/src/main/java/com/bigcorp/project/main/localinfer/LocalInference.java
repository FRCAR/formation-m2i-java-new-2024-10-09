package com.bigcorp.project.main.localinfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LocalInference {

	public static void main(String[] args) {
		var compteur = 5;
		System.out.println("Le résultat de l'addition vaut " + 5 + compteur);
		var localInference = new LocalInference();
		// La ligne ci-dessous ne compile pas
		// compteur = "6";
		
		var monSet = Set.of(1,2);

		// L'inférence de type marche bien avec classe fille -> classe mère
		var monMetal = new Metal();
		monMetal = new Or();

		var monArgent = new Argent();
		// La ligne ci-dessous ne compile pas : le type par défaut est celui de la
		// classe
		// monArgent = new Or();

		// En explicitant le type parent, cela fonctionne
		Metal monMetal2 = new Argent();
		monMetal2 = new Or();

		// Ne fonctionne pas
		// var x;
		// x = 3;
		// var maVariableNull = null;

		// var maLambda = () -> {}

		// Peut fonctionner selon les règles de compilateur ...
		// var<Object> list = new ArrayList<>();
		// ... mais crée une liste d'objets ...
		// list.add(new Object());

		// Que penser de ce code ?
		Map<String, List<String>> definitions = new HashMap<>();
		definitions.put("bleu", new ArrayList<>());
		definitions.get("bleu").add("Une couleur");
		definitions.get("bleu").add("Un mode de cuisson de viande");
		definitions.get("bleu").add("Une personne inexpérimentée");

		// Sans var
		for (Map.Entry<String, List<String>> definitionsDuMot : definitions.entrySet()) {
			for (String definition : definitionsDuMot.getValue()) {
				System.out.println("Une définition de " + definitionsDuMot.getKey() + " est " + definition);
			}
		}

		// Avec var
		for (var definitionsDuMot : definitions.entrySet()) {
			for (var definition : definitionsDuMot.getValue()) {
				System.out.println("Une définition de " + definitionsDuMot.getKey() + " est " + definition);
			}
		}

		// Fonctionne depuis Java 11
		//BiFunction<Integer, Integer, Integer> addition = (var x, var y) -> x + y;
		// Ne fonctionne pas
		//BiFunction<Integer, Integer, Integer> addition2 = (var x, y) -> x + y;
		//BiFunction<Integer, Integer, Integer> addition3 = (var x, int y) -> x + y;

	}

	// Ne fonctionne pas
	// public var maMethode1() {
	// return 3;
	// }

	//	public void maMethode2(var string) {
	//		System.out.println(string);
	//	}

}

class Metal {

}

class Or extends Metal {

}

class Argent extends Metal {

}
