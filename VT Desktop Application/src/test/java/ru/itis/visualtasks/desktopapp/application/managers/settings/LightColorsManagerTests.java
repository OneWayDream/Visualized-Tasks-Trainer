package ru.itis.visualtasks.desktopapp.application.managers.settings;

import org.junit.jupiter.api.*;
import ru.itis.visualtasks.desktopapp.application.settings.Theme;

import java.awt.*;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class LightColorsManagerTests {

    @BeforeAll
    public static void beforeAll(){
        ColorsManager.setTheme(Theme.LIGHT);
    }

    @Test
    public void get_text_color(){
        Assertions.assertEquals(Color.BLACK, ColorsManager.getTextColor());
    }

    @Test
    public void get_button_borders_color(){
        Assertions.assertEquals(Color.decode("#990066"), ColorsManager.getButtonBordersColor());
    }

    @Test
    public void get_borders_color(){
        Assertions.assertEquals(Color.decode("#FAEEDD"), ColorsManager.getBordersColor());
    }

    @Test
    public void get_panel_background_color(){
        Assertions.assertEquals(new Color(165, 165, 165, 200), ColorsManager.getPanelBackgroundColor());
    }

    @Test
    public void get_workspace_background_color(){
        Assertions.assertEquals(new Color(	250, 238, 221, 200), ColorsManager.getWorkspaceBackgroundColor());
    }

}
