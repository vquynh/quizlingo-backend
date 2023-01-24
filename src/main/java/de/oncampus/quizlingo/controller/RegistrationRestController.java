package de.oncampus.quizlingo.controller;

import de.oncampus.quizlingo.exception.UserAlreadyExistException;
import de.oncampus.quizlingo.service.UserService;
import de.oncampus.quizlingo.domain.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegistrationRestController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    public RegistrationRestController(UserService userService) {
        super();
        this.userService = userService;
    }

    // Registration
    @PostMapping("/user/registration")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void registerUserAccount(@RequestBody @Valid final UserDto userDto) throws UserAlreadyExistException {
        LOGGER.debug("Registering user account with information: {}", userDto);
        userService.registerNewUserAccount(userDto);
    }

}
