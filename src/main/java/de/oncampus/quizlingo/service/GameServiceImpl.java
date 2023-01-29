package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.dto.GameDTO;
import de.oncampus.quizlingo.domain.model.Game;
import de.oncampus.quizlingo.domain.model.QuizUser;
import de.oncampus.quizlingo.repository.GameRepository;
import de.oncampus.quizlingo.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public GameDTO getGameById(long id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isEmpty()){
            return null;
        }
        return toGameDTO(optionalGame.get());
    }

    @Override
    public GameDTO saveGame(GameDTO gameDTO) {
        Game game = gameRepository.save(toGameEntity(gameDTO));
        return toGameDTO(game);
    }

    private Game toGameEntity(GameDTO gameDTO){
        Game game = new Game();
        List<String> playersNames = gameDTO.getPlayers();
        game.setQuizUsers(playersNames);
        game.setStartedAt(gameDTO.getStartTimestamp());
        return game;
    }

    private GameDTO toGameDTO(Game game) {
            GameDTO gameDTO = new GameDTO();
            gameDTO.setId(game.getId());
            gameDTO.setPlayers(game.getQuizUsers() == null ? null : new ArrayList<>(game.getQuizUsers()));
            gameDTO.setSessions(game.getSessions() == null ? null : new ArrayList<>(game.getSessions()));
            gameDTO.setStartTimestamp(game.getStartedAt());
            return gameDTO;
    }
}
