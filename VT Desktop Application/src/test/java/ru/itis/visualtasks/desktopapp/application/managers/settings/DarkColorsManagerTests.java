package ru.itis.visualtasks.desktopapp.application.managers.settings;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;

import java.awt.*;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class DarkColorsManagerTests {

    @BeforeAll
    public static void beforeAll(){
        ColorsManager.setTheme(Theme.DARK);
    }

    @Test
    public void get_text_color(){
        Assertions.assertEquals(Color.WHITE, ColorsManager.getTextColor());
    }

    @Test
    public void get_button_borders_color(){
        Assertions.assertEquals(Color.decode("#6A5ACD"), ColorsManager.getButtonBordersColor());
    }

    @Test
    public void get_borders_color(){
        Assertions.assertEquals(Color.decode("#293133"), ColorsManager.getBordersColor());
    }

    @Test
    public void get_panel_background_color(){
        Assertions.assertEquals(new Color(70, 68, 81, 200), ColorsManager.getPanelBackgroundColor());
    }

    @Test
    public void get_workspace_background_color(){
        Assertions.assertEquals(new Color(47, 53, 59, 200), ColorsManager.getWorkspaceBackgroundColor());
    }

}
