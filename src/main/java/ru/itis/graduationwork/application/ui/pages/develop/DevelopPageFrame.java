package ru.itis.graduationwork.application.ui.pages.develop;

import ru.itis.graduationwork.application.ui.core.PageType;
import ru.itis.graduationwork.application.ui.core.ide.IdePageFrame;
import ru.itis.graduationwork.application.ui.core.templates.PageFrame;
import ru.itis.graduationwork.application.ui.pages.develop.panels.explorer.DevelopFileExplorerPanel;
import ru.itis.graduationwork.application.ui.pages.develop.panels.visualization.VisualizationPanel;
import ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.ButtonsAndWorkspacePanel;

import java.awt.*;

public class DevelopPageFrame extends IdePageFrame {

    private ButtonsAndWorkspacePanel buttonsAndWorkspacePanel;

    public DevelopPageFrame(){
        super();
        pageType = PageType.DEVELOP;
    }

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth()*3 / 4;
        height = getDeviceScreenHeight()*3 / 4;
    }

    @Override
    public PageFrame copy() {
        DevelopPageFrame developPageFrame = new DevelopPageFrame();
        developPageFrame.setProjectConfig(projectConfig);
        return developPageFrame;
    }

    @Override
    public void initPage() {
        backgroundPanel.setLayout(new BorderLayout());
        super.initPage();
    }

    @Override
    protected void addComponents() {
        backgroundPanel.add(new DevelopFileExplorerPanel().getComponent(), BorderLayout.LINE_START);
        buttonsAndWorkspacePanel = new ButtonsAndWorkspacePanel();
        backgroundPanel.add(buttonsAndWorkspacePanel.getComponent(), BorderLayout.CENTER);
        backgroundPanel.add(new VisualizationPanel().getComponent(), BorderLayout.LINE_END);
    }


    public void updateWorkspaceContent() {
        buttonsAndWorkspacePanel.updateWorkspaceContent();
    }

}
