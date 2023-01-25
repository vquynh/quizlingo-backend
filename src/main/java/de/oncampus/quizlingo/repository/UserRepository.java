package de.oncampus.quizlingo.repository;

import de.oncampus.quizlingo.domain.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUserName(String username);

	boolean existsByUserName(String username);
}