package ru.itis.visualtasks.desktopapp.application.managers.content;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.entities.project.RecentList;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class RecentManagerTests {

    @BeforeAll
    public static void beforeAll(){
        delete(RecentManager.RECENT_PROJECTS_FILE_PATH);
        delete(RecentManager.RECENT_TASKS_FILE_PATH);
    }

    private static void delete(String filePath){
        try{
            FilesManager.delete(filePath);
        } catch (Exception exception){
            //ignore
        }
    }

    @Test
    @Order(1)
    public void get_empty_recent_projects(){
        RecentList recentList = RecentManager.getRecentProjects();
        Assertions.assertTrue(recentList.getContent().isEmpty());
    }

    @Test
    @Order(2)
    public void add_recent_project(){
        RecentManager.addRecentProject(ProjectConfig.builder()
                .projectName("Test project")
                .projectPath("test_project_path")
                .build());
    }

    @Test
    @Order(3)
    public void get_projects(){
        RecentList recentList = RecentManager.getRecentProjects();
        Assertions.assertFalse(recentList.getContent().isEmpty());
        Assertions.assertEquals("Test project",
                recentList.getContent().get("test_project_path").getProjectName());
    }

    @Test
    @Order(4)
    public void delete_recent_project(){
        RecentManager.deleteRecentProject("test_project_path");
        RecentList recentList = RecentManager.getRecentProjects();
        Assertions.assertTrue(recentList.getContent().isEmpty());
    }

    @Test
    @Order(5)
    public void get_empty_recent_tasks(){
        RecentList recentList = RecentManager.getRecentTasks();
        Assertions.assertTrue(recentList.getContent().isEmpty());
    }

    @Test
    @Order(6)
    public void add_recent_tasks(){
        RecentManager.addRecentTask(ProjectConfig.builder()
                .projectName("Test task")
                .projectPath("test_task_path")
                .build());
    }

    @Test
    @Order(7)
    public void get_tasks(){
        RecentList recentList = RecentManager.getRecentTasks();
        Assertions.assertFalse(recentList.getContent().isEmpty());
        Assertions.assertEquals("Test task",
                recentList.getContent().get("test_task_path").getProjectName());
    }

    @Test
    @Order(8)
    public void delete_recent_task(){
        RecentManager.deleteRecentTask("test_task_path");
        RecentList recentList = RecentManager.getRecentTasks();
        Assertions.assertTrue(recentList.getContent().isEmpty());
    }

}
