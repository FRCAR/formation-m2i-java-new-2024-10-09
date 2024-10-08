package com.bigcorp.project.main.http2;

// Attention, ces imports nécessitent un require 
// requires java.net.http;
// dans module-info.java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Http2HttpClient {

	public static void main(String[] args) {
		try {
			// Creation du HTTP Client
			HttpClient httpClient = HttpClient.newHttpClient();

			// Création de l'HttpRequest GET www.google.fr
			HttpRequest httpRequest = HttpRequest
					.newBuilder()
					.uri(new URI("https://www.google.fr"))
					.GET()
					.build();

			// Travail en mode asynchrone avec un CompletableFuture.
			// La requête est émise, mais le thread peut continuer à travailler
			CompletableFuture<String> cf = httpClient.sendAsync(
					httpRequest,
					HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);

			// On revient en mode synchrone (ici le thread peut être bloqué)
			System.out.println(cf.get());
		} catch (Exception e) {
			System.out.println("Exception" + e);
		}
	}
}
