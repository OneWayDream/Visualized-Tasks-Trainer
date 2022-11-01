package ru.itis.graduationwork.application.ui.pages.main;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.ui.core.PageFrame;
import ru.itis.graduationwork.application.ui.pages.main.panels.LeftMainPanel;
import ru.itis.graduationwork.application.ui.pages.main.panels.RightMainPanel;
import ru.itis.graduationwork.application.ui.pages.menu.settings.Settings;
import ru.itis.graduationwork.application.utils.ColorsManager;
import ru.itis.graduationwork.application.utils.ImagesManager;

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

    private void initHeader(){
        frame.setTitle("Visualized Tasks Trainer");
        frame.setForeground(ColorsManager.getTextColor());
        frame.setIconImage(ImagesManager.getApplicationIcon().getImage());
        initJMenuBar();
    }

    private void initJMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new Settings().getComponent());
        frame.setJMenuBar(menuBar);
    }

    private void initMode(){
        mode = Application.getSettings().getMode();
    }

    @Override
    public void initPage() {
        initHeader();
        super.initPage();
        frame.setLayout(new GridLayout(1, 2));
    }

    protected void addComponents(){
        leftMainPanel = new LeftMainPanel();
        frame.add(leftMainPanel.getComponent());
        rightMainPanel = new RightMainPanel();
        frame.add(rightMainPanel.getComponent());
    }

    public void changeMode(Mode mode){
        MainPageFrame.mode = mode;
        Application.getSettings().setMode(mode);
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
