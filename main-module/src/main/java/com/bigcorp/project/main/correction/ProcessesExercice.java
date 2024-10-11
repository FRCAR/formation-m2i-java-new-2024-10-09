package com.bigcorp.project.main.correction;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Classe de gestion des processus
 */
public class ProcessesExercice {

	public static void main(String[] args) {
		// trouver le premier processus s’appelant jshell (ou jshell.exe)
		Optional<ProcessHandle> premierProcessJshell = ProcessHandle.allProcesses()
				.filter(p -> p.info().command().orElse("").endsWith("jshell.exe")).findFirst();

		if (premierProcessJshell.isEmpty()) {
			System.out.println("Aucun process Jshell trouvé.");
		} else {
			// Sinon afficher toutes les informations possibles, récupérables
			// grâce aux méthodes de ProcessHandle
			ProcessHandle p = premierProcessJshell.get();
			System.out.println(String.format(
					"Le process avec l'id %1$s a été lancé avec :"
							+ "\r\n\t la commmande %2$s"
							+ "\r\n\t a été lancé avec les arguments %3$s"
							+ "\r\n\t a été lancé avec la ligne de commande %4$s"
							+ "\r\n\t a été lancé à %5$s"
							+ "\r\n\t a consommé %6$s ns"
							+ "\r\n\t a été lancé par %7$s",
					p.pid(),
					p.info().command().orElse("pas de commande"),
					p.info().arguments(),
					p.info().commandLine().orElse("pas de ligne de commande"),
					p.info().startInstant().orElse(null),
					p.info().totalCpuDuration().orElse(Duration.ZERO).getNano(),
					p.info().user().orElse("pas d'utilisateur")));

			p.destroy();
		}

		// Lancer via Java deux explorer windows
		// ... et en tuer un au bout de 10 secondes (ou immédiatement).
		try {
			System.out.println("Lancement de deux explorers");
			ProcessBuilder processBuilder = new ProcessBuilder("explorer");
			// lance le process
			Process p1 = processBuilder.start();
			Process p2 = processBuilder.start();
			// L'arrête
			System.out.println(p1.isAlive());
			TimeUnit.SECONDS.sleep(2);
		} catch (IOException | InterruptedException e) {
			System.out.println("Impossible de créer la commande");
		}

	}

}
