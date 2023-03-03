package ru.itis.graduationwork.application.managers;

import ru.itis.graduationwork.exceptions.files.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesManager {

    public static void createFile(String filePath){
        File file = new File(filePath);
        try{
            boolean isCreated = file.createNewFile();
            if (!isCreated){
                throw new FileAlreadyExistsException();
            }
        } catch (IOException ex) {
            throw new FileCreationException(ex);
        }
    }

    public static void copyFile(String source, String destinationPath){
        try{
            Files.copy(Path.of(source), Path.of(destinationPath));
        } catch (IOException ex) {
            throw new FileCopyingException(ex);
        }
    }

    public static String loadFileContent(String filePath){
        try{
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (NoSuchFileException ex){
            throw new FileNotFoundException(ex);
        } catch (IOException ex) {
            throw new FileReadingException(ex);
        }
    }

    public static void writeFile(String filePath, String fileContent){
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(fileContent);
        } catch (IOException ex) {
            throw new FileWritingException(ex);
        }
    }

}
