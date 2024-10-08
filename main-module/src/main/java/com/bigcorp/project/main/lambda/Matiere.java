package com.bigcorp.project.main.lambda;

public class Matiere {

	private final String nom;
	/** Poids en kg */
	private final int poids;
	/** Prix en centimes d'euros */
	private final int prix;

	public Matiere(String nom, int poids, int prix) {
		this.nom = nom;
		this.poids = poids;
		this.prix = prix;
	}

	public String getNom() {
		return nom;
	}

	public int getPoids() {
		return poids;
	}

	public int getPrix() {
		return prix;
	}
	
	public void afficheNom() {
		System.out.println(nom);;
	}

	@Override
	public String toString() {
		return "Matiere [nom=" + nom + ", poids=" + poids + ", prix=" + prix + "]";
	}

}
