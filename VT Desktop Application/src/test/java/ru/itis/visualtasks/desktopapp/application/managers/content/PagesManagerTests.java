package ru.itis.visualtasks.desktopapp.application.managers.content;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.core.TestPageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.core.PageType;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.PageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.pages.develop.DevelopPageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.MainPageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.pages.study.StudyPageFrame;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class PagesManagerTests {

    @Test
    public void get_start_page(){
        Assertions.assertEquals(PageType.MAIN, PagesManager.getStartPageType());
    }

    @ParameterizedTest
    @EnumSource(PageType.class)
    public void get_page_by_type(PageType pageType){
        PageFrame pageFrame = PagesManager.getPage(pageType);
        Assertions.assertEquals(getPageClassByPageType(pageType), pageFrame.getClass());
    }

    private Class<?> getPageClassByPageType(PageType pageType){
        Class<?> pageClass = null;
        switch (pageType){
            case MAIN -> pageClass = MainPageFrame.class;
            case STUDY -> pageClass = StudyPageFrame.class;
            case DEVELOP -> pageClass = DevelopPageFrame.class;
        }
        return pageClass;
    }

    @Test
    public void open_page(){
        PagesManager.openPage(new TestPageFrame());
        Assertions.assertNotNull(Application.getCurrentPageFrame());
        Assertions.assertEquals(TestPageFrame.class, Application.getCurrentPageFrame().getClass());
    }

}
