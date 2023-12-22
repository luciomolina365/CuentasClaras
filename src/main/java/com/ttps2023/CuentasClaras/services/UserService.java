package com.ttps2023.CuentasClaras.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.Expense;
import com.ttps2023.CuentasClaras.model.User;
import com.ttps2023.CuentasClaras.repositories.FriendRequestRepository;
import com.ttps2023.CuentasClaras.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;
    

    public UserService(UserRepository userRepository,FriendRequestRepository friendRequestRepository) {
        this.userRepository = userRepository;
        this.friendRequestRepository= friendRequestRepository;
    }
	
	public Boolean exists(Long id) {
		return userRepository.existsById(id);
	}
	
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
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
	
	@Transactional
	public List<Crew> getCrewList(Long id) {
		User user = userRepository.findById(id).orElse(null);
		
		if (user != null ) {
			return user.getCrewList();
		}
		
		return null;
	}
	
}
	
//	public void addFriendRequest(Long id,User user ){//le voy a poner string pero en duda si pasarle el user
//		
//	
//	
//	
//	
//}





