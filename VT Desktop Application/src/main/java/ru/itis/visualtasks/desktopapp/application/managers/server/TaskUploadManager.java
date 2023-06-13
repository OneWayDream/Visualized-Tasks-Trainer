package ru.itis.visualtasks.desktopapp.application.managers.server;

import ru.itis.visualtasks.desktopapp.application.entities.project.ProjectConfig;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.exceptions.project.ProjectConfigReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.server.*;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnsupportedLanguageException;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnsupportedVisualizationTypeException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TaskUploadManager {

    public static void uploadCurrentTask() {
        uploadCurrentTask(false);
    }

    private static void uploadCurrentTask(boolean isRetry){
        byte[] taskContent = getTaskContent();
        try {
            TaskSender.uploadTask(
                    TokensManager.getAccessToken(),
                    taskContent
            );
        } catch (BannedTokenException | ExpiredTokenException | IncorrectTokenException exception) {
            if (!isRetry){
                TokensManager.updateAccessToken();
                uploadCurrentTask(true);
            }
            throw new AuthenticationRequestExecutionException();
        } catch (UnsupportedLanguageException | UnsupportedTaskArchiveExtensionException |
                 UnsupportedVisualizationTypeException exception){
            throw new ApiDifferenceException(exception);
        } catch (ProjectConfigReadingException | ConfigNotFoundException exception){
            ConfigManager.saveConfigFile();
            throw new ConfigInteractionException();
        }
    }

    private static byte[] getTaskContent() {
        checkConfigFile();
        return archiveFileContent();
    }

    private static void checkConfigFile() {
        if (!checkIsConfigFileValid()) {
            throw new ConfigIsIncompleteException();
        }
    }

    private static boolean checkIsConfigFileValid() {
        ProjectConfig projectConfig = ConfigManager.getConfig();
        return projectConfig.getGeneralDescriptionFilePath() != null
                && projectConfig.getStudyingContentFilePath() != null
                && projectConfig.getTaskTermsFilePath() != null;
    }

    private static byte[] archiveFileContent() {
        String folderPath = ConfigManager.getProjectPath();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            File rootFolder = new File(folderPath);
            zipFolder(rootFolder, rootFolder.getName(), zipOutputStream);
        } catch (IOException exception) {
            throw new ArchiveCreationException(exception);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static void zipFolder(File folder, String parentFolder, ZipOutputStream zipOutputStream) throws IOException {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            String filePath = parentFolder + "/" + file.getName();
            if (file.isDirectory()) {
                zipFolder(file, filePath, zipOutputStream);
            } else {
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    zipOutputStream.putNextEntry(new ZipEntry(filePath));
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fileInputStream.read(buffer)) > 0) {
                        zipOutputStream.write(buffer, 0, length);
                    }
                }
            }
        }
    }

}
