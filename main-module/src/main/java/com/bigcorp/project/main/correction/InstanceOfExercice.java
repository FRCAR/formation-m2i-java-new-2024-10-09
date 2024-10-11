package com.bigcorp.project.main.correction;

public class InstanceOfExercice {
	
	
	public static void main(String[] args) {
		Auto auto = new Auto();
		Velo velo = new Velo();
		
		faitRoulerAvecNouvelInstanceOf(auto);
		faitRoulerAvecNouvelInstanceOf(velo);
		faitRoulerAvecAncienInstanceOf(auto);
		faitRoulerAvecAncienInstanceOf(velo);
	}
	
	public static void faitRoulerAvecNouvelInstanceOf(Vehicule vehicule) {
		if(vehicule instanceof Auto a) {
			a.rouleSurAutoroute();
		}
		if(vehicule instanceof Velo v) {
			v.rouleSurChemin();
		}
	}
	
	public static void faitRoulerAvecAncienInstanceOf(Vehicule vehicule) {
		if(vehicule instanceof Auto) {
			Auto a  = (Auto) vehicule;
			a.rouleSurAutoroute();
		}
		if(vehicule instanceof Velo) {
			Velo v  = (Velo) vehicule;
			v.rouleSurChemin();
		}
	}

	public static class Vehicule {

	}

	public static class Auto extends Vehicule {
		public void rouleSurAutoroute() {
			System.out.println("Je vais très vite");
		}
	}

	public static class Velo extends Vehicule {
		public void rouleSurChemin() {
			System.out.println("Je roule sur le chemin");
		}

	}

}
