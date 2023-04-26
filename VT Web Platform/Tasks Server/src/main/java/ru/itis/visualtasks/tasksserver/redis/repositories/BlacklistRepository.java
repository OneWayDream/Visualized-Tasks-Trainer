package ru.itis.visualtasks.tasksserver.redis.repositories;

public interface BlacklistRepository {

    void save(String token);

    boolean exists(String token);

}
