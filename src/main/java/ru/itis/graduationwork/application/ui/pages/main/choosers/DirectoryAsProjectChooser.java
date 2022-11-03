package ru.itis.graduationwork.application.ui.pages.main.choosers;

import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.ui.core.templates.Chooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DirectoryAsProjectChooser extends Chooser {

    public DirectoryAsProjectChooser(){
        super();
    }

    @Override
    protected void init(){
        super.init();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        chooser.setDialogTitle("Select the project folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
    }

    @Override
    public void execute(){
        if (chooser.showOpenDialog(getCurrentFrame()) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
        }
        else {
            System.out.println("No Selection ");
        }
    }

    private Component getCurrentFrame(){
        return Application.getCurrentPageFrame().getComponent();
    }

}
