package ru.itis.graduationwork.application.ui.pages.main;

import ru.itis.graduationwork.application.managers.SettingsManager;
import ru.itis.graduationwork.application.ui.core.templates.PageFrame;
import ru.itis.graduationwork.application.ui.pages.main.panels.LeftMainPanel;
import ru.itis.graduationwork.application.ui.pages.main.panels.RightMainPanel;
import ru.itis.graduationwork.application.ui.pages.menu.settings.Settings;

import javax.swing.*;
import java.awt.*;

public class MainPageFrame extends PageFrame {

    private static Mode mode;
    private LeftMainPanel leftMainPanel;
    private RightMainPanel rightMainPanel;

    public MainPageFrame(){
        super();
        initMode();
    }

    @Override
    protected void initFields(){
        width = getDeviceScreenWidth()*3 / 4;
        height = getDeviceScreenHeight()*3 / 4;
        title = "Visualized Tasks Trainer";
    }

    private void initMode(){
        mode = SettingsManager.getMode();
    }

    @Override
    public void initPage() {
        initHeader();
        super.initPage();
        frame.setLayout(new GridLayout(1, 2));
    }

    protected void initHeader(){
        super.initHeader();
        initJMenuBar();
    }

    private void initJMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new Settings().getComponent());
        frame.setJMenuBar(menuBar);
    }

    protected void addComponents(){
        leftMainPanel = new LeftMainPanel();
        frame.add(leftMainPanel.getComponent());
        rightMainPanel = new RightMainPanel();
        frame.add(rightMainPanel.getComponent());
    }

    public void changeMode(Mode mode){
        MainPageFrame.mode = mode;
        SettingsManager.setMode(mode);
        leftMainPanel.changeMode();
        rightMainPanel.changeMode();
    }

    public Mode getMode(){
        return mode;
    }

    public enum Mode{
        DEVELOP, STUDY
    }

}
