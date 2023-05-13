package ru.itis.visualtasks.desktopapp.application.managers.project;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.core.TestIdePageFrame;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ContentTab;

import javax.swing.*;
import java.io.File;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class WorkspaceContentManagerTests {

    private static final String PROJECT_PATH = USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\project\\workspace_content_manager";
    private static final String NEW_STUDYING_CONTENT_FILE_PATH = PROJECT_PATH + File.separator
            + "info\\new_studying_content.html";

    @BeforeAll
    public static void beforeAll(){
        ConfigManager.setConfig(ProjectConfig.builder()
                        .projectName("Test project")
                        .projectPath(PROJECT_PATH)
                        .language(Language.JAVA)
                        .visualizationType(VisualizationType.SWING)
                        .generalDescriptionFilePath("info\\general_description.html")
                        .studyingContentFilePath("info\\studying_content.html")
                        .taskTermsFilePath("info\\task_terms")
                .build());
        WorkspaceContentManager.reset();
        Application.changePage(new TestIdePageFrame());
    }

    @Test
    @Order(1)
    public void get_default_content_type(){
        Assertions.assertEquals(ContentTab.GENERAL_DESCRIPTION, WorkspaceContentManager.getContentTab());
    }

    @Test
    @Order(2)
    public void get_default_content_pane(){
        Assertions.assertEquals(JScrollPane.class, WorkspaceContentManager.getContentPane().getClass());
    }

    @Test
    @Order(3)
    public void get_content_editor_pane(){
        WorkspaceContentManager.setEditorContent(ConfigManager.getGeneralDescriptionFilePath());
        Assertions.assertEquals(JScrollPane.class, WorkspaceContentManager.getContentPane().getClass());
    }

    @Test
    @Order(4)
    public void get_editor_file_path(){
        Assertions.assertEquals(ConfigManager.getGeneralDescriptionFilePath(), WorkspaceContentManager.getEditorFilePath());
    }

    @Test
    @Order(5)
    public void change_workspace_content(){
        WorkspaceContentManager.changeWorkspaceContent(ContentTab.STUDYING_CONTENT);
        Assertions.assertEquals(ContentTab.STUDYING_CONTENT, WorkspaceContentManager.getContentTab());
    }

    @Test
    @Order(6)
    public void change_info_content_file_path(){
        WorkspaceContentManager.changeContentFilePath(NEW_STUDYING_CONTENT_FILE_PATH);
        Assertions.assertEquals(ConfigManager.getStudyingContentFilePath(), NEW_STUDYING_CONTENT_FILE_PATH);
    }

    @Test
    @Order(7)
    public void notify_about_file_deletion(){
        WorkspaceContentManager.notifyAboutFileDeletion(PROJECT_PATH + File.separator
                + "info\\new_studying_content.html");
        Assertions.assertNull(ConfigManager.getStudyingContentFilePath());
    }

    @AfterAll
    public static void afterAll(){
        WorkspaceContentManager.reset();
        ConfigManager.setConfig(null);
        Application.closePageFrame();
    }

}
