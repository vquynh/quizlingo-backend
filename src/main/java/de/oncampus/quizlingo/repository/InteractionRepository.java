package de.oncampus.quizlingo.repository;

import de.oncampus.quizlingo.domain.model.Interaction;
import org.springframework.data.repository.CrudRepository;

public interface InteractionRepository extends CrudRepository<Interaction, Long> {

    Interaction findInteractionByGameIdAndUserAndQuestionId(long gameId, String username, long questionId);
}
