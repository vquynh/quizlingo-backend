package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.dto.PlayerDTO;
import de.oncampus.quizlingo.domain.model.Game;
import de.oncampus.quizlingo.domain.model.user.Account;
import de.oncampus.quizlingo.domain.model.user.Player;
import de.oncampus.quizlingo.repository.AccountRepository;
import de.oncampus.quizlingo.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final AccountRepository accountRepository;
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(AccountRepository accountRepository, PlayerRepository playerRepository) {
        this.accountRepository = accountRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerDTO getPlayerByUsername(String username) {
        if(accountRepository.existsByUserName(username)){
            Account account = accountRepository.findByUserName(username);
            return toPlayerDTO(playerRepository.findByAccountId(account.getId()));
        }
        return null;
    }

    private PlayerDTO toPlayerDTO(Player player){
        String username = player.getAccount().getUserName();
        PlayerDTO playerDTO = new PlayerDTO(username);
        Collection<Game> games = player.getGames();
        int totalGames = games.size();
        playerDTO.setTotalGames(totalGames);
        if (totalGames != 0){
            playerDTO.setWinningPercentage(
                    (games.stream().filter(game -> Objects.equals(game.getWonBy().getUserName(), username)).count()
                            / games.size()) * 100);
        }

        return playerDTO;
    }
}
