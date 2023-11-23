package com.ttps2023.CuentasClaras.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps2023.CuentasClaras.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByUsernameAndPass(String username, String pass);
	public User getReferenceByUsernameAndPass(String username, String pass);
	public User getByUsernameAndPass(String username, String pass);
}
