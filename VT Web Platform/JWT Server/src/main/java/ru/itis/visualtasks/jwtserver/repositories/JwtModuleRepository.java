package ru.itis.visualtasks.jwtserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.visualtasks.jwtserver.models.JwtModule;

import java.util.Optional;

public interface JwtModuleRepository extends JpaRepository<JwtModule, Long> {

    Optional<JwtModule> findByLogin(String login);

}
