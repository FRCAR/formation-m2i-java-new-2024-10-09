package com.bigcorp.project.main.correction;

import java.time.LocalDate;

/**
 * 
 */
public record MonRecord(Long id, String name, LocalDate creationDate) {
	
	public int computeAge() {
		System.out.println(this.id);
		return LocalDate.now().compareTo(creationDate);
	}
	
}