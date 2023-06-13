package ru.itis.visualtasks.tasksserver.repositories;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.itis.visualtasks.tasksserver.models.Language;
import ru.itis.visualtasks.tasksserver.models.TaskInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES, beanName = "dataSource",
        provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY,
        refresh = AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"classpath:schemas/task_info_schema.sql", "classpath:schemas/task_info_data.sql"})
@DisplayName("TaskInfoRepository is working when")
@TestPropertySource("classpath:test.properties")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class TaskInfoRepositoryTests {

    @Autowired
    private TaskInfoRepository taskInfoRepository;

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    @Order(1)
    public class FindAllTests {

        @Test
        public void find_all(){
            List<TaskInfo> actual = taskInfoRepository.findAll();
            List<TaskInfo> expected = expectedAllTaskInfos();
            Assertions.assertEquals(actual, expected);
        }

        public List<TaskInfo> expectedAllTaskInfos(){
            return List.of(
                    TaskInfo.builder()
                            .id(1L)
                            .authorId(1L)
                            .taskName("First Test Task")
                            .authorName("test")
                            .language(Language.JAVA)
                            .additionDate(LocalDateTime.parse("2023-04-20T13:59:37.862496"))
                            .build(),
                    TaskInfo.builder()
                            .id(2L)
                            .authorId(1L)
                            .taskName("Second Test Task")
                            .authorName("test")
                            .language(Language.PYTHON)
                            .additionDate(LocalDateTime.parse("2023-04-21T17:19:21.862496"))
                            .build(),
                    TaskInfo.builder()
                            .id(3L)
                            .authorId(1L)
                            .taskName("Task to delete")
                            .authorName("test")
                            .language(Language.PYTHON)
                            .additionDate(LocalDateTime.parse("2023-04-22T07:10:01.862496"))
                            .build()
            );
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("delete() in working when")
    @Order(2)
    public class DeleteTests {

        @Test
        public void delete_existing(){
            taskInfoRepository.deleteById(taskInfoIdToDelete());
        }


        public static Long taskInfoIdToDelete(){
            return 3L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    @Order(3)
    public class AddTests {

        @Test
        public void add(){
            TaskInfo expected = taskInfoAfterAddition();
            TaskInfo actual = taskInfoRepository.save(taskInfoToAdd());
            assertEquals(expected, actual);
        }

        public static TaskInfo taskInfoToAdd(){
            return TaskInfo.builder()
                    .authorId(1L)
                    .taskName("Added task")
                    .authorName("test")
                    .language(Language.JAVA)
                    .additionDate(LocalDateTime.parse("2023-04-19T14:00:00.000001"))
                    .build();
        }

        public static TaskInfo taskInfoAfterAddition(){
            TaskInfo taskInfo = taskInfoToAdd();
            taskInfo.setId(4L);
            return taskInfo;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    @Order(4)
    public class FindByIdTests {

        @Test
        public void find_existing_by_id(){
            TaskInfo expected = expectedExistingTaskInfo();
            assertEquals(expected, taskInfoRepository.findById(expected.getId()).orElse(null));
        }

        public TaskInfo expectedExistingTaskInfo(){
            return TaskInfo.builder()
                    .id(1L)
                    .authorId(1L)
                    .taskName("First Test Task")
                    .authorName("test")
                    .language(Language.JAVA)
                    .additionDate(LocalDateTime.parse("2023-04-20T13:59:37.862496"))
                    .build();
        }

        @Test
        public void find_not_existing_by_id(){
            assertEquals(Optional.empty(), taskInfoRepository.findById(notExistingTaskInfoId()));
        }

        public Long notExistingTaskInfoId(){
            return 100L;
        }

    }

}
