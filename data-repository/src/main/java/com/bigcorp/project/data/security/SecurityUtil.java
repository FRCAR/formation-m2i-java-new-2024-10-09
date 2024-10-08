package com.bigcorp.project.data.security;

import java.util.Random;

/**
 * Cette classe est public car elle peut servir à toute
 * autre classe du module, mais ne sera pas exportée, et donc
 * pas accessible aux modules qui dépendent de celui-ci.
 */
public class SecurityUtil {

	public boolean SecurityCheck(Long userId) {
		return new Random().nextBoolean();
	}

}
