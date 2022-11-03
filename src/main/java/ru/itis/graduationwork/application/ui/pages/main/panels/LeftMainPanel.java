package ru.itis.graduationwork.application.ui.pages.main.panels;

import ru.itis.graduationwork.application.ui.core.templates.ModeChangeable;
import ru.itis.graduationwork.application.ui.core.templates.Panel;

import javax.swing.*;
import java.awt.*;

public class LeftMainPanel extends Panel implements ModeChangeable {

    private LeftModeTitlePanel leftModeTitlePanel;
    private LeftButtonsPanel leftButtonsPanel;

    public LeftMainPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new GridLayout(2, 1));
    }

    @Override
    protected void addComponents() {
        leftModeTitlePanel = new LeftModeTitlePanel();
        panel.add(leftModeTitlePanel.getComponent());
        leftButtonsPanel = new LeftButtonsPanel();
        panel.add(leftButtonsPanel.getComponent());
    }

    public void changeMode(){
        resetPanel();
    }

    protected void resetPanel(){
        leftModeTitlePanel.changeMode();
        leftButtonsPanel.changeMode();
    }

    @Override
    public JComponent getComponent() {
        return panel;
    }

}
