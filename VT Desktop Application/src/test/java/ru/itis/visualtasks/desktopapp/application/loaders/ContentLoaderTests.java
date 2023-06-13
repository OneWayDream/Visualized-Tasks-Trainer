package ru.itis.visualtasks.desktopapp.application.loaders;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ContentTab;

import java.io.File;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class ContentLoaderTests {

    private static final String HTML_PROJECT_PATH = USER_DIRECTORY + File.separator +
            "src\\test\\resources\\loaders\\content_loader\\html";
    private static final String MARKDOWN_PROJECT_PATH = USER_DIRECTORY + File.separator +
            "src\\test\\resources\\loaders\\content_loader\\md";

    public static void initHtmlProject(){
        ConfigManager.setConfig(
                ProjectConfig.builder()
                .projectPath(HTML_PROJECT_PATH)
                        .generalDescriptionFilePath("general_description.html")
                        .studyingContentFilePath("studying_content.html")
                        .taskTermsFilePath("task_terms.html")
                .build()
        );
    }

    public static void initMdProject(){
        ConfigManager.setConfig(
                ProjectConfig.builder()
                        .projectPath(MARKDOWN_PROJECT_PATH)
                        .generalDescriptionFilePath("general_description.md")
                        .studyingContentFilePath("studying_content.md")
                        .taskTermsFilePath("task_terms.md")
                        .build()
        );
    }

    public static void initTxtProject(){
        ConfigManager.setConfig(
                ProjectConfig.builder()
                        .projectPath(MARKDOWN_PROJECT_PATH)
                        .generalDescriptionFilePath("general_description.txt")
                        .studyingContentFilePath("studying_content.txt")
                        .taskTermsFilePath("task_terms.txt")
                        .build()
        );
    }

    @Test
    public void get_html_general_description_content(){
        initHtmlProject();
        Assertions.assertEquals("<h1>General description title</h1>",
                ContentLoader.loadContent(ContentTab.GENERAL_DESCRIPTION));
    }

    @Test
    public void get_html_studying_content(){
        initHtmlProject();
        Assertions.assertEquals("<h1>Studying content title</h1>",
                ContentLoader.loadContent(ContentTab.STUDYING_CONTENT));
    }

    @Test
    public void get_html_task_terms_content(){
        initHtmlProject();
        Assertions.assertEquals("<h1>Task terms title</h1>",
                ContentLoader.loadContent(ContentTab.TASK_TERMS));
    }

    @Test
    public void get_md_general_description_content(){
        initMdProject();
        Assertions.assertEquals("<h1>General description title</h1>\n",
                ContentLoader.loadContent(ContentTab.GENERAL_DESCRIPTION));
    }

    @Test
    public void get_md_studying_content(){
        initMdProject();
        Assertions.assertEquals("<h1>Studying content title</h1>\n",
                ContentLoader.loadContent(ContentTab.STUDYING_CONTENT));
    }

    @Test
    public void get_md_task_terms_content(){
        initMdProject();
        Assertions.assertEquals("<h1>Task terms title</h1>\n",
                ContentLoader.loadContent(ContentTab.TASK_TERMS));
    }

    @Test
    public void get_txt_general_description_content(){
        initTxtProject();
        Assertions.assertNull(ContentLoader.loadContent(ContentTab.GENERAL_DESCRIPTION));
        Assertions.assertNull(ConfigManager.getGeneralDescriptionFilePath());
    }

    @Test
    public void get_txt_studying_content(){
        initTxtProject();
        Assertions.assertNull(ContentLoader.loadContent(ContentTab.STUDYING_CONTENT));
        Assertions.assertNull(ConfigManager.getStudyingContentFilePath());
    }

    @Test
    public void get_txt_task_terms_content(){
        initTxtProject();
        Assertions.assertNull(ContentLoader.loadContent(ContentTab.TASK_TERMS));
        Assertions.assertNull(ConfigManager.getTaskTermsFilePath());
    }

    @AfterAll
    public static void afterAll(){
        ConfigManager.setConfig(null);
    }

}
