<#if content.packageValue ??>
package ${content.packageValue};

</#if>
<#list content.imports as import>
import ${import};
</#list>
<#if content.imports?size != 0>

</#if>
<#if content.generalComment ??>
/*
    ${content.generalComment}
*/
</#if>
public class VisualizationScenePanel extends VisualizationSceneJavaFxPanelScheme {

    public VisualizationScenePanel(JFXPanel panel){
        super(panel);
    }

    <#if content.buttonFlagsComment ??>
    /*
        ${content.buttonFlagsComment}
    */
    </#if>
    @Override
    protected void adjustControlButtons() {
        isStartEndButtonsEnabled = true;
        isForwardBackStepButtonsEnabled = true;
        isPlayPauseButtonsEnabled = true;
    }

    @Override
    protected void adjustEdgesCommands() {
        inInitialStateCommand = null;
        initialStepDelay = 0;
        atSceneStartCommand = null;
        atStartStepDelay = 0;
        atSceneEndCommand = null;
        atEndStepDelay = 0;
    }

    @Override
    protected void configureScene() {
        scene = null;
    }

    @Override
    protected void addComponents() {

    }

}
