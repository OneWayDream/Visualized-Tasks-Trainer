package ru.itis.visualtasks.tasksserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.tasksserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.tasksserver.models.TaskArchive;
import ru.itis.visualtasks.tasksserver.repositories.TaskArchiveRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest()
@TestPropertySource("classpath:test.properties")
@DisplayName("TaskArchiveService is working when")
public class TaskArchiveServiceTests {

    @Autowired
    private TaskArchiveService taskArchiveService;

    @MockBean
    private TaskArchiveRepository taskArchiveRepository;

    @BeforeEach
    public void setUp() {
        when(taskArchiveRepository.findAll()).thenReturn(
                FindAllTests.expectedAllArchives()
        );
        doNothing().when(taskArchiveRepository).delete(DeleteTests.taskArchiveToDelete());
        when(taskArchiveRepository.save(AddTests.taskArchiveToAdd())).thenReturn(
                AddTests.taskArchiveAddResponse()
        );
        when(taskArchiveRepository.findById(FindByIdTests.existingTaskArchive().getId())).thenReturn(
                Optional.ofNullable(FindByIdTests.existingTaskArchive())
        );
        when(taskArchiveRepository.findById(FindByIdTests.notExistingId())).thenThrow(EntityNotFoundException.class);
        when(taskArchiveRepository.save(UpdateTests.updatedTaskArchive())).thenReturn(
                UpdateTests.updatedTaskArchive()
        );
        when(taskArchiveRepository.findByTaskId(FindByTaskIdTests.existingTaskArchive().getTaskId())).thenReturn(
                Optional.ofNullable(FindByTaskIdTests.existingTaskArchive())
        );
        when(taskArchiveRepository.findByTaskId(FindByTaskIdTests.notExistingTaskId())).thenThrow(EntityNotFoundException.class);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    public class FindAllTests {

        @Test
        public void find_all(){
            List<TaskArchive> actual = taskArchiveService.findAll();
            assertEquals(expectedAllArchives(), actual);
        }

        public static List<TaskArchive> expectedAllArchives(){
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

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("delete() in working when")
    public class DeleteTests {

        @Test
        public void delete_existing(){
            taskArchiveService.delete(taskArchiveToDelete());
        }


        public static TaskArchive taskArchiveToDelete(){
            return TaskArchive.builder()
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
            TaskArchive expected = taskArchiveAddResponse();
            TaskArchive actual = taskArchiveService.add(taskArchiveToAdd());
            assertEquals(expected, actual);
        }


        public static TaskArchive taskArchiveToAdd(){
            return TaskArchive.builder()
                    .taskId(1L)
                    .archiveName("Test Task")
                    .archive(new byte[]{1, 2, 3})
                    .build();
        }

        public static TaskArchive taskArchiveAddResponse(){
            return TaskArchive.builder()
                    .id("task_id")
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
            TaskArchive actual = taskArchiveService.findById(existingTaskArchive().getId());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_id(){
            assertThrows(EntityNotFoundException.class, () -> taskArchiveService.findById(notExistingId()));
        }

        public static TaskArchive existingTaskArchive(){
            return TaskArchive.builder()
                    .id("task_id")
                    .taskId(1L)
                    .archiveName("Test Task")
                    .archive(new byte[]{1, 2, 3})
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
            TaskArchive expected = updatedTaskArchive();
            TaskArchive actual = taskArchiveService.update(updatedTaskArchive());
            assertEquals(expected, actual);
        }

        @Test
        public void update_not_existing(){
            assertThrows(EntityNotFoundException.class, () -> taskArchiveService.update(notExistingUpdatedTaskArchive()));
        }

        public static TaskArchive updatedTaskArchive(){
            return TaskArchive.builder()
                    .id("task_id")
                    .taskId(1L)
                    .archiveName("Test Task")
                    .archive(new byte[]{2, 3, 4})
                    .build();
        }

        public static TaskArchive notExistingUpdatedTaskArchive(){
            return TaskArchive.builder().id(FindByIdTests.notExistingId()).build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findByTaskId() in working when")
    public class FindByTaskIdTests {

        @Test
        public void find_existing_by_task_id(){
            TaskArchive expected = existingTaskArchive();
            TaskArchive actual = taskArchiveService.findByTaskId(existingTaskArchive().getTaskId());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_task_id(){
            assertThrows(EntityNotFoundException.class, () -> taskArchiveService.findByTaskId(notExistingTaskId()));
        }

        public static TaskArchive existingTaskArchive(){
            return TaskArchive.builder()
                    .id("task_id")
                    .taskId(1L)
                    .archiveName("Test Task")
                    .archive(new byte[]{1, 2, 3})
                    .build();
        }

        public static Long notExistingTaskId(){
            return 0L;
        }

    }

}
