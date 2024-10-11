package com.bigcorp.project.main.correction;

import java.time.LocalDate;

public record RecordExercice(Integer identifiant, String nom, String prenom, LocalDate dateNaissance) {

	public void faitUnTruc() {
		System.out.println(this.identifiant * 42);
	}
	
	public static void main(String[] args) {
		RecordExercice jeanDubois = new RecordExercice(1, "Jean", "Dubois", LocalDate.of(2000, 3, 1));
		System.out.println(jeanDubois.toString());
		jeanDubois.faitUnTruc();
	}
	
}