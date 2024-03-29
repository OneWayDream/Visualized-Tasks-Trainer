package ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.components;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.managers.project.ProjectFilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.ProjectVisualizationClassesManager;
import ru.itis.visualtasks.desktopapp.application.managers.project.visualization.VisualizationSceneController;
import ru.itis.visualtasks.desktopapp.application.managers.settings.ColorsManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.Panel;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileNotFoundException;
import ru.itis.visualtasks.desktopapp.exceptions.project.VisualizationFileNotFoundException;
import ru.itis.visualtasks.desktopapp.exceptions.project.VisualizationFilesRecoveryException;

import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends Panel {

    private JComponent visualizationScenePanel;

    public VisualizationPanel(){
        super();
        VisualizationSceneController.setVisualizationPanel(this);
        createPanel();
    }

    @Override
    protected void setPanelStyle() {
        panel.setBorder((BorderFactory.createMatteBorder(5, 5, 5, 5,
                ColorsManager.getBordersColor())));
        panel.setPreferredSize(new Dimension((int) (Application.getCurrentPageFrame().getWidth() * 0.35),
                0));
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());

    }

    @Override
    protected void addComponents() {
        addVisualizationScenePanel();
        addVisualizationControlPanel();
    }

    private void addVisualizationScenePanel() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridy = 0;
        constraint.gridx = 0;
        constraint.weightx = 1;
        constraint.weighty = 0.75;
        try{
            visualizationScenePanel = ProjectVisualizationClassesManager.getVisualizationScene();
            panel.add(visualizationScenePanel, constraint);
        } catch (FileNotFoundException exception){
            tryToRecreateVisualizationFiles(constraint);
        } catch (Exception exception){
            addMockVisualizationScene(constraint);
        }
    }

    private void tryToRecreateVisualizationFiles(GridBagConstraints constraint){
        try{
            ProjectFilesManager.createVisualizationFiles();
            visualizationScenePanel = ProjectVisualizationClassesManager.getVisualizationScene();
            panel.add(visualizationScenePanel, constraint);
            new VisualizationFileNotFoundException().handle();
        } catch (Exception exception){
            addMockVisualizationScene(constraint);
            new VisualizationFilesRecoveryException().handle();
        }
    }

    private void addMockVisualizationScene(GridBagConstraints constraint){
        panel.add(new VisualizationSceneMock().getComponent(), constraint);
    }

    private void addVisualizationControlPanel() {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.gridy = 1;
        constraint.gridx = 0;
        constraint.weightx = 1;
        constraint.weighty = 0.25;
        panel.add(new VisualizationControlPanel().getComponent(), constraint);
    }

    public void updateContent(){
        if (visualizationScenePanel != null){
            panel.remove(visualizationScenePanel);
        }
        addVisualizationScenePanel();
        panel.validate();
        panel.repaint();
    }

}
