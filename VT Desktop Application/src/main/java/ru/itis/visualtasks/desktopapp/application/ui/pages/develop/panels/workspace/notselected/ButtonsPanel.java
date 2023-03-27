package ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.workspace.notselected;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Panel;

import javax.swing.*;
import java.awt.*;

public class ButtonsPanel extends Panel {

    public ButtonsPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }

    @Override
    protected void addComponents() {
        panel.add(new CreateFileButton().getComponent());
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(new ChooseFileButton().getComponent());
    }

}
