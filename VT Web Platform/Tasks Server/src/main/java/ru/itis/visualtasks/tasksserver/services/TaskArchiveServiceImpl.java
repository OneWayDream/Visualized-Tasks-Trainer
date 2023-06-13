package ru.itis.visualtasks.tasksserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.visualtasks.tasksserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.tasksserver.models.TaskArchive;
import ru.itis.visualtasks.tasksserver.repositories.TaskArchiveRepository;
import ru.itis.visualtasks.tasksserver.utils.PropertiesUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskArchiveServiceImpl implements TaskArchiveService {

    private final TaskArchiveRepository repository;

    @Override
    public List<TaskArchive> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(TaskArchive taskArchive) {
        repository.deleteById(taskArchive.getId());
    }

    @Override
    public TaskArchive add(TaskArchive taskArchive) {
        return repository.save(taskArchive);
    }

    @Override
    public TaskArchive findById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(" task archive."));
    }

    @Override
    public TaskArchive update(TaskArchive taskArchive) {
        TaskArchive entity = findById(taskArchive.getId());
        PropertiesUtils.copyNonNullProperties(taskArchive, entity);
        return repository.save(entity);
    }

    @Override
    public TaskArchive findByTaskId(Long taskId) {
        return repository.findByTaskId(taskId).orElseThrow(() -> new EntityNotFoundException(" task archive."));
    }
}
