package de.oncampus.quizlingo.controller;

import de.oncampus.quizlingo.config.JwtTokenUtil;
import de.oncampus.quizlingo.domain.dto.UserDTO;
import de.oncampus.quizlingo.domain.model.JwtRequest;
import de.oncampus.quizlingo.domain.model.JwtResponse;
import de.oncampus.quizlingo.service.JwtUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Controller for registering and authenticating a user.
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

	private final AuthenticationManager authenticationManager;

	private final JwtTokenUtil jwtTokenUtil;

	private final JwtUserDetailsService userDetailsService;

	/**
	 * Injects the required dependencies for the JwtAuthenticationController
	 *
	 * @param authenticationManager is the default AuthenticationManager from Spring
	 * @param jwtTokenUtil is the helper service for generating JWT token
	 * @param userDetailsService is the service for persisting and retrieving user details
	 */
	public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userDetailsService = userDetailsService;
	}

	/**
	 * Creates a token for the given authentication request
	 *
	 * @param  authenticationRequest contains the username and password to authenticate the user
	 * @return ResponseEntity  ok (200) with generated token if the authentication is successful
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	/**
	 * Creates a new user
	 *
	 * @param  user contains the details of the user
	 * @return ResponseEntity  ok (200) with persisted user (without password)
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	/**
	 * Deletes an existing user
	 *
	 * @param  username the username of the user to be deleted
	 * @return ResponseEntity  ok (200)
	 */
	@RequestMapping(value = "/users/{username}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		userDetailsService.delete(username);
		return ResponseEntity.ok("Deleted");
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}