package ru.itis.visualtasks.tasksserver.services;

import ru.itis.visualtasks.tasksserver.models.TaskArchive;

public interface TaskArchiveService extends CrudService<TaskArchive, String> {

    TaskArchive findByTaskId(Long taskId);

}
