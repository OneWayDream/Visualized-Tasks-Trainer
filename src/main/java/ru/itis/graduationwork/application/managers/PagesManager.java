package ru.itis.graduationwork.application.managers;

import ru.itis.graduationwork.application.ui.core.templates.PageFrame;
import ru.itis.graduationwork.application.ui.pages.main.MainPageFrame;

public class PagesManager {

    public static PageFrame getStartPage(){
        return new MainPageFrame();
    }

}
