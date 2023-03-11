package ru.itis.graduationwork.application.ui.pages.study;

import ru.itis.graduationwork.application.ui.core.PageType;
import ru.itis.graduationwork.application.ui.core.ide.IdePageFrame;
import ru.itis.graduationwork.application.ui.core.templates.PageFrame;
import ru.itis.graduationwork.application.ui.core.ide.visualization.components.VisualizationPanel;
import ru.itis.graduationwork.application.ui.core.ide.workspace.ButtonsAndWorkspacePanel;
import ru.itis.graduationwork.application.ui.pages.study.panels.explorer.StudentFileExplorerPanel;

import java.awt.*;

public class StudyPageFrame extends IdePageFrame {

    private ButtonsAndWorkspacePanel buttonsAndWorkspacePanel;

    public StudyPageFrame(){
        super();
        pageType = PageType.STUDY;
    }

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth()*3 / 4;
        height = getDeviceScreenHeight()*3 / 4;
    }

    @Override
    public PageFrame copy() {
        StudyPageFrame studyPageFrame = new StudyPageFrame();
        studyPageFrame.setProjectConfig(projectConfig);
        return studyPageFrame;
    }

    @Override
    public void initPage() {
        backgroundPanel.setLayout(new BorderLayout());
        super.initPage();
    }

    @Override
    protected void addComponents() {
        backgroundPanel.add(new StudentFileExplorerPanel().getComponent(), BorderLayout.LINE_START);
        buttonsAndWorkspacePanel = new ButtonsAndWorkspacePanel();
        backgroundPanel.add(buttonsAndWorkspacePanel.getComponent(), BorderLayout.CENTER);
        backgroundPanel.add(new VisualizationPanel().getComponent(), BorderLayout.LINE_END);
    }

    @Override
    public void updateWorkspaceContent() {
        buttonsAndWorkspacePanel.updateWorkspaceContent();
    }

}
