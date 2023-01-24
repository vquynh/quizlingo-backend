package de.oncampus.quizlingo.repository;

import de.oncampus.quizlingo.domain.model.Term;
import org.springframework.data.repository.CrudRepository;

public interface TermRepository extends CrudRepository<Term, Long> {
    Term findByName(String name);
}
