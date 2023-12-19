package com.ttps2023.CuentasClaras.filter;

public class Credentials {

    private String token;
    private int expirationInSeconds;
    private String username;

    public Credentials(String token, int expirationInSeconds, String username) {
        this.token = token;
        this.expirationInSeconds = expirationInSeconds;
        this.username = username;
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

