package ru.itis.visualtasks.tasksserver.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.visualtasks.tasksserver.models.TaskDescriptionFile;

public interface TaskDescriptionFileRepository extends MongoRepository<TaskDescriptionFile, String> {



}
