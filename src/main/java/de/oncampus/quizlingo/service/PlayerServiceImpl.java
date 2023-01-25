package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.dto.PlayerDTO;
import de.oncampus.quizlingo.domain.model.Game;
import de.oncampus.quizlingo.domain.model.user.User;
import de.oncampus.quizlingo.domain.model.user.Player;
import de.oncampus.quizlingo.repository.PlayerRepository;
import de.oncampus.quizlingo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(UserRepository userRepository, PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerDTO getPlayerByUsername(String username) {
        if(userRepository.existsByUserName(username)){
            User user = userRepository.findByUserName(username);
            return toPlayerDTO(playerRepository.findByUserId(user.getId()));
        }
        return null;
    }

    private PlayerDTO toPlayerDTO(Player player){
        String username = player.getUser().getUserName();
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
