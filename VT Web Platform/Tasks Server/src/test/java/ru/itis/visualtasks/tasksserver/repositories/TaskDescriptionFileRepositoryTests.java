package ru.itis.visualtasks.tasksserver.repositories;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.tasksserver.models.TaskDescriptionFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@TestPropertySource("classpath:test.properties")
@DisplayName("TaskDescriptionFileRepository is working when")
public class TaskDescriptionFileRepositoryTests {

    @Autowired
    private TaskDescriptionFileRepository taskDescriptionFileRepository;

    @BeforeEach
    public void setUp(){
        taskDescriptionFileRepository.deleteAll();
        taskDescriptionFileRepository.saveAll(getDefaultTaskDescriptionFiles());
    }

    private static List<TaskDescriptionFile> getDefaultTaskDescriptionFiles() {
        return List.of(
                TaskDescriptionFile.builder()
                        .id("first_id")
                        .taskId(1L)
                        .content("First file content")
                        .build(),
                TaskDescriptionFile.builder()
                        .id("second_id")
                        .taskId(2L)
                        .content("Second file content")
                        .build()
        );
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    public class FindAllTests {

        @Test
        public void find_all(){
            List<TaskDescriptionFile> actual = taskDescriptionFileRepository.findAll();
            assertEquals(getDefaultTaskDescriptionFiles(), actual);
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("delete() in working when")
    public class DeleteTests {

        @Test
        public void delete_existing(){
            taskDescriptionFileRepository.deleteById(taskArchiveToDelete().getId());
            List<TaskDescriptionFile> expected = getExpectedTaskArchives();
            List<TaskDescriptionFile> actual = taskDescriptionFileRepository.findAll();
            assertEquals(expected, actual);
        }

        public static TaskDescriptionFile taskArchiveToDelete(){
            return TaskDescriptionFile.builder()
                    .id("second_id")
                    .taskId(2L)
                    .content("Second file content")
                    .build();
        }

        public static List<TaskDescriptionFile> getExpectedTaskArchives() {
            return List.of(
                    TaskDescriptionFile.builder()
                            .id("first_id")
                            .taskId(1L)
                            .content("First file content")
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
            TaskDescriptionFile actual = taskDescriptionFileRepository.save(taskDescriptionFileToAdd());
            assertNotNull(actual.getId());
            actual.setId(null);
            assertEquals(taskDescriptionFileToAdd(), actual);
        }


        public static TaskDescriptionFile taskDescriptionFileToAdd(){
            return TaskDescriptionFile.builder()
                    .taskId(3L)
                    .content("Third file content")
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    public class FindByIdTests {

        @Test
        public void find_existing_by_id(){
            TaskDescriptionFile expected = existingTaskDescriptionFile();
            TaskDescriptionFile actual = taskDescriptionFileRepository.findById(existingTaskDescriptionFile().getId()).orElse(null);
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_id(){
            assertEquals(Optional.empty(), taskDescriptionFileRepository.findById(notExistingId()));
        }

        public TaskDescriptionFile existingTaskDescriptionFile(){
            return taskDescriptionFileRepository.findAll().get(0);
        }

        public static String notExistingId(){
            return "not_existing_id";
        }

    }


}
