package de.oncampus.quizlingo.controller;

import de.oncampus.quizlingo.domain.dto.GameDTO;
import de.oncampus.quizlingo.exception.InvalidNumberOfPlayers;
import de.oncampus.quizlingo.exception.PlayerNotFoundException;
import de.oncampus.quizlingo.service.GameService;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Controller for all game-related requests.
 */
@RestController
public class GameController {

    private final GameService gameService;

    /**
     * Sets the gameService for the GameController
     *
     * @param  gameService the gameService to be used
     */
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Returns the requested game
     *
     * @param  id of the requested game
     * @return GameDTO  the requested game
     */
    @GetMapping("/games/{id}")
    public GameDTO getGame(@PathVariable String id){
        return gameService.getGameById(Long.parseLong(id));
    }

    /**
     * Creates a new game and returns the created game
     *
     * @param  gameDTO  the game to be created that belongs to a certain topic and is played by given users
     * @return GameDTO  the created game
     * @throws InvalidNumberOfPlayers thrown when the number of players does not match the number of e
     * @throws PlayerNotFoundException thrown when the user given in the GameDTO object can not be found
     */
    @PostMapping("/games")
    public GameDTO createGame(@RequestBody GameDTO gameDTO) {
        return gameService.saveGame(gameDTO);
    }


}
