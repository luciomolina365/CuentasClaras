package com.ttps2023.CuentasClaras.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	public Boolean exists(Long id) {
		return userRepository.existsById(id);
	}
	
	public void create(User user) {
		userRepository.save(user);
	}
	
	public Optional<User> authenticateWithUsernameAndPass(String username, String pass) {
		return userRepository.findByUsernameAndPass(username, pass);
	}
	
	public Optional<User> getById(Long id) {
		return userRepository.findById(id);
	}
}
