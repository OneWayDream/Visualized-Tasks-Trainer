package ru.itis.visualtasks.backendserver.services;

import org.springframework.data.domain.Page;
import ru.itis.visualtasks.backendserver.dto.search.SearchAttributes;
import ru.itis.visualtasks.backendserver.models.TaskInfo;

public interface TaskInfoService extends CrudService<TaskInfo, Long> {

    Page<TaskInfo> search(SearchAttributes searchAttributes);

}
