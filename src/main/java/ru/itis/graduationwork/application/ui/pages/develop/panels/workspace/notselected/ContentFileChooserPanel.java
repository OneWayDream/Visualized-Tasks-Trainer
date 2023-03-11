package ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.notselected;

import ru.itis.graduationwork.application.ui.core.templates.Panel;

import java.awt.*;

public class ContentFileChooserPanel extends Panel {

    public ContentFileChooserPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new GridBagLayout());
    }

    @Override
    protected void addComponents() {
        addInformationLabel();
        addButtonsPanel();

    }

    private void addInformationLabel() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(new FileIsNotSelectedLabel().getComponent(), constraint);
    }

    private void addButtonsPanel(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.insets = new Insets(30, 0, 20, 0);
        panel.add(new ButtonsPanel().getComponent(), constraint);
    }

}
