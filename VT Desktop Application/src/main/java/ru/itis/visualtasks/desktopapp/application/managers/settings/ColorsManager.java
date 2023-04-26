package ru.itis.visualtasks.desktopapp.application.managers.settings;

import ru.itis.visualtasks.desktopapp.application.settings.Theme;

import java.awt.*;

public class ColorsManager {

    private static Color BUTTON_TEXT_COLOR;
    private static Color BUTTON_BORDER_COLOR;
    private static Color BORDERS_COLOR;
    private static Color PANEL_BACKGROUND_COLOR;
    private static Color WORKSPACE_BACKGROUND_COLOR;

    public static Color getTextColor(){
        return BUTTON_TEXT_COLOR;
    }

    public static Color getButtonBordersColor(){
        return BUTTON_BORDER_COLOR;
    }

    public static void setTheme(Theme theme){
        if (theme == Theme.DARK){
            initDarkModeColors();
        } else {
            initLightModeColors();
        }
    }

    public static Color getBordersColor(){
        return BORDERS_COLOR;
    }

    public static Color getPanelBackgroundColor(){
        return PANEL_BACKGROUND_COLOR;
    }

    public static Color getWorkspaceBackgroundColor(){
        return WORKSPACE_BACKGROUND_COLOR;
    }

    private static void initDarkModeColors(){
        BUTTON_TEXT_COLOR = Color.WHITE;
        BUTTON_BORDER_COLOR = Color.decode("#6A5ACD");
        BORDERS_COLOR = Color.decode("#293133");
        PANEL_BACKGROUND_COLOR = new Color(70, 68, 81, 200);
        WORKSPACE_BACKGROUND_COLOR = new Color(47, 53, 59, 200);
    }

    private static void initLightModeColors(){
        BUTTON_TEXT_COLOR = Color.BLACK;
        BUTTON_BORDER_COLOR = Color.decode("#990066");
        BORDERS_COLOR = Color.decode("#FAEEDD");
        PANEL_BACKGROUND_COLOR = new Color(165, 165, 165, 200);
        WORKSPACE_BACKGROUND_COLOR = new Color(	250, 238, 221, 200);
    }

}
