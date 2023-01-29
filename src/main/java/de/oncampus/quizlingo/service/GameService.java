package de.oncampus.quizlingo.service;

import de.oncampus.quizlingo.domain.dto.GameDTO;

public interface GameService {

    GameDTO getGameById(long id);

    GameDTO saveGame(GameDTO gameDTO);
}
