package ru.itis.graduationwork.application.managers;

import ru.itis.graduationwork.exceptions.application.NotInitializedThemeException;
import ru.itis.graduationwork.application.settings.units.Theme;

import java.awt.*;

public class ColorsManager {

    private static Theme currentTheme;
    private static Color BUTTON_TEXT_COLOR;
    private static Color BUTTON_BORDER_COLOR;
    private static Color BORDERS_COLOR;

    public static Color getTextColor(){
        if (currentTheme == null){
            throw new NotInitializedThemeException();
        }
        return BUTTON_TEXT_COLOR;
    }

    public static Color getButtonBordersColor(){
        if (currentTheme == null){
            throw new NotInitializedThemeException();
        }
        return BUTTON_BORDER_COLOR;
    }

    public static void setTheme(Theme theme){
        currentTheme = theme;
        if (theme == Theme.DARK){
            initDarkModeColors();
        } else {
            initLightModeColors();
        }
    }

    public static Color getBordersColor(){
        if (currentTheme == null){
            throw new NotInitializedThemeException();
        }
        return BORDERS_COLOR;
    }

    private static void initDarkModeColors(){
        BUTTON_TEXT_COLOR = Color.WHITE;
        BUTTON_BORDER_COLOR = Color.decode("#6A5ACD");
        BORDERS_COLOR = Color.WHITE;
    }

    private static void initLightModeColors(){
        BUTTON_TEXT_COLOR = Color.BLACK;
        BUTTON_BORDER_COLOR = Color.decode("#990066");
        BORDERS_COLOR = Color.BLACK;
    }

}
