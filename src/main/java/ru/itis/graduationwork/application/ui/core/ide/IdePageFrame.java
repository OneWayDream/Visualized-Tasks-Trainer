package ru.itis.graduationwork.application.ui.core.ide;

import lombok.Getter;
import ru.itis.graduationwork.application.entities.ProjectConfig;
import ru.itis.graduationwork.application.managers.project.WorkspaceContentManager;
import ru.itis.graduationwork.application.managers.settings.BackgroundImageManager;
import ru.itis.graduationwork.application.ui.core.templates.PageFrame;
import ru.itis.graduationwork.application.ui.pages.menu.file.FileMenu;
import ru.itis.graduationwork.application.ui.pages.menu.settings.SettingsMenu;

import javax.swing.*;
import java.awt.*;

public abstract class IdePageFrame extends PageFrame {

    protected IdeBackgroundPanel backgroundPanel;
    @Getter
    protected ProjectConfig projectConfig;

    public IdePageFrame(){
        super();
        initBackground();
    }

    private void initBackground() {
        backgroundPanel = new IdeBackgroundPanel();
        Image backgroundImage = BackgroundImageManager.getBackgroundImage();
        if (backgroundImage != null){
            backgroundPanel.setImage(backgroundImage);
        }
        frame.add(backgroundPanel);
    }

    public void setProjectConfig(ProjectConfig config){
        this.projectConfig = config;
        this.title = config.getProjectName();
    }

    protected void initHeader(){
        super.initHeader();
        initJMenuBar();
    }

    private void initJMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new FileMenu().getComponent());
        menuBar.add(new SettingsMenu().getComponent());
        frame.setJMenuBar(menuBar);
    }

    public abstract void updateWorkspaceContent();

    @Override
    public void dispose(){
        WorkspaceContentManager.saveEditorChangedIfNeeded();
        frame.dispose();
    }

}
