package ru.itis.visualtasks.tasksserver.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "task_info")
public class TaskInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "author_name")
    private String authorName;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Column(name = "addition_date")
    private LocalDateTime additionDate;

}
