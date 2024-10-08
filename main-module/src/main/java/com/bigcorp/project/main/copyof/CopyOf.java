package com.bigcorp.project.main.copyof;

import java.util.List;

public class CopyOf {

	public static void main(String[] args) {

		// Renvoie une List, Map or Set non modifiable, qui contient les éléments
		// de l'objet passé en paramètre, en conservant leur ordre (si cela a du sens).
		var liste = List.of("3", "4", "5");
		List<String> copie = List.copyOf(liste);
	}

}
