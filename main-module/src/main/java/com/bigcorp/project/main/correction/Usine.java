package com.bigcorp.project.main.correction;

import com.bigcorp.project.main.lambda.Machine;
import com.bigcorp.project.main.lambda.Matiere;

/**
 * Classe usine contenant des machines 
 * qui traitent toutes de manière unique
 * les matériaux qu'on leur donne.
 */
public class Usine {
	
	public static void main(String[] args) {
		
		//Création de la machine
		Machine machine1 = new Machine();
		machine1.setId(1);
		machine1.setNom("Decoupeuse");
		machine1.setReference("decoup-001");
		
		//Remplissage de la machine
		machine1.setMatiere(new Matiere("Verre", 2, 100));
		
		//La machine fait son traitement
		
		//Utilisation d'une lambda "classique"
		machine1.travaille((Matiere m) -> {System.out.println("je traite le " + m);});
		
		//Utilisation d'une lambda "raccourcie"
		machine1.travaille(m -> System.out.println("je traite le " + m));
		
		//Utilisation d'une lambda sur plusieurs lignes avec une variable
		//effectivement final
		int maxPoids = 10;
		machine1.travaille((Matiere m) -> {
			if(m.getPoids() > maxPoids) {
				System.out.println("C'est trop lourd pour moi");
			}else {
				System.out.println("Contrôle de poids passé : je traite la matière : " + m.getNom());
			}
		});
		
		
		//Utilisation de références de méthodes sur System.out :
		machine1.travaille(System.out::println);
		//équivaut à : 
		machine1.travaille((Matiere m) -> {System.out.println(m);});
		
		//Utilisation de références de méthodes sur Matiere :
		machine1.travaille(Matiere::afficheNom);
		//équivaut à : 
		machine1.travaille((Matiere m) -> {m.afficheNom();});
	}

}
