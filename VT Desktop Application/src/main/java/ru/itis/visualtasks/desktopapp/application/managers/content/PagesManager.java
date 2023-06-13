package ru.itis.visualtasks.desktopapp.application.managers.content;

import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.ui.core.PageType;
import ru.itis.visualtasks.desktopapp.application.ui.core.templates.PageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.pages.develop.DevelopPageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.pages.main.MainPageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.pages.study.StudyPageFrame;
import ru.itis.visualtasks.desktopapp.exceptions.project.UnknownPageTypeException;

public class PagesManager {

    public static PageType getStartPageType(){
        return PageType.MAIN;
    }

    public static PageFrame getPage(PageType pageType){
        PageFrame pageFrame;
        switch (pageType){
            case MAIN -> pageFrame = new MainPageFrame();
            case DEVELOP -> pageFrame = new DevelopPageFrame();
            case STUDY -> pageFrame = new StudyPageFrame();
            default -> throw new UnknownPageTypeException();
        }
        return pageFrame;
    }

    public static void openPage(PageFrame pageFrame){
        Application.changePage(pageFrame);
    }

}
