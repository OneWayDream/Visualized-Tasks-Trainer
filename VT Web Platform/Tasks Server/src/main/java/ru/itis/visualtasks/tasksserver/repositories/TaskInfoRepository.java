package ru.itis.visualtasks.tasksserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.visualtasks.tasksserver.models.TaskInfo;

public interface TaskInfoRepository extends JpaRepository<TaskInfo, Long> {



}
