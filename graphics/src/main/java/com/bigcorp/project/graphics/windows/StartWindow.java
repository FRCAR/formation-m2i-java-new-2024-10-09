package com.bigcorp.project.graphics.windows;

import com.bigcorp.project.data.model.User;
import com.bigcorp.project.data.repository.UserRepositoryImpl;

/**
 * Simule une fenêtre de démarrage. Nécessite
 * UserRepository.
 */
public class StartWindow {

	private UserRepositoryImpl userRepository = new UserRepositoryImpl();

	public User displayHelloMessage() {
		User currentlyLoggedUser = userRepository.getCurrentlyLoggedUser();
		System.out.println("Hello, " + currentlyLoggedUser.getFirstName());
		return currentlyLoggedUser;
	}

	public static void main(String[] args) throws Exception {
		StartWindow startWindow = new StartWindow();
		startWindow.displayHelloMessage();
		
		//Utilisation de la réflexion
//		User user = new User();
//		Method methodePrivee = User.class.getDeclaredMethod("methodePrivee", String.class);
//		methodePrivee.setAccessible(true);
//		methodePrivee.invoke(user, "coucou");
//		System.out.println(user.getFirstName());
		
		//Utilisation des services
//		AddressService addressService = ServiceLoader
//				.load(AddressService.class)
//				.findFirst()
//				.orElseThrow();
//		System.out.println(addressService.getPostCode("43434"));
	}

}
