package ru.itis.graduationwork.application.managers;

import ru.itis.graduationwork.application.ui.core.PageType;
import ru.itis.graduationwork.application.ui.core.templates.PageFrame;
import ru.itis.graduationwork.application.ui.pages.main.MainPageFrame;

public class PagesManager {

    public static PageType getStartPageType(){
        return PageType.MAIN;
    }

    public static PageFrame getPage(PageType pageType){
        return new MainPageFrame();
    }

}
