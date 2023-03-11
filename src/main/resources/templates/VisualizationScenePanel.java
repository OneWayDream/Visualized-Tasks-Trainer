package visualization;

import ru.itis.graduationwork.application.ui.core.ide.visualization.core.VisualizationScenePanelScheme;
import ru.itis.graduationwork.application.ui.core.ide.workspace.ButtonsColumnPanel;

import javax.swing.*;
import java.awt.*;

public class VisualizationScenePanel extends VisualizationScenePanelScheme {

    public VisualizationScenePanel(JPanel jpanel){
        super(jpanel);
    }

    @Override
    protected void setPanelStyle() {
        panel.setBackground(Color.YELLOW);
    }

    @Override
    protected void addComponents() {

    }

    public void executeCommand(String command){

    }

}