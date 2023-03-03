package ru.itis.graduationwork.application.ui.core.templates;

import javax.swing.*;

public abstract class TextArea implements Component {

    protected JTextArea textArea;

    protected TextArea(){
        textArea = new JTextArea();
        initTextArea();
    }

    protected abstract void initTextArea();

    @Override
    public JComponent getComponent(){
        return textArea;
    }

}
