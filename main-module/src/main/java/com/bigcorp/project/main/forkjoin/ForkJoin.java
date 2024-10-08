package com.bigcorp.project.main.forkjoin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoin extends RecursiveAction {

	private static final long serialVersionUID = 1L;

	private static final int MAX_LENGTH_BY_COMPUTE = 10;

	private String[] input;
	private int[] output;
	private int startIndex;
	private int length;

	public ForkJoin(String[] input, int[] output, int startIndex, int length) {
		this.input = input;
		this.output = output;
		this.startIndex = startIndex;
		this.length = length;
	}

	@Override
	protected void compute() {

		// A chaque tour,
		// Si length < MAX_LENGTH_BY_COMPUTE, on fait le calcul directement
		if (this.length < MAX_LENGTH_BY_COMPUTE) {
			computeDirectly();
		} else {
			// sinon, on coupe nos opérations en deux, et on laisse un nouveau ForkJoin
			// faire le travail
			int halfIndex = this.length / 2;

			invokeAll(new ForkJoin(this.input, this.output, this.startIndex, halfIndex),
					new ForkJoin(this.input, this.output, this.startIndex + halfIndex,
							this.length - halfIndex));
		}

	}

	/**
	 * Méthode de calcul direct : n'opère que sur de petits tableaux dont length <
	 * MAX_LENGTH_BY_COMPUTE
	 */
	private void computeDirectly() {
		for (int i = this.startIndex; i < this.startIndex + this.length; i++) {
			try {
				int inputValue = Integer.parseInt(this.input[i]);
				this.output[i] = 5 * inputValue * inputValue * inputValue - 4 * inputValue * inputValue - 3 * inputValue
						+ 2;
			} catch (NumberFormatException e) {
				System.out.println("Attention : i vaut : " + this.input[i]);
			}
		}
	}

	/**
	 * Méthode utilitaire de lecture de fichier
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static String readFile(String filePath) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (InputStream in = new FileInputStream(filePath);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));) {

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) throws IOException {
		// Le fichier d'entiers séparés par des virgules est fourni
		String fileAsString = readFile("src/main/resources/fork-join-data.txt");
		// On le charge dans un tableau
		String[] inputValues = fileAsString.split(";");
		// On crée un tableau de résultats
		int[] outputValues = new int[inputValues.length];

		long startTimeNano = System.nanoTime();
		// Tuyauterrie pour invoquer un forkJoin dans un Pool
		ForkJoin forkJoin = new ForkJoin(inputValues, outputValues, 0, inputValues.length);
		ForkJoinPool pool = new ForkJoinPool(1);
		pool.invoke(forkJoin);
		long endTimeNano = System.nanoTime();
		System.out.println("Temps écoulé : " + (endTimeNano - startTimeNano) / 1_000_000 + " ms");

		startTimeNano = System.nanoTime();
		forkJoin = new ForkJoin(inputValues, outputValues, 0, inputValues.length);
		pool = new ForkJoinPool(2);
		pool.invoke(forkJoin);
		endTimeNano = System.nanoTime();
		System.out.println("Temps écoulé : " + (endTimeNano - startTimeNano) / 1_000_000 + " ms");

		startTimeNano = System.nanoTime();
		forkJoin = new ForkJoin(inputValues, outputValues, 0, inputValues.length);
		forkJoin.compute();
		endTimeNano = System.nanoTime();
		System.out.println("Temps écoulé : " + (endTimeNano - startTimeNano) / 1_000_000 + " ms");

		// Le tableau est mis dans le fichier.
		System.out.println("Input value : " + inputValues[4] + " output value : " + outputValues[4]);

	}

}
