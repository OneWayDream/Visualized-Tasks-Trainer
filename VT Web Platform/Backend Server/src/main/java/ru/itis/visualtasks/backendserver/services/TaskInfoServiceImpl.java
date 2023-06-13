package ru.itis.visualtasks.backendserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.visualtasks.backendserver.dto.search.SearchAttributes;
import ru.itis.visualtasks.backendserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.backendserver.models.TaskInfo;
import ru.itis.visualtasks.backendserver.repositories.TaskInfoRepository;
import ru.itis.visualtasks.backendserver.utils.PropertiesUtils;
import ru.itis.visualtasks.backendserver.utils.SearchManager;

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

    @Override
    public Page<TaskInfo> search(SearchAttributes searchAttributes) {
        Pageable pageable = SearchManager.preparePageable(searchAttributes);
        boolean isTaskNameSpecified = searchAttributes.getTaskName() != null;
        boolean isAuthorNameSpecified = searchAttributes.getAuthorName() != null;
        Page<TaskInfo> result;
        if (isTaskNameSpecified && isAuthorNameSpecified){
            result = repository.findAllByTaskNameAndAuthorNameAndLanguageIn(
                    searchAttributes.getTaskName(), searchAttributes.getAuthorName(),
                    searchAttributes.getLanguages(), pageable
            );
        } else if (isTaskNameSpecified){
            result = repository.findAllByTaskNameAndLanguageIn(searchAttributes.getTaskName(),
                    searchAttributes.getLanguages(), pageable);
        } else if (isAuthorNameSpecified){
            result = repository.findAllByAuthorNameAndLanguageIn(searchAttributes.getAuthorName(),
                    searchAttributes.getLanguages(), pageable);
        } else {
            result = repository.findAllByLanguageIn(searchAttributes.getLanguages(), pageable);
        }
        return result;
    }

}
