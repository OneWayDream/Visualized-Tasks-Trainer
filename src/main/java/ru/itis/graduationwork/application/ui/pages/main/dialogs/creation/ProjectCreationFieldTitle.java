package ru.itis.graduationwork.application.ui.pages.main.dialogs.creation;

import ru.itis.graduationwork.application.managers.ColorsManager;
import ru.itis.graduationwork.application.ui.core.templates.Label;

import java.awt.*;

public class ProjectCreationFieldTitle extends Label {

    private final String title;

    public ProjectCreationFieldTitle(String title){
        super();
        this.title = title;
        createLabel();
    }

    @Override
    protected void setUpLabel() {
        label.setText(title);
        label.setFont(new Font("Comic Sans", Font.ITALIC, 16));
        label.setForeground(ColorsManager.getTextColor());
    }

}
