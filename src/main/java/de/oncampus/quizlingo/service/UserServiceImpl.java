package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.model.user.Player;
import de.oncampus.quizlingo.exception.UserAlreadyExistException;
import de.oncampus.quizlingo.domain.model.user.Account;
import de.oncampus.quizlingo.repository.AccountRepository;
import de.oncampus.quizlingo.domain.dto.UserDto;
import de.oncampus.quizlingo.repository.PlayerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final AccountRepository accountRepository;
    private final PlayerRepository playerRepository;

    public UserServiceImpl(AccountRepository accountRepository, PlayerRepository playerRepository) {
        this.accountRepository = accountRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDto registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (usernameExists(userDto.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that username: "
                    + userDto.getUsername());
        }
        Account account = new Account();
        account.setUserName(userDto.getUsername());
        account.setPasswordHash(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        accountRepository.save(account);
        Player player = new Player();
        player.setAccount(account);
        playerRepository.save(player);
        return userDto;
    }

    private boolean usernameExists(String username) {
        return accountRepository.findByUserName(username) != null;
    }

}
