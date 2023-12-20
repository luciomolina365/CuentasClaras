package com.ttps2023.CuentasClaras.services;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class TokenServices {
	final static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	/**
	 * Genera el token de authorizacion para el usuario
	 *
	 * @param username Username que se guarda dentro del token
	 * @param segundos tiempo de validez del token
	 * @return token
	 */

	public String generateToken(String username, int segundos) {
		Date exp = getExpiration(new Date(), segundos);
		return Jwts.builder().setSubject(username).signWith(key).setExpiration(exp).compact();
	}

	public Date getExpiration(Date fechaInicio, int segundos) {
		long expMilisegundos = fechaInicio.getTime() + (segundos * 1000L);
		return new Date(expMilisegundos);
	}

	public static boolean validateToken(String token) {
		String prefix = "Bearer";
		try {
			if (token.startsWith(prefix)) {
				token = token.substring(prefix.length()).trim();
			}
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			System.out.println("Subject: " + claims.getSubject());
			System.out.println("Expiration: " + claims.getExpiration());
			return true;
		} catch (ExpiredJwtException exp) {
			return false;
		} catch (JwtException e) {
			return false; // Algo salio mal en la verificacion
		}
	}

}