package ru.itis.visualtasks.tasksserver.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;
import ru.itis.visualtasks.tasksserver.entities.ArchiveInfo;
import ru.itis.visualtasks.tasksserver.entities.NewTaskInfo;
import ru.itis.visualtasks.tasksserver.exceptions.persistence.EntityNotFoundException;
import ru.itis.visualtasks.tasksserver.models.Language;
import ru.itis.visualtasks.tasksserver.models.TaskArchive;
import ru.itis.visualtasks.tasksserver.models.TaskInfo;
import ru.itis.visualtasks.tasksserver.services.TasksService;
import ru.itis.visualtasks.tasksserver.utils.JwtUtils;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource("classpath:test.properties")
@DisplayName("TaskController is working when")
public class TasksControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TasksService tasksService;

    @MockBean
    private JwtUtils jwtUtils;

    @Value("${jwt.user.access-token.secret-key}")
    private String accessKey;

    @BeforeEach
    public void setUp(){
        when(tasksService.getTasksInfos()).thenReturn(Collections.singletonList(
                TaskInfo.builder()
                        .id(1L)
                        .authorId(1L)
                        .authorName("Author")
                        .taskName("Test Task")
                        .language(Language.JAVA)
                        .additionDate(LocalDateTime.now())
                        .build()
        ));
        doNothing().when(tasksService).saveTask(any(byte[].class), any(NewTaskInfo.class), any(ArchiveInfo.class));
        when(tasksService.getTaskArchiveById("correct-id")).thenReturn(
                TaskArchive.builder()
                        .id("correct-id")
                        .taskId(1L)
                        .archiveName("TestTask")
                        .archive(new byte[]{1, 2, 3})
                        .build()
        );
        when(tasksService.getTaskArchiveById("incorrect-id")).thenThrow(EntityNotFoundException.class);
        when(tasksService.getTaskArchiveByTaskId(1L)).thenReturn(
                TaskArchive.builder()
                        .id("correct-id")
                        .taskId(1L)
                        .archiveName("TestTask")
                        .archive(new byte[]{1, 2, 3})
                        .build()
        );
        when(tasksService.getTaskArchiveByTaskId(2L)).thenThrow(EntityNotFoundException.class);
        when(jwtUtils.decodeJwt(UploadNewTaskTests.getActualToken())).thenReturn(
                JWT.require(Algorithm.HMAC256(accessKey))
                        .build()
                        .verify(UploadNewTaskTests.getActualToken())
        );
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getTasksInfos() in working when")
    public class GetTaskInfosTests {

        @Test
        public void return_task_infos() throws Exception{
             mockMvc.perform(get("/all"))
                     .andDo(print())
                     .andExpect(status().isOk())
                     .andExpect(jsonPath("$[0].id", is(1)))
                     .andExpect(jsonPath("$[0].authorId", is(1)))
                     .andExpect(jsonPath("$[0].authorName", is("Author")))
                     .andExpect(jsonPath("$[0].taskName", is("Test Task")))
                     .andExpect(jsonPath("$[0].language", is("JAVA")));
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("uploadNewTask() in working when")
    public class UploadNewTaskTests {

        @Test
        public void upload_new_task() throws Exception{
            mockMvc.perform(
                        multipart("/upload")
                                .file("task", getArchiveBytes())
                                .header("JWT", getActualToken())
                    )
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        public byte[] getArchiveBytes() throws Exception {
            File taskArchive = ResourceUtils.getFile("classpath:TestTask.zip");
            return Files.readAllBytes(taskArchive.toPath());
        }

        public static String getActualToken(){
            return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0IiwiYWNjb3VudF9pZCI6OSwicm9sZSI6IlVTRVIiLCJzYWx0IjoiMWZjYWZlZGQtNDJiYy00YWRhLWI4YzMtOTliNzMwOGQ5NzM4IiwiZXhwaXJhdGlvbiI6bnVsbCwic3RhdGUiOiJBQ1RJVkUiLCJsb2dpbiI6Ik9uZVdheURyZWFtIn0._aX20py8eNU8249Hd-xebLMo1B1OeNnX_xa0UeSn0Mw";
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getTaskArchiveById() in working when")
    public class GetTaskByArchiveByIdTests {

        @Test
        public void get_task_archive_by_correct_id() throws Exception{
            mockMvc.perform(get("/by-id/correct-id"))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        public void get_task_archive_by_incorrect_id() throws Exception{
            mockMvc.perform(get("/by-id/incorrect-id"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getTaskArchiveByTaskId() in working when")
    public class GetTaskByArchiveByTaskIdTests {

        @Test
        public void get_task_archive_by_correct_task_id() throws Exception{
            mockMvc.perform(get("/by-task-id/1"))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        public void get_task_archive_by_incorrect_task_id() throws Exception{
            mockMvc.perform(get("/by-task-id/2"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }

    }

}
