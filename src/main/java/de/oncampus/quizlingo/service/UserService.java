package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.exception.UserAlreadyExistException;
import de.oncampus.quizlingo.domain.dto.UserDto;

public interface UserService {

    UserDto registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException, UserAlreadyExistException;
}
