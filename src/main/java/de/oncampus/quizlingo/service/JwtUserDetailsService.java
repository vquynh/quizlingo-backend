package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.dto.UserDTO;
import de.oncampus.quizlingo.domain.model.QuizUser;
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
		QuizUser quizUser = userRepository.findByUserName(username);
		if (quizUser == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(quizUser.getUserName(), quizUser.getPasswordHash(),
				new ArrayList<>());
	}
	
	public QuizUser save(UserDTO user) {
		QuizUser newQuizUser = new QuizUser();
		newQuizUser.setUserName(user.getUsername());
		newQuizUser.setName(user.getName());
		newQuizUser.setImageURL(user.getImageURL());
		newQuizUser.setPasswordHash(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newQuizUser);
	}

	public void delete(String username) {
		QuizUser user = userRepository.findByUserName(username);
		userRepository.delete(user);
	}
}