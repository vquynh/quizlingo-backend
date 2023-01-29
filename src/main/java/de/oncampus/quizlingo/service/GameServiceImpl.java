package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.dto.GameDTO;
import de.oncampus.quizlingo.domain.model.Game;
import de.oncampus.quizlingo.domain.model.QuizUser;
import de.oncampus.quizlingo.exception.InvalidNumberOfPlayers;
import de.oncampus.quizlingo.exception.PlayerNotFoundException;
import de.oncampus.quizlingo.repository.GameRepository;
import de.oncampus.quizlingo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
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

    private Game toGameEntity(GameDTO gameDTO) throws InvalidNumberOfPlayers, PlayerNotFoundException {
        Game game = new Game();
        List<QuizUser> quizUsers = new ArrayList<>();
        List<String> playersNames = gameDTO.getPlayers();
        if(playersNames.size() != 2){
            throw new InvalidNumberOfPlayers("Number of players is not 2. Found: " + gameDTO.getPlayers().size());
        }
        QuizUser quizUser1 = userRepository.findByUserName(playersNames.get(0));
        quizUsers.add(quizUser1);
        QuizUser quizUser2 = userRepository.findByUserName(playersNames.get(1));
        quizUsers.add(quizUser2);
        game.setUsers(quizUsers);
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
