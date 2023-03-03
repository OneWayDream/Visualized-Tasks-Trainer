package ru.itis.graduationwork.application.ui.pages.develop.panels;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.ui.core.templates.Panel;
import ru.itis.graduationwork.application.ui.pages.develop.buttons.tabs.OpenFileRedactorButton;
import ru.itis.graduationwork.application.ui.pages.develop.buttons.tabs.OpenGeneralDescriptionButton;
import ru.itis.graduationwork.application.ui.pages.develop.buttons.tabs.OpenStudyingContentButton;
import ru.itis.graduationwork.application.ui.pages.develop.buttons.tabs.OpenTaskTermsButton;

import java.awt.*;

public class ButtonsColumnPanel extends Panel {

    public ButtonsColumnPanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension((int) (Application.getCurrentPageFrame().getWidth() * 0.1),
                0));
    }

    @Override
    protected void addComponents() {
        addOpenGeneralDescriptionButton();
        addOpenStudyingContentButton();
        addOpenTaskTermsButton();
        addOpenCodeIdeButton();
    }

    private void addOpenGeneralDescriptionButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridy = 0;
        constraint.gridx = 0;
        constraint.weightx = 1;
        constraint.insets = new Insets(10, 0, 5, 0);
        panel.add(new OpenGeneralDescriptionButton().getComponent(), constraint);
    }

    private void addOpenStudyingContentButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridy = 1;
        constraint.gridx = 0;
        constraint.weightx = 1;
        constraint.insets = new Insets(5, 0, 5, 0);
        panel.add(new OpenStudyingContentButton().getComponent(), constraint);
    }

    private void addOpenTaskTermsButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridy = 2;
        constraint.gridx = 0;
        constraint.weightx = 1;
        constraint.insets = new Insets(5, 0, 5, 0);
        panel.add(new OpenTaskTermsButton().getComponent(), constraint);
    }

    private void addOpenCodeIdeButton() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridy = 3;
        constraint.gridx = 0;
        constraint.weightx = 1;
        constraint.insets = new Insets(5, 0, 10, 0);
        panel.add(new OpenFileRedactorButton().getComponent(), constraint);
    }

}
