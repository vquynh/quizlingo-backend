package de.oncampus.quizlingo.repository;

import de.oncampus.quizlingo.domain.model.user.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    public Account findByUserName(String username);

    public boolean existsByUserName(String username);
}
