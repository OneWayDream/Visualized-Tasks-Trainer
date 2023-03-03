package ru.itis.graduationwork.application.ui.core.ide;

import lombok.Setter;

import java.awt.*;

public class IdeBackgroundPanel extends Panel {

    @Setter
    private Image image;

    @Override
    public void paint(Graphics g) {
        if (image !=  null){
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
        super.paint(g);
    }

}
