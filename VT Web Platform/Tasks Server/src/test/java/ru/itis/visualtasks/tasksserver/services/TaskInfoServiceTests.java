package ru.itis.visualtasks.tasksserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import ru.itis.visualtasks.tasksserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.tasksserver.models.Language;
import ru.itis.visualtasks.tasksserver.models.TaskInfo;
import ru.itis.visualtasks.tasksserver.repositories.TaskInfoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest()
@TestPropertySource("classpath:test.properties")
@DisplayName("TaskInfoService is working when")
public class TaskInfoServiceTests {

    @Autowired
    private TaskInfoService taskInfoService;

    @MockBean
    private TaskInfoRepository taskInfoRepository;

    @BeforeEach
    public void setUp() {
        when(taskInfoRepository.findAll()).thenReturn(
                TaskInfoServiceTests.FindAllTests.expectedAllTaskInfos()
        );
        doNothing().when(taskInfoRepository).delete(TaskInfoServiceTests.DeleteTests.taskInfoToDelete());
        when(taskInfoRepository.save(TaskInfoServiceTests.AddTests.taskInfoToAdd())).thenReturn(
                TaskInfoServiceTests.AddTests.taskInfoAddResponse()
        );
        when(taskInfoRepository.findById(TaskInfoServiceTests.FindByIdTests.existingTaskInfo().getId())).thenReturn(
                Optional.ofNullable(TaskInfoServiceTests.FindByIdTests.existingTaskInfo())
        );
        when(taskInfoRepository.findById(TaskInfoServiceTests.FindByIdTests.notExistingId())).thenThrow(EntityNotFoundException.class);
        when(taskInfoRepository.save(TaskInfoServiceTests.UpdateTests.updatedTaskInfo())).thenReturn(
                TaskInfoServiceTests.UpdateTests.updatedTaskInfo()
        );
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findAll() in working when")
    public class FindAllTests {

        @Test
        public void find_all(){
            List<TaskInfo> actual = taskInfoService.findAll();
            assertEquals(expectedAllTaskInfos(), actual);
        }

        public static List<TaskInfo> expectedAllTaskInfos(){
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
            taskInfoService.delete(taskInfoToDelete());
        }


        public static TaskInfo taskInfoToDelete(){
            return TaskInfo.builder()
                    .id(3L)
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("add() in working when")
    public class AddTests {

        @Test
        public void add(){
            TaskInfo expected = taskInfoAddResponse();
            TaskInfo actual = taskInfoService.add(taskInfoToAdd());
            assertEquals(expected, actual);
        }


        public static TaskInfo taskInfoToAdd(){
            return TaskInfo.builder()
                    .authorId(1L)
                    .taskName("Third Test Task")
                    .authorName("test")
                    .language(Language.JAVA)
                    .additionDate(LocalDateTime.parse("2023-04-11T17:11:11.761486"))
                    .build();
        }

        public static TaskInfo taskInfoAddResponse(){
            return TaskInfo.builder()
                    .id(3L)
                    .authorId(1L)
                    .taskName("Third Test Task")
                    .authorName("test")
                    .language(Language.JAVA)
                    .additionDate(LocalDateTime.parse("2023-04-11T17:11:11.761486"))
                    .build();
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("findById() in working when")
    public class FindByIdTests {

        @Test
        public void find_existing_by_id(){
            TaskInfo expected = existingTaskInfo();
            TaskInfo actual = taskInfoService.findById(existingTaskInfo().getId());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_id(){
            assertThrows(EntityNotFoundException.class, () -> taskInfoService.findById(notExistingId()));
        }

        public static TaskInfo existingTaskInfo(){
            return TaskInfo.builder()
                    .id(1L)
                    .authorId(1L)
                    .taskName("First Test Task")
                    .authorName("test")
                    .language(Language.JAVA)
                    .additionDate(LocalDateTime.parse("2023-04-20T13:59:37.862496"))
                    .build();
        }

        public static Long notExistingId(){
            return 100L;
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("update() in working when")
    public class UpdateTests {

        @Test
        public void update_existing(){
            TaskInfo expected = updatedTaskInfo();
            TaskInfo actual = taskInfoService.update(updatedTaskInfo());
            assertEquals(expected, actual);
        }

        @Test
        public void update_not_existing(){
            assertThrows(EntityNotFoundException.class, () -> taskInfoService.update(notExistingUpdatedTaskArchive()));
        }

        public static TaskInfo updatedTaskInfo(){
            return TaskInfo.builder()
                    .id(1L)
                    .authorId(1L)
                    .taskName("New Task Name")
                    .authorName("test")
                    .language(Language.JAVA)
                    .additionDate(LocalDateTime.parse("2023-04-20T13:59:37.862496"))
                    .build();
        }

        public static TaskInfo notExistingUpdatedTaskArchive(){
            return TaskInfo.builder().id(TaskInfoServiceTests.FindByIdTests.notExistingId()).build();
        }

    }
    
}
