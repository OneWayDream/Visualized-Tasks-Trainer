package ru.itis.graduationwork.application.ui.pages.study.panels.explorer;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.ui.core.templates.Panel;

import java.awt.*;

public class StudentFileExplorerPanel extends Panel {

    public StudentFileExplorerPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new BorderLayout());
        panel.setBackground(ColorsManager.getPanelBackgroundColor());
        panel.setPreferredSize(new Dimension((int) (Application.getCurrentPageFrame().getWidth() * 0.2),
                0));
    }

    @Override
    protected void addComponents() {
        panel.add(new StudentFileScrollPanel().getComponent(), BorderLayout.CENTER);
    }

}
