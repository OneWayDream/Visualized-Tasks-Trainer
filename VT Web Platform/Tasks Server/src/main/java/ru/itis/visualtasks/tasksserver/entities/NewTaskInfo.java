package ru.itis.visualtasks.tasksserver.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.visualtasks.tasksserver.models.Language;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTaskInfo {

    private Long authorId;
    private String authorName;
    private String taskName;
    private Language language;


}
