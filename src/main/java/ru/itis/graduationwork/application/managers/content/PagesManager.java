package ru.itis.graduationwork.application.managers.content;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.ui.core.PageType;
import ru.itis.graduationwork.application.ui.core.templates.PageFrame;
import ru.itis.graduationwork.application.ui.pages.develop.DevelopPageFrame;
import ru.itis.graduationwork.application.ui.pages.main.MainPageFrame;
import ru.itis.graduationwork.application.ui.pages.study.StudyPageFrame;
import ru.itis.graduationwork.exceptions.project.UnknownPageTypeException;

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

    public static void openStartPage(){
        Application.changePage(getPage(getStartPageType()));
    }

}
