package ru.itis.visualtasks.desktopapp.application.ui.pages.main.panels;

import ru.itis.visualtasks.desktopapp.application.ui.core.templates.ModeChangeable;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Panel;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.labels.RightTitleImageLabel;

import java.awt.*;

public class RightMainPanel extends Panel implements ModeChangeable {

    private RightTitleImageLabel rightTitleImageLabel;
    private RightButtonsPanel rightButtonsPanel;

    public RightMainPanel(){
        super();
        createPanel();
    }
    protected void setPanelStyle(){
        panel.setLayout(new GridBagLayout());
    }

    @Override
    protected void addComponents() {
        addButtonsPanel();
        addTitleImageLabel();
    }

    private void addButtonsPanel(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridheight=2;
        constraint.insets = new Insets(0, 0, 50, 0);
        rightButtonsPanel = new RightButtonsPanel();
        panel.add(rightButtonsPanel.getComponent(), constraint);
    }

    private void addTitleImageLabel(){
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.CENTER;
        constraint.gridx = 0;
        constraint.gridy = 2;
        rightTitleImageLabel = new RightTitleImageLabel();
        panel.add(rightTitleImageLabel.getComponent(), constraint);
    }

    @Override
    public void changeMode(){
        panel.repaint();
        rightTitleImageLabel.changeMode();
        rightButtonsPanel.changeMode();
    }

}
