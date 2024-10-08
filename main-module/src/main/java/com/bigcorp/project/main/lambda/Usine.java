package com.bigcorp.project.main.lambda;

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
	}

}
