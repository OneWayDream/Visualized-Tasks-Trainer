package ru.itis.visualtasks.tasksserver.repositories;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.tasksserver.models.TaskArchive;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@TestPropertySource("classpath:test.properties")
@DisplayName("TaskArchiveRepository is working when")
class TaskArchiveRepositoryTests {

    @Autowired
    private TaskArchiveRepository taskArchiveRepository;

    @BeforeEach
    public void setUp(){
        taskArchiveRepository.deleteAll();
        taskArchiveRepository.saveAll(getDefaultTaskArchives());
    }

    private static List<TaskArchive> getDefaultTaskArchives() {
        return List.of(
                TaskArchive.builder()
                        .id("first_id")
                        .taskId(1L)
                        .archiveName("First Task")
                        .archive(new byte[]{1, 2, 3})
                        .build(),
                TaskArchive.builder()
                        .id("second_id")
                        .taskId(2L)
                        .archiveName("Second Task")
                        .archive(new byte[]{4, 5, 6})
                        .build()
        );
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    public class FindAllTests {

        @Test
        public void find_all(){
            List<TaskArchive> actual = taskArchiveRepository.findAll();
            assertEquals(getDefaultTaskArchives(), actual);
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("delete() in working when")
    public class DeleteTests {

        @Test
        public void delete_existing(){
            taskArchiveRepository.deleteById(taskArchiveToDelete().getId());
            List<TaskArchive> expected = getExpectedTaskArchives();
            List<TaskArchive> actual = taskArchiveRepository.findAll();
            assertEquals(expected, actual);
        }

        public static TaskArchive taskArchiveToDelete(){
            return TaskArchive.builder()
                    .id("second_id")
                    .taskId(2L)
                    .archiveName("Second Task")
                    .archive(new byte[]{4, 5, 6})
                    .build();
        }

        public static List<TaskArchive> getExpectedTaskArchives() {
            return List.of(
                    TaskArchive.builder()
                            .id("first_id")
                            .taskId(1L)
                            .archiveName("First Task")
                            .archive(new byte[]{1, 2, 3})
                            .build()
            );
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    public class AddTests {

        @Test
        public void add(){
            TaskArchive actual = taskArchiveRepository.save(taskArchiveToAdd());
            assertNotNull(actual.getId());
            actual.setId(null);
            assertEquals(taskArchiveToAdd(), actual);
        }


        public static TaskArchive taskArchiveToAdd(){
            return TaskArchive.builder()
                    .taskId(1L)
                    .archiveName("Test Task")
                    .archive(new byte[]{1, 2, 3})
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    public class FindByIdTests {

        @Test
        public void find_existing_by_id(){
            TaskArchive expected = existingTaskArchive();
            TaskArchive actual = taskArchiveRepository.findById(existingTaskArchive().getId()).orElse(null);
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_id(){
            assertEquals(Optional.empty(), taskArchiveRepository.findById(notExistingId()));
        }

        public TaskArchive existingTaskArchive(){
            return taskArchiveRepository.findAll().get(0);
        }

        public static String notExistingId(){
            return "not_existing_id";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByTaskId() in working when")
    public class FindByTaskIdTests {

        @Test
        public void find_existing_by_task_id(){
            TaskArchive expected = existingTaskArchive();
            TaskArchive actual = taskArchiveRepository.findByTaskId(existingTaskArchive().getTaskId()).orElse(null);
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_task_id(){
            assertEquals(Optional.empty(), taskArchiveRepository.findByTaskId(notExistingTaskId()));
        }

        public static TaskArchive existingTaskArchive(){
            return getDefaultTaskArchives().get(0);
        }

        public static Long notExistingTaskId(){
            return 0L;
        }

    }

}
