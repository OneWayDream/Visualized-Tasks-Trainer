package ru.itis.visualtasks.tasksserver.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;
import ru.itis.visualtasks.tasksserver.entities.ArchiveInfo;
import ru.itis.visualtasks.tasksserver.entities.NewTaskInfo;
import ru.itis.visualtasks.tasksserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.tasksserver.models.Language;
import ru.itis.visualtasks.tasksserver.models.TaskArchive;
import ru.itis.visualtasks.tasksserver.models.TaskDescriptionFile;
import ru.itis.visualtasks.tasksserver.models.TaskInfo;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest()
@TestPropertySource("classpath:test.properties")
@DisplayName("TasksService is working when")
public class TasksServiceTests {

    @Autowired
    private TasksService tasksService;

    @MockBean
    private TaskInfoService taskInfoService;
    @MockBean
    private TaskArchiveService taskArchiveService;
    @MockBean
    private TaskDescriptionFileService taskDescriptionFileService;

    @BeforeEach
    public void setUp() {
        when(taskInfoService.findAll()).thenReturn(
                TasksServiceTests.GetTaskInfosTests.expectedAllTaskInfos()
        );
        when(taskInfoService.add(any(TaskInfo.class))).thenReturn(new TaskInfo());
        when(taskDescriptionFileService.add(any(TaskDescriptionFile.class))).thenReturn(new TaskDescriptionFile());
        when(taskArchiveService.add(any(TaskArchive.class))).thenReturn(new TaskArchive());
        when(taskArchiveService.findById(TasksServiceTests.GetTaskArchiveById.existingTaskArchive().getId())).thenReturn(
                TasksServiceTests.GetTaskArchiveById.existingTaskArchive()
        );
        when(taskArchiveService.findById(TasksServiceTests.GetTaskArchiveById.notExistingId()))
                .thenThrow(EntityNotFoundException.class);
        when(taskArchiveService.findByTaskId(TasksServiceTests.GetTaskArchiveByTaskId.existingTaskArchive().getTaskId()))
                .thenReturn(TasksServiceTests.GetTaskArchiveByTaskId.existingTaskArchive());
        when(taskArchiveService.findByTaskId(TasksServiceTests.GetTaskArchiveByTaskId.notExistingTaskId()))
                .thenThrow(EntityNotFoundException.class);
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getTasksInfos() in working when")
    public class GetTaskInfosTests{

        @Test
        public void get_task_infos(){
            List<TaskInfo> actual = tasksService.getTasksInfos();
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
    @DisplayName("saveTask() in working when")
    public class SaveTaskTests{

        @Test
        public void save_correct_task() throws Exception {
            tasksService.saveTask(getArchiveBytes(), getNewTaskInfo(), getArchiveInfo());
        }

        public static byte[] getArchiveBytes() throws Exception {
            File taskArchive = ResourceUtils.getFile("classpath:TestTask.zip");
            return Files.readAllBytes(taskArchive.toPath());
        }

        public static NewTaskInfo getNewTaskInfo(){
            return NewTaskInfo.builder()
                    .authorId(1L)
                    .authorName("test")
                    .taskName("Test Task")
                    .language(Language.JAVA)
                    .build();
        }

        public static ArchiveInfo getArchiveInfo(){
            return new ArchiveInfo("Test_Task.zip");
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getTaskArchiveById() in working when")
    public class GetTaskArchiveById{

        @Test
        public void find_existing_by_id(){
            TaskArchive expected = existingTaskArchive();
            TaskArchive actual = tasksService.getTaskArchiveById(existingTaskArchive().getId());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_id(){
            assertThrows(EntityNotFoundException.class, () -> tasksService.getTaskArchiveById(notExistingId()));
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
    @DisplayName("getTaskArchiveByTaskId() in working when")
    public class GetTaskArchiveByTaskId{

        @Test
        public void find_existing_by_task_id(){
            TaskArchive expected = existingTaskArchive();
            TaskArchive actual = tasksService.getTaskArchiveByTaskId(existingTaskArchive().getTaskId());
            assertEquals(expected, actual);
        }

        @Test
        public void find_not_existing_by_task_id(){
            assertThrows(EntityNotFoundException.class, () -> tasksService.getTaskArchiveByTaskId(notExistingTaskId()));
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
