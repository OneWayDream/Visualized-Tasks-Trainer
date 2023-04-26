package ru.itis.visualtasks.tasksserver.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.visualtasks.tasksserver.dto.NewTaskInfo;
import ru.itis.visualtasks.tasksserver.models.TaskArchive;
import ru.itis.visualtasks.tasksserver.models.TaskInfo;

import java.util.List;

public interface TasksService {

    List<TaskInfo> getTasksInfos();
    void saveTask(MultipartFile file, NewTaskInfo newTaskInfo);
    TaskArchive getTaskArchiveById(String id);
    TaskArchive getTaskArchiveByTaskId(Long taskId);

}
