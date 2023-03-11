package ru.itis.graduationwork.application.ui.core.ide.explorer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.*;

@Getter
@AllArgsConstructor
public class IconData {

    private ImageIcon icon;
    private ImageIcon expandedIcon;
    private Object data;

    @Override
    public String toString() {
        return data.toString();
    }

}
