package ru.itis.graduationwork.application.ui.pages.main.panels;

import ru.itis.graduationwork.application.ui.core.templates.ModeChangeable;
import ru.itis.graduationwork.application.ui.core.templates.Panel;
import ru.itis.graduationwork.application.ui.pages.main.buttons.ModeSwitchButton;
import ru.itis.graduationwork.application.ui.pages.main.labels.LeftTitleImageLabel;

import java.awt.*;

public class LeftModeTitlePanel extends Panel implements ModeChangeable {

    public LeftModeTitlePanel(){
        super();
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setLayout(new GridBagLayout());
    }

    @Override
    protected void addComponents() {
        addModeSwitchLabel();
        addImageLabel();
        addDescriptionLabel();
    }

    @Override
    public void changeMode() {
        cleanDynamicContent();
        addImageLabel();
        addDescriptionLabel();
        panel.validate();
        panel.repaint();
    }

    public void cleanDynamicContent(){
        panel.remove(2);
        panel.remove(1);
    }

    private void addModeSwitchLabel(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(new ModeSwitchButton().getComponent(), constraint);
    }

    private void addImageLabel(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridheight = 4;
        constraint.gridx = 0;
        constraint.gridy = 1;
        panel.add(new LeftTitleImageLabel().getComponent(), constraint);
    }

    private void addDescriptionLabel(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridx = 0;
        constraint.gridy = 5;
        panel.add(new LeftDescriptionLabel().getComponent(), constraint);
    }

}
