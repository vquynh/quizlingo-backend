package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.dto.GameDTO;
import de.oncampus.quizlingo.domain.model.Game;
import de.oncampus.quizlingo.domain.model.user.User;
import de.oncampus.quizlingo.domain.model.user.Player;
import de.oncampus.quizlingo.exception.InvalidNumberOfPlayers;
import de.oncampus.quizlingo.exception.PlayerNotFoundException;
import de.oncampus.quizlingo.repository.GameRepository;
import de.oncampus.quizlingo.repository.PlayerRepository;
import de.oncampus.quizlingo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GameDTO getGameById(long id) {
        return toGameDTO(gameRepository.findById(id));
    }

    @Override
    public GameDTO addGame(GameDTO gameDTO) throws PlayerNotFoundException, InvalidNumberOfPlayers {
        gameRepository.save(toGameEntity(gameDTO));
        return gameDTO;
    }

    private Player getPlayer(String username) throws PlayerNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user == null){
            throw  new PlayerNotFoundException("No player found with username: " + username);
        }
        return  playerRepository.findByUserId(user.getId());
    }

    private Game toGameEntity(GameDTO gameDTO) throws InvalidNumberOfPlayers, PlayerNotFoundException {
        Game game = new Game();
        List<Player> players = new ArrayList<>();
        List<String> playersNames = gameDTO.getPlayers();
        if(playersNames.size() != 2){
            throw new InvalidNumberOfPlayers("Number of players is not 2. Found: " + gameDTO.getPlayers().size());
        }
        Player player1 = getPlayer(playersNames.get(0));
        players.add(player1);
        playerRepository.save(player1);
        Player player2 = getPlayer(playersNames.get(1));
        players.add(player2);
        playerRepository.save(player2);
        game.setPlayers(players);
        game.setStartedAt(gameDTO.getStartTimestamp());
        return game;
    }

    private GameDTO toGameDTO(Optional<Game> game) {
        if(game.isPresent()){
            return new GameDTO();
        }
        return null;
    }
}
