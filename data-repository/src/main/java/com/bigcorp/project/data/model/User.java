package com.bigcorp.project.data.model;

/**
 * POJO représentant un USER
 */
public class User {

	private Long id;
	private String firstName;
	private String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Met à jour this.firstName
	 * @param firstName
	 */
	public void methodePrivee(String firstName) {
		this.firstName = firstName;
	}

}
