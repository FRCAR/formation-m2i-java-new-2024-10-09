package com.bigcorp.project.main.nestmate;

import java.lang.reflect.Field;

//Le code ci-dessous ne fonctionne pas en Java 10, mais fonctionne en Java 11
public class NestMate {

	private static int testingNumber = 17;

	public static class NestedInnerTest {
		public static void test() throws Exception {
			Field value = NestMate.class.getDeclaredField("testingNumber");
			value.setInt(null, 12);
		}
	}

	public static void main(String[] args) throws Exception {
		NestedInnerTest.test();
		System.out.println(NestMate.testingNumber);
	}

}
