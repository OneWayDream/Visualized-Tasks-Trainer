package ru.itis.graduationwork.application.compilers.java;

import ru.itis.graduationwork.application.compilers.WrappersFilesCompiler;
import ru.itis.graduationwork.application.entities.Language;
import ru.itis.graduationwork.application.managers.files.ConfigManager;
import ru.itis.graduationwork.application.managers.project.JavaClassesCompiler;
import ru.itis.graduationwork.application.managers.utils.ExceptionsManager;
import ru.itis.graduationwork.exceptions.files.WrapperFilesReadingException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class JavaWrappersFilesCompiler extends WrappersFilesCompiler {

    @Override
    public void compileWrappersFiles() {
        try{
            JavaClassesCompiler.compileFiles(getAllWrapperFiles());
        } catch (WrapperFilesReadingException exception){
            ExceptionsManager.addDelayedException(
                    ExceptionsManager::handleWrapperFilesReadingException, 200, TimeUnit.MILLISECONDS
            );
        }
    }

    private List<File> getAllWrapperFiles(){
        String folderPath = ConfigManager.getProjectPath() + File.separatorChar + WRAPPERS_FOLDER;
        try (Stream<Path> entries
                     = Files.find(Paths.get(folderPath), Integer.MAX_VALUE,
                (filePath, fileAttr) -> fileAttr.isRegularFile() && isJavaFile(String.valueOf(filePath)))
        ) {
            return entries.map(path -> new File(path.toUri())).toList();
        } catch (IOException exception) {
            throw new WrapperFilesReadingException(exception);
        }
    }

    private boolean isJavaFile(String filePath){
        return filePath.substring(filePath.length() - 5).equals(Language.JAVA.getExtension());
    }

}
