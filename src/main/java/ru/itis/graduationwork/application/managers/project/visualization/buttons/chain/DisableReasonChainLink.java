package ru.itis.graduationwork.application.managers.project.visualization.buttons.chain;

import lombok.Setter;
import lombok.ToString;
import ru.itis.graduationwork.application.ui.core.ide.visualization.buttons.VisualizationButtonType;
import ru.itis.graduationwork.exceptions.project.NoDisableReasonException;

@ToString
public abstract class DisableReasonChainLink {

    @Setter
    protected DisableReasonChainLink nextLink;
    protected String reason;

    public String execute(VisualizationButtonType buttonType){
        if (checkCondition(buttonType)){
            return reason;
        }
        if (nextLink == null){
            throw new NoDisableReasonException();
        }
        return nextLink.execute(buttonType);
    }

    protected abstract boolean checkCondition(VisualizationButtonType buttonType);

}
