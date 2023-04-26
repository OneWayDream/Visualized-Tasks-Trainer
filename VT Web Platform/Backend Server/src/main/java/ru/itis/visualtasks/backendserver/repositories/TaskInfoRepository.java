package ru.itis.visualtasks.backendserver.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.visualtasks.backendserver.dto.search.Language;
import ru.itis.visualtasks.backendserver.models.TaskInfo;

import java.util.List;

public interface TaskInfoRepository extends JpaRepository<TaskInfo, Long> {

    Page<TaskInfo> findAllByLanguageIn(List<Language> languages, Pageable pageable);

    Page<TaskInfo> findAllByTaskNameAndLanguageIn(String taskName, List<Language> languages, Pageable pageable);

    Page<TaskInfo> findAllByAuthorNameAndLanguageIn(String authorName, List<Language> languages, Pageable pageable);

    Page<TaskInfo> findAllByTaskNameAndAuthorNameAndLanguageIn(String taskName, String authorName,
                                                               List<Language> languages, Pageable pageable);

}
