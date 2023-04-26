package ru.itis.visualtasks.desktopapp.application.managers.files;

import ru.itis.visualtasks.desktopapp.exceptions.files.*;

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

    public static boolean checkIsFileExist(String filePath){
        return new File(filePath).exists();
    }

    public static byte[] readFileBytes(File file){
        try{
            return Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            throw new FileReadingException(ex);
        }
    }

    public static void delete(String path){
        if (!isDirectory(path)){
            deleteFile(path);
        } else {
            deleteDirectory(path);
        }

    }

    public static boolean isDirectory(String path){
        return new File(path).isDirectory();
    }

    public static void deleteFile(String filePath){
        boolean isDeleted;
        try{
            isDeleted = new File(filePath).delete();
        } catch (Exception exception){
            throw new FileDeletionException(exception);
        }
        if (!isDeleted){
            throw new FileDeletionException();
        }
    }

    public static void deleteDirectory(String directoryPath){
        File directoryToDelete = new File(directoryPath);
        String[] innerFilePaths = directoryToDelete.list();
        innerFilePaths = (innerFilePaths == null) ? new String[0] : innerFilePaths;
        try{
            for (String innerFilePath: innerFilePaths){
                File currentFile = new File(directoryPath + File.separator + innerFilePath);
                currentFile.delete();
            }
        } catch (Exception exception){
            throw new FileDeletionException(exception);
        }
        if (!directoryToDelete.delete()){
            throw new FileDeletionException();
        }
    }

    public static void createDirectory(String directoryPath){
        if (checkIsFileExist(directoryPath)) {
            throw new FolderAlreadyExistsException();
        }
        File folder = new File(directoryPath);
        boolean successCreation;
        try{
            successCreation = folder.mkdir();
        } catch (Exception exception){
            throw new FolderCreationException(exception);
        }
        if (!successCreation) {
            throw new FolderCreationException();
        }
    }

    public static String readFileAsString(String path){
        try{
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new FileReadingException(e);
        }

    }

}
