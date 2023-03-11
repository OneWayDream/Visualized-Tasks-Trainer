package ru.itis.graduationwork.application.managers.project;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.entities.NewProjectInfo;
import ru.itis.graduationwork.application.entities.ProjectConfig;
import ru.itis.graduationwork.application.managers.content.PagesManager;
import ru.itis.graduationwork.application.managers.content.RecentManager;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.settings.Mode;
import ru.itis.graduationwork.application.ui.core.PageType;
import ru.itis.graduationwork.application.ui.core.ide.IdePageFrame;
import ru.itis.graduationwork.application.ui.core.ide.explorer.FileWatchService;

public class ProjectsManager {

    public static void createProject(NewProjectInfo newProjectInfo) {
        ProjectFilesManager.createProjectFolders(newProjectInfo.getProjectPath());
        ProjectConfig projectConfig = projectInfoToProjectConfig(newProjectInfo);
        ProjectFilesManager.createProjectFiles(projectConfig);
    }

    private static ProjectConfig projectInfoToProjectConfig(NewProjectInfo projectInfo){
        return ProjectConfig.builder()
                .projectName(projectInfo.getProjectName())
                .language(projectInfo.getLanguage())
                .projectPath(projectInfo.getProjectPath())
                .build();
    }

    public static void openProject(String projectPath){
        ProjectConfig projectConfig = ProjectFilesManager.getConfigFile(projectPath);
        ProjectFilesManager.createDevelopProjectFiles(projectConfig);
        updateApplicationComponents(projectConfig);
        resetApplicationComponents();
        changeApplicationPageFrame(projectConfig);
    }

    private static void updateApplicationComponents(ProjectConfig projectConfig){
        ConfigManager.setConfig(projectConfig);
        ProjectTaskFilesManager.setProjectLanguage(projectConfig.getLanguage());
    }

    private static void resetApplicationComponents(){
        WorkspaceContentManager.reset();
        FileWatchService.clearMonitoringTreePaths();
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
