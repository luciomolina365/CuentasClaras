package com.ttps2023.CuentasClaras.DTO;

public class Credentials {

	private Long id;
	private String token;
	private int expirationInSeconds;
	private String username;

	public Credentials(String token, int expirationInSeconds, Long id, String username) {
		this.token = token;
		this.expirationInSeconds = expirationInSeconds;
		this.id = id;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpirationInSeconds() {
		return expirationInSeconds;
	}

	public void setExpirationInSeconds(int expirationInSeconds) {
		this.expirationInSeconds = expirationInSeconds;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
