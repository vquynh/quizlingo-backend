package de.oncampus.quizlingo.repository;

import de.oncampus.quizlingo.domain.model.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {

}
