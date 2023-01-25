package de.oncampus.quizlingo.repository;

import de.oncampus.quizlingo.domain.model.user.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Player findByUserId(Long id);
}
