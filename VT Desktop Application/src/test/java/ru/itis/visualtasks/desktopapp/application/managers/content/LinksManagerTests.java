package ru.itis.visualtasks.desktopapp.application.managers.content;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.itis.visualtasks.desktopapp.application.settings.Link;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class LinksManagerTests {

    @ParameterizedTest
    @EnumSource(Link.class)
    public void get_link_value(Link link){
        Assertions.assertNotNull(LinksManager.getLinkValue(link));
    }

}
