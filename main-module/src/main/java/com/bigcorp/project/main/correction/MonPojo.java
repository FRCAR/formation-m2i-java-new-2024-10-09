package com.bigcorp.project.main.correction;

import java.time.LocalDate;

public class MonPojo {

	private Long id;

	private String name;

	private LocalDate creationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "MonPojo [id=" + id + ", name=" + name + ", creationDate=" + creationDate + "]";
	}
	
}
