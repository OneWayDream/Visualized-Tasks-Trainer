package ru.itis.graduationwork.application.ui.core.ide.visualization.components;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.project.ProjectFilesManager;
import ru.itis.graduationwork.application.managers.project.VisualizationProjectClassesManager;
import ru.itis.graduationwork.application.managers.settings.ColorsManager;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.application.ui.core.templates.Panel;
import ru.itis.graduationwork.exceptions.files.FileNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class VisualizationPanel extends Panel {

    private JComponent visualizationScene;

    public VisualizationPanel(){
        super();
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
            visualizationScene = VisualizationProjectClassesManager.getVisualizationScene();
            panel.add(visualizationScene, constraint);
        } catch (FileNotFoundException exception){
            tryToRecreateVisualizationFiles(constraint);
        } catch (Exception exception){
            addMockVisualizationScene(constraint);
        }
    }

    private void tryToRecreateVisualizationFiles(GridBagConstraints constraint){
        try{
            ProjectFilesManager.createDevelopProjectFiles(ConfigManager.getConfig());
            visualizationScene = VisualizationProjectClassesManager.getVisualizationScene();
            panel.add(visualizationScene, constraint);
            ExceptionsManager.addDelayedException(
                ExceptionsManager::handleVisualizationFileNotFoundException, 200, TimeUnit.MILLISECONDS
            );
        } catch (Exception exception){
            addMockVisualizationScene(constraint);
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleVisualizationFilesRecoveryException, 200, TimeUnit.MILLISECONDS
            );
        }
    }

    private void addMockVisualizationScene(GridBagConstraints constraint){
        visualizationScene = new VisualizationSceneMock().getComponent();
        panel.add(visualizationScene, constraint);
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
        if (visualizationScene != null){
            panel.remove(visualizationScene);
        }
        addVisualizationScenePanel();
        panel.validate();
        panel.repaint();
    }

}
