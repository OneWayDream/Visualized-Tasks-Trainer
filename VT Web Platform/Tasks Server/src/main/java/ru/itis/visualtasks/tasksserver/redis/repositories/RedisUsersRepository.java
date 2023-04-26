package ru.itis.visualtasks.tasksserver.redis.repositories;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.itis.visualtasks.tasksserver.redis.repositories.models.RedisUser;

public interface RedisUsersRepository extends KeyValueRepository<RedisUser, String> {

}
