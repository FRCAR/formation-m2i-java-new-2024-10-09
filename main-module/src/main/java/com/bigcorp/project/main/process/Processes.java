package com.bigcorp.project.main.process;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

/**
 * Classe de gestion des processus
 */
public class Processes {

	public static void main(String[] args) {
		// Itère sur tous les processes
		for (ProcessHandle p : ProcessHandle.allProcesses().toList()) {
			afficheProcess(p);
			if (p.info().command().orElse("").endsWith("jshell.exe")) {
				//Détruit un process
				p.destroy();
			}
		}

		try {
			System.out.println("Tentative de créer un process java");
			// Crée un nouveau process et affichage de la sortie du process dans un fichier
			ProcessBuilder processBuilder = new ProcessBuilder("java", "--version");
			processBuilder.redirectOutput(Redirect.appendTo(new File("java-version.txt")));
			//lance le process
			Process p = processBuilder.start();
			afficheProcess(p.toHandle());
			//L'arrête
			p.destroy();
		} catch (IOException e) {
			System.out.println("Impossible de créer la commande");
		}
	}

	/**
	 * Affiche les informations du process p
	 * @param p
	 */
	private static void afficheProcess(ProcessHandle p) {
		System.out.println(String.format("Le process avec l'id %1$s a été lancé avec la commmande %2$s", p.pid(), p
				.info().command().orElse("pas de commande")));
	}

}
