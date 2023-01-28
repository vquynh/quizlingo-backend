package de.oncampus.quizlingo.repository;

import de.oncampus.quizlingo.domain.model.Question;
import de.oncampus.quizlingo.domain.model.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findQuestionsByTopic(Topic topic);

    Question findQuestionById(long id);
}
