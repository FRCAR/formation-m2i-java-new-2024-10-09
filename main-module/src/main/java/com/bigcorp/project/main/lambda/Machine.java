package com.bigcorp.project.main.lambda;

public class Machine {

	private int id;
	private String nom;
	private String reference;
	private Matiere matiere;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public void travaille(Traitement t) {
		t.traite(this.matiere);
	}

}
