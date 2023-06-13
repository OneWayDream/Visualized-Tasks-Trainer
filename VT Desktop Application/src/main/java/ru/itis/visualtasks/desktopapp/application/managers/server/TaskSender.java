package ru.itis.visualtasks.desktopapp.application.managers.server;

import okhttp3.*;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.exceptions.project.ProjectConfigReadingException;
import ru.itis.visualtasks.desktopapp.exceptions.server.*;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnsupportedLanguageException;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnsupportedVisualizationTypeException;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class TaskSender {

    private static final OkHttpClient client;
    private static final String TASK_UPLOAD_URL;
    private static final String TASK_UPLOAD_TOKEN_HEADER_NAME;

    public static final MediaType TASK_MEDIA_TYPE
            = MediaType.parse("application/x-zip-compressed; charset=utf-8");

    static {
        client = new OkHttpClient();
        Properties properties = PropertiesUtils.getInstance().getProperties();
        TASK_UPLOAD_URL = properties.getProperty("task-upload-url");
        TASK_UPLOAD_TOKEN_HEADER_NAME = properties.getProperty("task-upload-token-header-name");
    }

    public static void uploadTask(String accessToken, byte[] content){
        Response response = null;
        try{
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("task", getTaskArchiveName(),
                            RequestBody.create(content, TASK_MEDIA_TYPE))
                    .build();
            Request request = new Request.Builder()
                    .url(TASK_UPLOAD_URL)
                    .header(TASK_UPLOAD_TOKEN_HEADER_NAME, accessToken)
                    .post(requestBody)
                    .build();
            response = client.newCall(request).execute();
            int httpCode = response.code();
            switch (httpCode) {
                case 200 -> {
                    // The task has been uploaded.
                }
                case 452 -> throw new BannedTokenException();
                case 453 -> throw new ExpiredTokenException();
                case 454 -> throw new IncorrectTokenException();
                case 458 -> throw new UnsupportedLanguageException();
                case 459 -> throw new UnsupportedTaskArchiveExtensionException();
                case 460 -> throw new UnsupportedVisualizationTypeException();
                case 461 -> throw new TaskArchiveReadingException();
                case 462 -> throw new ProjectConfigReadingException();
                case 463 -> throw new ConfigNotFoundException();
                case 464 -> throw new ConfigIsIncompleteException();
                default -> throw new TaskUploadException();
            }
        } catch (IOException ex) {
            throw new TaskUploadRequestExecutionException(ex);
        } finally {
            if (response != null){
                response.close();
            }
        }
    }

    private static String getTaskArchiveName() {
        return new File(ConfigManager.getProjectPath()).getName() + ".zip";
    }

}
