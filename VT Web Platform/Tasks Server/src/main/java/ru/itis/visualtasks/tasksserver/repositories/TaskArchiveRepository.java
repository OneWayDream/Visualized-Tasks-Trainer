package ru.itis.visualtasks.tasksserver.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.visualtasks.tasksserver.models.TaskArchive;

import java.util.Optional;

public interface TaskArchiveRepository extends MongoRepository<TaskArchive, String> {

    Optional<TaskArchive> findByTaskId(Long taskId);

}
