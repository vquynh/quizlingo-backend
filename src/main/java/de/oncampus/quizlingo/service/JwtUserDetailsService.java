package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.dto.UserDto;
import de.oncampus.quizlingo.domain.model.user.User;
import de.oncampus.quizlingo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPasswordHash(),
				new ArrayList<>());
	}
	
	public User save(UserDto user) {
		User newUser = new User();
		newUser.setUserName(user.getUsername());
		newUser.setName(user.getName());
		newUser.setGames(new ArrayList<>());
		newUser.setImageURL(user.getImageURL());
		newUser.setPasswordHash(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}
}