package ru.itis.visualtasks.desktopapp.application.managers.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import ru.itis.visualtasks.desktopapp.application.entities.project.Language;
import ru.itis.visualtasks.desktopapp.application.generators.JavaFilesGenerator;
import ru.itis.visualtasks.desktopapp.application.generators.PythonFilesGenerator;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class FilesGeneratorManagerTests {

    @Test
    public void get_java_generator(){
        Assertions.assertEquals(JavaFilesGenerator.class,
                FilesGeneratorManager.getFilesGenerator(Language.JAVA).getClass());
    }

    @Test
    public void get_python_generator(){
        Assertions.assertEquals(PythonFilesGenerator.class,
                FilesGeneratorManager.getFilesGenerator(Language.PYTHON).getClass());
    }

}
