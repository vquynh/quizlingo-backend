package de.oncampus.quizlingo.repository;

import de.oncampus.quizlingo.domain.model.QuizUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<QuizUser, Integer> {
	
	QuizUser findByUserName(String username);

	boolean existsByUserName(String username);
}