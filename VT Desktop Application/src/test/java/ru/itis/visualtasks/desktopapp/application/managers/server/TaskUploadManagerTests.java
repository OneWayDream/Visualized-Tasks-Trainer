package ru.itis.visualtasks.desktopapp.application.managers.server;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.core.TestIdePageFrame;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.server.UserAuthorizationForm;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;

import java.io.File;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class TaskUploadManagerTests {

    private static final String PROJECT_PATH = USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\server\\task_upload_manager";

    @BeforeAll
    public static void beforeAll(){
        ProjectConfig projectConfig = ProjectFilesManager.getConfigFile(PROJECT_PATH);
        ConfigManager.setConfig(projectConfig);
        Application.changePage(new TestIdePageFrame());
        AuthorizationManager.signIn(UserAuthorizationForm.builder()
                .login("OneWayDream")
                .password("qwerty007")
                .build());
    }

    @Test
    public void upload_task(){
        TaskUploadManager.uploadCurrentTask();
    }

    @AfterAll
    public static void afterAll(){
        ConfigManager.setConfig(null);
        AuthorizationManager.logOut();
        Application.closePageFrame();
    }

}
