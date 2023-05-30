package visualization;

import javafx.scene.paint.Color;
import ru.itis.visualtasks.application.ui.core.ide.visualization.core.VisualizationSceneJavaFxPanelScheme;
import javafx.embed.swing.JFXPanel;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.application.Platform;

/*
    Use this class to customize your panel as a component (setting styles and adding internal components). 
To access the JFXPanel component (we'll pass an instance of your CustomPanel to the constructor),
override the 'configureScene' method using 'scene' variable.

*/
public class VisualizationScenePanel extends VisualizationSceneJavaFxPanelScheme {

    private BorderPane borderPane;

    public VisualizationScenePanel(JFXPanel panel){
        super(panel);
    }

    /*
        Use this method to configure the visualisation control buttons (you can disable some of them 
if you don't have an implementation for these actions).
    */
    @Override
    protected void adjustControlButtons() {
        isStartEndButtonsEnabled = true;
        isForwardBackStepButtonsEnabled = true;
        isPlayPauseButtonsEnabled = true;
    }

    @Override
    protected void adjustEdgesCommands() {
        inInitialStateCommand = "initialStyle";
        initialStepDelay = 1000;
        atSceneStartCommand = "initialStyle";
        atStartStepDelay = 1000;
        atSceneEndCommand = "thirdAnimation";
        atEndStepDelay = 1000;
    }

    @Override
    protected void configureScene() {
        borderPane = new BorderPane();
        scene = new Scene(borderPane);
    }

    @Override
    protected void addComponents() {
    }

    public void initialStyle(){
        Platform.runLater(() -> {
            borderPane.setCenter(new TextField("Init"));
            System.out.println("Init");
        });
    }

    public void firstAnimation(){
        Platform.runLater(() -> {
            borderPane.setCenter(new TextField("Step 1"));
            System.out.println("Step 1");
        });
    }

    public void secondAnimation(){
        Platform.runLater(() -> {
            borderPane.setCenter(new TextField("Step 2"));
            System.out.println("Step 2");
        });
    }

    public void thirdAnimation(){
        Platform.runLater(() -> {
            borderPane.setCenter(new TextField("Step 3"));
            System.out.println("Step 3");
        });
    }

}
