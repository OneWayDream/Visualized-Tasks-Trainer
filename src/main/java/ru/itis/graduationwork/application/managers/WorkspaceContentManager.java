package ru.itis.graduationwork.application.managers;

import lombok.Getter;
import ru.itis.graduationwork.application.Application;
import ru.itis.graduationwork.application.loaders.ContentLoader;
import ru.itis.graduationwork.application.ui.pages.develop.DevelopPageFrame;
import ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.ContentEditorPane;
import ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.ContentTab;
import ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.editor.FileEditorScrollPane;
import ru.itis.graduationwork.application.ui.pages.develop.panels.workspace.notselected.ContentFileChooserPanel;
import ru.itis.graduationwork.exceptions.UnexpectedContentTabException;
import ru.itis.graduationwork.exceptions.files.FileNotFoundException;
import ru.itis.graduationwork.exceptions.files.FileReadingException;

import java.awt.*;

public class WorkspaceContentManager {

    @Getter
    private static ContentTab contentTab = ContentTab.GENERAL_DESCRIPTION;
    @Getter
    private static String editorFilePath;
    private static ru.itis.graduationwork.application.ui.core.templates.Component contentComponent;

    public static Component getContentPane(){
        if (contentTab == ContentTab.FILE_EDITOR){
            return getFileEditorScrollPane();
        } else {
            return getContentEditorPane();
        }
    }

    private static Component getContentEditorPane(){
        String paneContent = ContentLoader.loadContent(contentTab);
        contentComponent = (paneContent == null) ? new ContentFileChooserPanel() : new ContentEditorPane(paneContent);
        return contentComponent.getComponent();
    }

    private static Component getFileEditorScrollPane(){
        contentComponent = (editorFilePath == null) ? new ContentFileChooserPanel() : new FileEditorScrollPane();
        return contentComponent.getComponent();
    }

    public static String getFileEditorContent(){
        try{
            return FilesManager.loadFileContent(editorFilePath);
        } catch (FileNotFoundException exception){
            ExceptionsManager.handleFileNotFoundException(editorFilePath);
        } catch (FileReadingException exception){
            ExceptionsManager.handleFileReadingException(editorFilePath);
        }
        setEditorContent(null);
        return "";
    }

    public static void setEditorContent(String filePath){
        saveEditorChangedIfNeeded();
        contentTab = ContentTab.FILE_EDITOR;
        WorkspaceContentManager.editorFilePath = filePath;
        updateWorkspaceContent();
    }

    public static void changeWorkspaceContent(ContentTab contentTab){
        saveEditorChangedIfNeeded();
        WorkspaceContentManager.contentTab = contentTab;
        updateWorkspaceContent();
    }

    public static void updateWorkspaceContent(){
        ((DevelopPageFrame) Application.getCurrentPageFrame()).updateWorkspaceContent();
    }

    private static void saveEditorChangedIfNeeded(){
        if (needToSaveChangedFile()){
            saveChangedFile();
        }
    }

    private static boolean needToSaveChangedFile(){
        return contentTab == ContentTab.FILE_EDITOR && contentComponent instanceof FileEditorScrollPane && editorFilePath != null;
    }

    private static void saveChangedFile(){
        ((FileEditorScrollPane) contentComponent).saveChanges();
    }

    public static void changeContentFilePath(String contentFilePath){
        switch (contentTab){
            case GENERAL_DESCRIPTION -> ConfigManager.setGeneralDescriptionFilePath(contentFilePath);
            case STUDYING_CONTENT -> ConfigManager.setStudyingContentFilePath(contentFilePath);
            case TASK_TERMS -> ConfigManager.setTaskTermsFilePath(contentFilePath);
            case FILE_EDITOR -> {
                setEditorContent(contentFilePath);
                return;
            }
            default -> throw new UnexpectedContentTabException();
        }
        updateWorkspaceContent();
    }

}
