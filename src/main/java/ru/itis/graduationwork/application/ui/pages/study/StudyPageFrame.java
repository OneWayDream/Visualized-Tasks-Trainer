package ru.itis.graduationwork.application.ui.pages.study;

import ru.itis.graduationwork.application.ui.core.PageType;
import ru.itis.graduationwork.application.ui.core.ide.IdePageFrame;
import ru.itis.graduationwork.application.ui.core.templates.PageFrame;

public class StudyPageFrame extends IdePageFrame {

    public StudyPageFrame(){
        super();
        pageType = PageType.STUDY;
    }

    @Override
    protected void initFields() {
        width = getDeviceScreenWidth()*3 / 4;
        height = getDeviceScreenHeight()*3 / 4;
    }

    @Override
    public PageFrame copy() {
        StudyPageFrame studyPageFrame = new StudyPageFrame();
        studyPageFrame.setProjectConfig(projectConfig);
        return studyPageFrame;
    }

    @Override
    protected void addComponents() {

    }

}
