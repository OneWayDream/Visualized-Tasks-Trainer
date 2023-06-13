package ru.itis.visualtasks.desktopapp.application.managers.files;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ConfigManagerTests {

    private static final ProjectConfig PROJECT_CONFIG;
    private static final String PROJECT_PATH =  USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\files\\config_manager";
    private static final String PROJECT_NAME =  "project-name";
    private static final String GENERAL_DESCRIPTION_FILE_PATH = PROJECT_PATH + File.separator
            + "general-description.html";
    private static final String STUDYING_CONTENT_FILE_PATH = PROJECT_PATH + File.separator
            + "studying-content.html";
    private static final String TASK_TERMS_FILE_PATH = PROJECT_PATH + File.separator
            + "task-terms.html";

    static {
        Map<String, String> wrappersNames = new HashMap<>();
        wrappersNames.put("WrappedClass", "Wrapper");
        PROJECT_CONFIG = ProjectConfig.builder()
                .projectPath(PROJECT_PATH)
                .projectName(PROJECT_NAME)
                .visualizationType(VisualizationType.SWING)
                .language(Language.JAVA)
                .wrappersNames(wrappersNames)
                .generalDescriptionFilePath("general-description.html")
                .studyingContentFilePath("studying-content.html")
                .taskTermsFilePath("task-terms.html")
                .build();
    }

    @Test
    @Order(1)
    public void set_project_config(){
        ConfigManager.setConfig(PROJECT_CONFIG);
        ConfigManager.saveConfigFile();
    }

    @Test
    @Order(2)
    public void get_project_config(){
        Assertions.assertEquals(PROJECT_CONFIG, ConfigManager.getConfig());
    }

    @Test
    @Order(3)
    public void get_project_path(){
        Assertions.assertEquals(PROJECT_PATH, ConfigManager.getProjectPath());
    }

    @Test
    @Order(4)
    public void get_project_name(){
        Assertions.assertEquals(PROJECT_NAME, ConfigManager.getProjectName());
    }

    @Test
    @Order(5)
    public void get_visualization_type(){
        Assertions.assertEquals(VisualizationType.SWING, ConfigManager.getProjectVisualizationType());
    }

    @Test
    @Order(6)
    public void get_language(){
        Assertions.assertEquals(Language.JAVA, ConfigManager.getProjectLanguage());
    }

    @Test
    @Order(7)
    public void get_wrappers_names(){
        Map<String, String> wrappersNames = ConfigManager.getWrappersNames();
        Assertions.assertNotNull(wrappersNames);
        Assertions.assertFalse(wrappersNames.isEmpty());
        Assertions.assertEquals("Wrapper", wrappersNames.get("WrappedClass"));
    }

    @Test
    @Order(8)
    public void set_wrappers_names(){
        Map<String, String> wrappersNames = new HashMap<>();
        wrappersNames.put("WrappedClass", "NewWrapper");
        ConfigManager.setWrappersNames(wrappersNames);
        wrappersNames = ConfigManager.getWrappersNames();
        Assertions.assertNotNull(wrappersNames);
        Assertions.assertFalse(wrappersNames.isEmpty());
        Assertions.assertEquals("NewWrapper", wrappersNames.get("WrappedClass"));
    }

    @Test
    @Order(9)
    public void get_general_description_file_path(){
        Assertions.assertEquals(GENERAL_DESCRIPTION_FILE_PATH, ConfigManager.getGeneralDescriptionFilePath());
    }

    @Test
    @Order(10)
    public void set_general_description_file_path(){
        String newGeneralDescriptionFilePath = PROJECT_PATH + File.separator + "new-general-description.html";
        ConfigManager.setGeneralDescriptionFilePath(newGeneralDescriptionFilePath);
        Assertions.assertEquals(newGeneralDescriptionFilePath, ConfigManager.getGeneralDescriptionFilePath());
    }

    @Test
    @Order(11)
    public void get_studying_content_file_path(){
        Assertions.assertEquals(STUDYING_CONTENT_FILE_PATH, ConfigManager.getStudyingContentFilePath());
    }

    @Test
    @Order(12)
    public void set_studying_content_file_path(){
        String newStudyingContentFilePath = PROJECT_PATH + File.separator + "new-studying-content.html";
        ConfigManager.setStudyingContentFilePath(newStudyingContentFilePath);
        Assertions.assertEquals(newStudyingContentFilePath, ConfigManager.getStudyingContentFilePath());
    }

    @Test
    @Order(13)
    public void get_task_terms_file_path(){
        Assertions.assertEquals(TASK_TERMS_FILE_PATH, ConfigManager.getTaskTermsFilePath());
    }

    @Test
    @Order(14)
    public void set_task_terms_file_path(){
        String newTaskTermsFilePath = PROJECT_PATH + File.separator + "new-task-terms.html";
        ConfigManager.setTaskTermsFilePath(newTaskTermsFilePath);
        Assertions.assertEquals(newTaskTermsFilePath, ConfigManager.getTaskTermsFilePath());
    }

}
