package com.bigcorp.project.main.correction;

/**
 * Une interface fonctionnelle : ne contient
 * qu'une méthode public abstract et peut 
 * être instanciée via une lambda.
 */
@FunctionalInterface
public interface Operation {
	
	double apply(double d1, double d2);

}
