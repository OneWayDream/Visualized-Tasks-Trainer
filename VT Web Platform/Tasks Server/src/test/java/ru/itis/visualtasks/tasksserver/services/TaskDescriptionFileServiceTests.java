package ru.itis.visualtasks.tasksserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.tasksserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.tasksserver.models.TaskDescriptionFile;
import ru.itis.visualtasks.tasksserver.repositories.TaskDescriptionFileRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest()
@TestPropertySource("classpath:test.properties")
@DisplayName("TaskDescriptionFileService is working when")
public class TaskDescriptionFileServiceTests {

    @Autowired
    private TaskDescriptionFileService taskDescriptionFileService;

    @MockBean
    private TaskDescriptionFileRepository taskDescriptionFileRepository;

    @BeforeEach
    public void setUp() {
        when(taskDescriptionFileRepository.findAll()).thenReturn(
                TaskDescriptionFileServiceTests.FindAllTests.expectedAllDescriptionFiles()
        );
        doNothing().when(taskDescriptionFileRepository).delete(
                TaskDescriptionFileServiceTests.DeleteTests.taskDescriptionFileToDelete());
        when(taskDescriptionFileRepository.save(
                TaskDescriptionFileServiceTests.AddTests.taskDescriptionFileToAdd())
        ).thenReturn(TaskDescriptionFileServiceTests.AddTests.taskDescriptionFileAddResponse());
        when(taskDescriptionFileRepository.findById(
                TaskDescriptionFileServiceTests.FindByIdTests.existingTaskDescriptionFile().getId())
        ).thenReturn(
                Optional.ofNullable(TaskDescriptionFileServiceTests.FindByIdTests.existingTaskDescriptionFile())
        );
        when(taskDescriptionFileRepository.findById(TaskDescriptionFileServiceTests.FindByIdTests.notExistingId()))
                .thenThrow(EntityNotFoundException.class);
        when(taskDescriptionFileRepository.save(
                TaskDescriptionFileServiceTests.UpdateTests.updatedTaskDescriptionFile())
        ).thenReturn(
                TaskDescriptionFileServiceTests.UpdateTests.updatedTaskDescriptionFile()
        );
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    public class FindAllTests {

        @Test
        public void find_all(){
            List<TaskDescriptionFile> actual = taskDescriptionFileService.findAll();
            assertEquals(expectedAllDescriptionFiles(), actual);
        }

        public static List<TaskDescriptionFile> expectedAllDescriptionFiles(){
            return List.of(
                    TaskDescriptionFile.builder()
                            .id("first_id")
                            .taskId(1L)
                            .content("First content")
                            .build(),
                    TaskDescriptionFile.builder()
                            .id("second_id")
                            .taskId(2L)
                            .content("Second content")
                            .build()
            );
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("delete() in working when")
    public class DeleteTests {

        @Test
        public void delete_existing(){
            taskDescriptionFileService.delete(taskDescriptionFileToDelete());
        }


        public static TaskDescriptionFile taskDescriptionFileToDelete(){
            return TaskDescriptionFile.builder()
                    .id("existing_entry_id")
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    public class AddTests {

        @Test
        public void add(){
            TaskDescriptionFile expected = taskDescriptionFileAddResponse();
            TaskDescriptionFile actual = taskDescriptionFileService.add(taskDescriptionFileToAdd());
            assertEquals(expected, actual);
        }

        public static TaskDescriptionFile taskDescriptionFileToAdd(){
            return TaskDescriptionFile.builder()
                    .taskId(3L)
                    .content("Added description file content")
                    .build();
        }

        public static TaskDescriptionFile taskDescriptionFileAddResponse(){
            return TaskDescriptionFile.builder()
                    .id("task_id")
                    .taskId(3L)
                    .content("Added description file content")
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
            TaskDescriptionFile actual = taskDescriptionFileService.findById(existingTaskDescriptionFile().getId());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_id(){
            assertThrows(EntityNotFoundException.class, () -> taskDescriptionFileService.findById(notExistingId()));
        }

        public static TaskDescriptionFile existingTaskDescriptionFile(){
            return TaskDescriptionFile.builder()
                    .id("task_id")
                    .taskId(1L)
                    .content("Test task content")
                    .build();
        }

        public static String notExistingId(){
            return "not_existing_id";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("update() in working when")
    public class UpdateTests {

        @Test
        public void update_existing(){
            TaskDescriptionFile expected = updatedTaskDescriptionFile();
            TaskDescriptionFile actual = taskDescriptionFileService.update(updatedTaskDescriptionFile());
            assertEquals(expected, actual);
        }

        @Test
        public void update_not_existing(){
            assertThrows(EntityNotFoundException.class,
                    () -> taskDescriptionFileService.update(notExistingUpdatedTaskDescriptionFile()));
        }

        public static TaskDescriptionFile updatedTaskDescriptionFile(){
            return TaskDescriptionFile.builder()
                    .id("task_id")
                    .taskId(1L)
                    .content("Updated file content")
                    .build();
        }

        public static TaskDescriptionFile notExistingUpdatedTaskDescriptionFile(){
            return TaskDescriptionFile.builder().id(
                    TaskDescriptionFileServiceTests.FindByIdTests.notExistingId()
            ).build();
        }

    }

}
