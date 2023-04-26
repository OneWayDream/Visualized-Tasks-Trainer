package ru.itis.visualtasks.tasksserver.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class TaskArchive {

    @Id
    private String id;

    private Long taskId;
    private String archiveName;

    private byte[] archive;

}
