package de.oncampus.quizlingo.controller;

import de.oncampus.quizlingo.domain.dto.PlayerDTO;
import de.oncampus.quizlingo.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players/{username}")
    public PlayerDTO getPlayerByUserName(@PathVariable String username){
        return playerService.getPlayerByUsername(username);
    }


}
