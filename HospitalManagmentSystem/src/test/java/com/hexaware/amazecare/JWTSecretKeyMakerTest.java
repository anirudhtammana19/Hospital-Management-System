package com.hexaware.amazecare;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;

public class JWTSecretKeyMakerTest {

	@Test
	public void generateSecretKey() {
		SecretKey secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
		String key=DatatypeConverter.printHexBinary(secretKey.getEncoded());
		System.err.printf("\nSecretKey = {%s}",key);
	}
}
