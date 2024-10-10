package com.bigcorp.project.main.correction;

import java.util.Optional;

public class OptionalTester {
	
	private static final String DEFAULT_STRING = "VALEUR PAR DEFAUT";
	
	private static enum Couleur {BLEU,ROUGE,VERT,JAUNE,ORANGE,VIOLET};

	public static void main(String args[]) {
		
		
		String monObjet = "Salut";
		
		//Création d'un Optional permettant la valeur null (l'optional serait vide)
		Optional<String> optionalString = Optional.ofNullable(monObjet);
		
		//Récupération avec get (lancerait une exception si la valeur était vide)
		System.out.println(optionalString.get());
		
		//Utilise une lambda pour exécuter une méthode si l'optional n'est pas vide
		optionalString.ifPresent(System.out::println);
		
		//Utilise orElse pour récupérer une valeur par défaut
		String recoveredString = optionalString.orElse(DEFAULT_STRING);
		
		OptionalTester optionalTester = new OptionalTester();
		
		//Utilisation de isPresent et get avec la méthode sur un optional qui n'est pas vide
		Optional<Couleur> rougeJaune = optionalTester.melange(Couleur.ROUGE, Couleur.JAUNE);
		if(rougeJaune.isPresent()) {
			System.out.println("Le mélange de ROUGE et JAUNE est : " + rougeJaune.get());
		}
		
		//Utilisation de isPresent et get avec la méthode sur un optional qui est vide
		Optional<Couleur> rougeVert = optionalTester.melange(Couleur.ROUGE, Couleur.VERT);
		if(rougeVert.isPresent()) {
			System.out.println("Le mélange de ROUGE et VERT est : " + rougeVert.get());
		}else {
			System.out.println("Le mélange de ROUGE et VERT ne donne rien ");
		}

		//Utilisation de orElse
		System.out.println("Le mélange de JAUNE et VERT donne " 
				+ optionalTester.melange(Couleur.JAUNE, Couleur.VERT).orElse(null));
		
		//Utilisation de ifPresent avec une lambda
		optionalTester.melange(Couleur.JAUNE, Couleur.BLEU).ifPresent(
				c -> System.out.println("Le mélange de JAUNE ET BLEU donne : "+ c));
		
		//Utilisation de orElseGet avec une lambda
		Couleur couleur1 = Couleur.VERT;
		Couleur couleur2 = Couleur.BLEU;
		Couleur vertBleu = optionalTester.melange(couleur1, couleur2).orElseGet(() -> couleur1 );
		System.out.println("Le mélange de VERT et BLEU donne " + vertBleu);
	}
	
	public Optional<Couleur> melange(Couleur couleur1, Couleur couleur2){
		if(couleur1 == Couleur.ROUGE && couleur2 == Couleur.JAUNE) {
			return Optional.of(Couleur.ORANGE);
		}
		if(couleur1 == Couleur.JAUNE && couleur2 == Couleur.BLEU) {
			return Optional.of(Couleur.VERT);
		}
		return Optional.empty();
	}

}
