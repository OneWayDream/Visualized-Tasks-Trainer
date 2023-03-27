package ru.itis.visualtasks.desktopapp.application.loaders;

import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.utils.ExceptionsManager;
import ru.itis.visualtasks.desktopapp.application.ui.core.ide.workspace.ContentTab;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileNotFoundException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.files.UnsupportedContentFileExtensionException;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnexpectedContentTabException;

public class ContentLoader {

    public static String loadContent(ContentTab contentTab){
        String filePath = getContentFilePathForTab(contentTab);
        try {
            String fileContent = null;
            if (filePath != null){
                fileContent = FilesManager.loadFileContent(filePath);
                fileContent = prepareFileContent(fileContent, getFileExtension(filePath));
            }
            return fileContent;
        } catch (FileNotFoundException exception){
            ExceptionsManager.addDelayedException(() -> ExceptionsManager.handleFileNotFoundException(filePath));
            resetContentFilePathForTab(contentTab);
        } catch (FileReadingException exception){
            ExceptionsManager.addDelayedException(() -> ExceptionsManager.handleFileReadingException(filePath));
            resetContentFilePathForTab(contentTab);
        } catch (UnexpectedContentTabException exception){
            exception.handle();
        } catch (UnsupportedContentFileExtensionException exception){
            exception.handle();
            resetContentFilePathForTab(contentTab);
        }
        return null;
    }

    private static String getContentFilePathForTab(ContentTab contentTab) {
        String filePath;
        switch (contentTab){
            case GENERAL_DESCRIPTION -> filePath = ConfigManager.getGeneralDescriptionFilePath();
            case STUDYING_CONTENT -> filePath = ConfigManager.getStudyingContentFilePath();
            case TASK_TERMS -> filePath = ConfigManager.getTaskTermsFilePath();
            default -> throw new UnexpectedContentTabException();
        }
        return filePath;
    }

    private static String getFileExtension(String filePath){
        String[] filePathParts = filePath.split("\\.");
        return filePathParts[filePathParts.length - 1].toLowerCase();
    }

    private static String prepareFileContent(String content, String extension){
        if (extension.equals("html")){
            return content;
        } else if (extension.equals("md")){
            return markDownToHtml(content);
        } else {
            throw new UnsupportedContentFileExtensionException();
        }
    }

    private static String markDownToHtml(String markDownContent){
        return com.github.rjeschke.txtmark.Processor.process(markDownContent);
    }

    private static void resetContentFilePathForTab(ContentTab contentTab){
        switch (contentTab){
            case GENERAL_DESCRIPTION -> ConfigManager.setGeneralDescriptionFilePath(null);
            case STUDYING_CONTENT -> ConfigManager.setStudyingContentFilePath(null);
            case TASK_TERMS -> ConfigManager.setTaskTermsFilePath(null);
        }
    }

}
