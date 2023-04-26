package ru.itis.visualtasks.tasksserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.visualtasks.tasksserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.tasksserver.models.TaskDescriptionFile;
import ru.itis.visualtasks.tasksserver.repositories.TaskDescriptionFileRepository;
import ru.itis.visualtasks.tasksserver.utils.PropertiesUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskDescriptionFileServiceImpl implements TaskDescriptionFileService {

    private final TaskDescriptionFileRepository repository;

    @Override
    public List<TaskDescriptionFile> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(TaskDescriptionFile taskDescriptionFile) {
        repository.deleteById(taskDescriptionFile.getId());
    }

    @Override
    public TaskDescriptionFile add(TaskDescriptionFile taskDescriptionFile) {
        return repository.save(taskDescriptionFile);
    }

    @Override
    public TaskDescriptionFile findById(String id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(" task description file."));
    }

    @Override
    public TaskDescriptionFile update(TaskDescriptionFile taskDescriptionFile) {
        TaskDescriptionFile entity = findById(taskDescriptionFile.getId());
        PropertiesUtils.copyNonNullProperties(taskDescriptionFile, entity);
        return repository.save(entity);
    }

}
