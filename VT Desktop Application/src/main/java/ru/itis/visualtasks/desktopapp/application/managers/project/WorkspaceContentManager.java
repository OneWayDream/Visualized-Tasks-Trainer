package ru.itis.visualtasks.desktopapp.application.managers.project;

import lombok.Getter;
import ru.itis.visualtasks.desktopapp.application.Application;
import ru.itis.visualtasks.desktopapp.application.loaders.ContentLoader;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.settings.Mode;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.IdePageFrame;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ContentEditorPane;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ContentScrollPane;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ContentTab;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.editor.FileEditorScrollPane;
import ru.itis.visualtasks.desktopapp.application.ui.pages.develop.panels.workspace.notselected.ContentFileChooserPanel;
import ru.itis.visualtasks.desktopapp.application.ui.pages.study.panels.workspace.notselected.NotSelectedFilePanel;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileNotFoundException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnexpectedContentTabException;

import java.awt.*;

public class WorkspaceContentManager {

    @Getter
    private static ContentTab contentTab = ContentTab.GENERAL_DESCRIPTION;
    @Getter
    private static String editorFilePath;
    private static ru.itis.visualtasks.desktopapp.application.ui.core.templates.Component contentComponent;

    public static Component getContentPane(){
        if (contentTab == ContentTab.FILE_EDITOR){
            return getFileEditorScrollPane();
        } else {
            return getContentEditorPane();
        }
    }

    private static Component getContentEditorPane(){
        String paneContent = ContentLoader.loadContent(contentTab);
        contentComponent = (paneContent == null) ? getNoContentPanel()
                : new ContentScrollPane(new ContentEditorPane(paneContent));
        return contentComponent.getComponent();
    }

    private static ru.itis.visualtasks.desktopapp.application.ui.core.templates.Component getNoContentPanel(){
        if (Application.getMode() == Mode.DEVELOP){
            return new ContentFileChooserPanel();
        } else {
            return new NotSelectedFilePanel();
        }
    }

    private static Component getFileEditorScrollPane(){
        contentComponent = (editorFilePath == null) ? new ContentFileChooserPanel() : new FileEditorScrollPane();
        return contentComponent.getComponent();
    }

    public static String getFileEditorContent(){
        try{
            return FilesManager.readFileAsString(editorFilePath);
        } catch (FileNotFoundException | FileReadingException exception){
            exception.handle();
        }
        setEditorContent(null);
        return "";
    }

    public static void setEditorContent(String filePath){
        saveEditorChangesIfNeeded();
        contentTab = ContentTab.FILE_EDITOR;
        WorkspaceContentManager.editorFilePath = filePath;
        updateWorkspaceContent();
    }

    public static void changeWorkspaceContent(ContentTab contentTab){
        saveEditorChangesIfNeeded();
        WorkspaceContentManager.contentTab = contentTab;
        updateWorkspaceContent();
    }

    public static void updateWorkspaceContent(){
        ((IdePageFrame) Application.getCurrentPageFrame()).updateWorkspaceContent();
    }

    public static void saveEditorChangesIfNeeded(){
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

    public static void reset(){
        contentTab = ContentTab.GENERAL_DESCRIPTION;
        editorFilePath = null;
        contentComponent = null;
    }

    public static void notifyAboutFileDeletion(String deletedFilePath){
        checkIfGeneralDescriptionFileWasDeleted(deletedFilePath);
        checkIfStudyingContentFileWasDeleted(deletedFilePath);
        checkIfTaskTermsFileWasDeleted(deletedFilePath);
        checkIfEditorContentWasDeleted(deletedFilePath);
    }

    private static void checkIfGeneralDescriptionFileWasDeleted(String deletedFilePath){
        if (deletedFilePath.equals(ConfigManager.getGeneralDescriptionFilePath())){
            ConfigManager.setGeneralDescriptionFilePath(null);
            updateWorkspaceContent();
        }
    }

    private static void checkIfStudyingContentFileWasDeleted(String deletedFilePath){
        if (deletedFilePath.equals(ConfigManager.getStudyingContentFilePath())){
            ConfigManager.setStudyingContentFilePath(null);
            updateWorkspaceContent();
        }
    }

    private static void checkIfTaskTermsFileWasDeleted(String deletedFilePath){
        if (deletedFilePath.equals(ConfigManager.getTaskTermsFilePath())){
            ConfigManager.setTaskTermsFilePath(null);
            updateWorkspaceContent();
        }
    }

    private static void checkIfEditorContentWasDeleted(String deletedFilePath){
        if (deletedFilePath.equals(editorFilePath)){
            editorFilePath = null;
            updateWorkspaceContent();
        }
    }

}
