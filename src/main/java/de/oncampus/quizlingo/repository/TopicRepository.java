package de.oncampus.quizlingo.repository;

import de.oncampus.quizlingo.domain.model.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long> {
    Topic findByName(String name);
}
