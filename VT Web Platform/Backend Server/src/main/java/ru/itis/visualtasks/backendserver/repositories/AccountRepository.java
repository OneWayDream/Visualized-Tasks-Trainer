package ru.itis.visualtasks.backendserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.visualtasks.backendserver.models.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByLogin(String login);
    Optional<Account> findByMail(String mail);

}
