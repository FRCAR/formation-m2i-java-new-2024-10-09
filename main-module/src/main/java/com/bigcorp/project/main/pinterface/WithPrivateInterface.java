package com.bigcorp.project.main.pinterface;

public interface WithPrivateInterface {

	private int calculeDouble(int i) {
		return 2 * i;
	}

	public default int faisQuelqueChose(int i) {
		return calculeDouble(i);
	}

}
