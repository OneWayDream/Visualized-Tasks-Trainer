package ru.itis.visualtasks.tasksserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.visualtasks.tasksserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.tasksserver.models.TaskInfo;
import ru.itis.visualtasks.tasksserver.repositories.TaskInfoRepository;
import ru.itis.visualtasks.tasksserver.utils.PropertiesUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskInfoServiceImpl implements TaskInfoService {

    private final TaskInfoRepository repository;

    @Override
    public List<TaskInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(TaskInfo taskInfo) {
        repository.deleteById(taskInfo.getId());
    }

    @Override
    public TaskInfo add(TaskInfo taskInfo) {
        return repository.save(taskInfo);
    }

    @Override
    public TaskInfo findById(Long aLong) {
        return repository.findById(aLong).orElseThrow(() -> new EntityNotFoundException(" task info."));
    }

    @Override
    public TaskInfo update(TaskInfo taskInfo) {
        TaskInfo entity = findById(taskInfo.getId());
        PropertiesUtils.copyNonNullProperties(taskInfo, entity);
        return repository.save(entity);
    }

}
