package ru.itis.visualtasks.desktopapp.application.managers.project.visualization.buttons.chain;

import ru.itis.visualtasks.desktopapp.application.ui.core.ide.visualization.buttons.VisualizationButtonType;

public class VisualizationControlButtonsDisableReasonManager {

    private static DisableReasonChainLink headLink;
    private static DisableReasonChainLink currentLink;

    static {
        initChain();
    }

    public static void changeLocale(){
        clearChain();
        initChain();
    }

    private static void clearChain(){
        headLink = null;
        currentLink = null;
    }

    private static void initChain(){
        addDisableReasonChainLink(new SolutionFileNotExecutedChainLink());
        addDisableReasonChainLink(new NonAnimationChainLink());
        addDisableReasonChainLink(new DisabledButtonChainLink());
        addDisableReasonChainLink(new NotPlayingForPauseChainLink());
        addDisableReasonChainLink(new PlayingSceneChainLink());
        addDisableReasonChainLink(new NoPreviousStepsChainLink());
        addDisableReasonChainLink(new NoNextStepsChainLink());
    }

    private static void addDisableReasonChainLink(DisableReasonChainLink disableReasonChainLink){
        if (headLink == null){
            headLink = disableReasonChainLink;
            currentLink = headLink;
        } else {
            currentLink.setNextLink(disableReasonChainLink);
            currentLink = disableReasonChainLink;
        }
    }

    public static String getDisableReason(VisualizationButtonType buttonType){
        return headLink.execute(buttonType);
    }

}
