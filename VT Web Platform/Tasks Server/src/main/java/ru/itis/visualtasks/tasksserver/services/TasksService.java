package ru.itis.visualtasks.tasksserver.services;

import ru.itis.visualtasks.tasksserver.entities.ArchiveInfo;
import ru.itis.visualtasks.tasksserver.entities.NewTaskInfo;
import ru.itis.visualtasks.tasksserver.models.TaskArchive;
import ru.itis.visualtasks.tasksserver.models.TaskInfo;

import java.util.List;

public interface TasksService {

    List<TaskInfo> getTasksInfos();
    void saveTask(byte[] content, NewTaskInfo newTaskInfo, ArchiveInfo archiveInfo);
    TaskArchive getTaskArchiveById(String id);
    TaskArchive getTaskArchiveByTaskId(Long taskId);

}
