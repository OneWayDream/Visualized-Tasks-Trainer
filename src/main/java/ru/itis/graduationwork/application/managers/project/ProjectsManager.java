package ru.itis.graduationwork.application.managers.project;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.entities.project.NewProjectInfo;
import ru.itis.graduationwork.application.entities.project.ProjectConfig;
import ru.itis.graduationwork.application.managers.content.PagesManager;
import ru.itis.graduationwork.application.managers.content.RecentManager;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.project.visualization.buttons.VisualizationControlButtonsManager;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.ui.core.PageType;
import ru.itis.graduationwork.application.ui.core.ide.IdePageFrame;
import ru.itis.graduationwork.application.ui.core.ide.explorer.FileWatchService;
import ru.itis.graduationwork.exceptions.generation.TemplateCreationException;
import ru.itis.graduationwork.exceptions.generation.TemplateLoadingException;

public class ProjectsManager {

    public static void createProject(NewProjectInfo newProjectInfo) {
        ProjectFilesManager.createProjectFolders(newProjectInfo.getProjectPath());
        ProjectConfig projectConfig = projectInfoToProjectConfig(newProjectInfo);
        ProjectFilesManager.writeConfigFile(projectConfig);
    }

    private static ProjectConfig projectInfoToProjectConfig(NewProjectInfo projectInfo){
        return ProjectConfig.builder()
                .projectName(projectInfo.getProjectName())
                .language(projectInfo.getLanguage())
                .visualizationType(projectInfo.getVisualizationType())
                .projectPath(projectInfo.getProjectPath())
                .build();
    }

    public static void openProject(String projectPath){
        try{
            ProjectConfig projectConfig = ProjectFilesManager.getConfigFile(projectPath);
            if (!projectConfig.getProjectPath().equals(projectPath)){
                projectConfig.setProjectPath(projectPath);
            }
            resetApplicationComponents();
            updateApplicationComponents(projectConfig);
            createDevelopProjectFiles();
            changeApplicationPageFrame(projectConfig);
        } catch (TemplateLoadingException | TemplateCreationException exception){
            exception.handle();
            PagesManager.openStartPage();
        }
    }

    private static void resetApplicationComponents(){
        WorkspaceContentManager.reset();
        FileWatchService.clearMonitoringTreePaths();
        VisualizationControlButtonsManager.reset();
        ProjectFilesManager.reset();
    }

    private static void updateApplicationComponents(ProjectConfig projectConfig){
        ConfigManager.setConfig(projectConfig);
        ProjectFilesManager.init(projectConfig);
        ProjectTaskFilesManager.setProjectLanguage(projectConfig.getLanguage());
    }

    private static void createDevelopProjectFiles(){
        if (Application.getMode() == Mode.DEVELOP){
            ProjectFilesManager.createProjectFiles();
        }
    }

    private static void changeApplicationPageFrame(ProjectConfig projectConfig){
        IdePageFrame pageFrame = getIdePageFrame(projectConfig);
        Application.changePage(pageFrame);
    }

    private static IdePageFrame getIdePageFrame(ProjectConfig projectConfig){
        IdePageFrame pageFrame;
        if (Application.getMode().equals(Mode.DEVELOP)){
            pageFrame = (IdePageFrame) PagesManager.getPage(PageType.DEVELOP);
            RecentManager.addRecentProject(projectConfig);
        } else {
            pageFrame = (IdePageFrame) PagesManager.getPage(PageType.STUDY);
            RecentManager.addRecentTask(projectConfig);
        }
        pageFrame.setProjectConfig(projectConfig);
        return pageFrame;
    }

}