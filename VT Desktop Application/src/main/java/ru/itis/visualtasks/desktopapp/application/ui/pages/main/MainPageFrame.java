package ru.itis.visualtasks.desktopapp.application.ui.pages.main;

import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.ui.core.PageType;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.PageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.panels.LeftMainPanel;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.panels.RightMainPanel;
import ru.itis.visualtasks.desktopapp.application.ui.pages.menu.settings.SettingsMenu;

import javax.swing.*;
import java.awt.*;

public class MainPageFrame extends PageFrame {

    private LeftMainPanel leftMainPanel;
    private RightMainPanel rightMainPanel;

    public MainPageFrame(){
        super();
        pageType = PageType.MAIN;
    }

    @Override
    protected void initFields(){
        width = getDeviceScreenWidth()*3 / 4;
        height = getDeviceScreenHeight()*3 / 4;
        title = "Visualized Tasks Trainer";
    }

    @Override
    public PageFrame copy() {
        return new MainPageFrame();
    }

    @Override
    public void initPage() {
        super.initPage();
        frame.setLayout(new GridLayout(1, 2));
    }

    protected void initHeader(){
        super.initHeader();
        initJMenuBar();
    }

    private void initJMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new SettingsMenu().getComponent());
        frame.setJMenuBar(menuBar);
    }

    @Override
    protected void addComponents(){
        leftMainPanel = new LeftMainPanel();
        frame.add(leftMainPanel.getComponent());
        rightMainPanel = new RightMainPanel();
        frame.add(rightMainPanel.getComponent());
    }

    @Override
    public void changeMode(Mode mode){
        leftMainPanel.changeMode();
        rightMainPanel.changeMode();
    }

}
