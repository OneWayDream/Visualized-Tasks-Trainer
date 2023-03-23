package ru.itis.graduationwork.application.managers.project;

import ru.itis.graduationwork.application.entities.project.Language;
import ru.itis.graduationwork.application.generators.FilesGenerator;
import ru.itis.graduationwork.application.generators.JavaFilesGenerator;
import ru.itis.graduationwork.application.generators.PythonFilesGenerator;
import ru.itis.graduationwork.exceptions.unexpected.UnsupportedLanguageException;

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
