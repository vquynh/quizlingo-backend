package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.dto.GameDTO;
import de.oncampus.quizlingo.exception.InvalidNumberOfPlayers;
import de.oncampus.quizlingo.exception.PlayerNotFoundException;

public interface GameService {

    GameDTO getGameById(long id);

    GameDTO addGame(GameDTO gameDTO) throws PlayerNotFoundException, InvalidNumberOfPlayers;
}
