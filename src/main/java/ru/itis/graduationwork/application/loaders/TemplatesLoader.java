package ru.itis.graduationwork.application.loaders;

import ru.itis.graduationwork.application.entities.project.Language;
import ru.itis.graduationwork.exceptions.files.FileNotFoundException;
import ru.itis.graduationwork.exceptions.files.FileReadingException;
import ru.itis.graduationwork.utils.PropertiesUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.Objects;
import java.util.Properties;

public class TemplatesLoader{

    private static final String TEMPLATES_FOLDER;
    private static final String JAVA_FILE_TEMPLATE_FILE_NAME = "FileTemplate";

    static {
        Properties properties = PropertiesUtils.getInstance().getProperties();
        TEMPLATES_FOLDER = properties.getProperty("templates-folder");
    }

    public static String getJavaFileTemplateFileTemplateContent(Language language){
        String templateFilePath = getTemplateFilePath(JAVA_FILE_TEMPLATE_FILE_NAME, language);
        return loadTemplate(templateFilePath);
    }

    private static String loadTemplate(String templatePath){
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(Objects.requireNonNull(
                             TemplatesLoader.class.getClassLoader().getResourceAsStream(templatePath)
                     ), StandardCharsets.UTF_8))) {
            StringBuilder content = new StringBuilder();
            String line;
            String lineSeparator = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(lineSeparator);
            }
            content.deleteCharAt(content.length() - 1);
            return content.toString();
        } catch (NoSuchFileException ex){
            throw new FileNotFoundException(ex);
        } catch (IOException ex) {
            throw new FileReadingException(ex);
        }
    }

    private static String getTemplateFilePath(String templateFileName, Language language){
        return TEMPLATES_FOLDER + File.separator + templateFileName + language.getExtension();
    }

}
