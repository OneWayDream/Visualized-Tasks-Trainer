package ru.itis.visualtasks.desktopapp.application.managers.project;

import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.generators.FilesGenerator;
import ru.itis.visualtasks.desktopapp.application.generators.JavaFilesGenerator;
import ru.itis.visualtasks.desktopapp.application.generators.PythonFilesGenerator;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnsupportedLanguageException;

public class FilesGeneratorManager {

    public static FilesGenerator getFilesGenerator(Language language){
        FilesGenerator filesGenerator;
        switch (language){
            case JAVA -> filesGenerator = new JavaFilesGenerator();
            case PYTHON -> filesGenerator = new PythonFilesGenerator();
            default -> throw new UnsupportedLanguageException();
        }
        return filesGenerator;
    }

}
