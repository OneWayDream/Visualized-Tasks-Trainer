package ru.itis.visualtasks.jwtserver.redis.repositories;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.visualtasks.jwtserver.redis.repositories.models.RedisUser;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {

}
