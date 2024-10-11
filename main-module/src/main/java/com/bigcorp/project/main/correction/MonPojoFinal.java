package com.bigcorp.project.main.correction;

import java.time.LocalDate;

public class MonPojoFinal {

	private final Long id;

	private final String name;

	private final LocalDate creationDate;

	public MonPojoFinal(Long id, String name, LocalDate creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	@Override
	public String toString() {
		return "MonPojo [id=" + id + ", name=" + name + ", creationDate=" + creationDate + "]";
	}

}
