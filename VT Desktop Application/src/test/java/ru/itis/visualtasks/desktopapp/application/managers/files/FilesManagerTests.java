package ru.itis.visualtasks.desktopapp.application.managers.files;

import org.junit.jupiter.api.*;

import java.io.File;

import static ru.itis.visualtasks.desktopapp.application.compilers.FilesVariables.USER_DIRECTORY;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class FilesManagerTests {

    private static final String FOLDER_PATH = USER_DIRECTORY + File.separator
            + "src\\test\\resources\\managers\\files\\files_manager";
    private static final String FILE_TO_CREATE_NAME = "test.txt";
    private static final String FILE_TO_COPY_NAME = "test-copy.txt";
    private static final String FILE_WITH_CONTENT_NAME = "txt-with-content.txt";
    private static final String FOLDER_TO_CREATE_NAME = "test";


    @Test
    @Order(1)
    public void create_file(){
        String filePath = FOLDER_PATH + File.separator + FILE_TO_CREATE_NAME;
        FilesManager.createFile(filePath);
        Assertions.assertTrue(new File(filePath).exists());
    }

    @Test
    @Order(2)
    public void copy_file(){
        String sourceFilePath = FOLDER_PATH + File.separator + FILE_TO_CREATE_NAME;
        String destinationFilePath = FOLDER_PATH + File.separator + FILE_TO_COPY_NAME;
        FilesManager.copyFile(sourceFilePath, destinationFilePath);
        Assertions.assertTrue(new File(destinationFilePath).exists());
    }

    @Test
    @Order(3)
    public void write_file(){
        String filePath = FOLDER_PATH + File.separator + FILE_TO_CREATE_NAME;
        FilesManager.writeFile(filePath, "Content");
        Assertions.assertEquals("Content", FilesManager.readFileAsString(filePath));
    }

    @Test
    @Order(4)
    public void check_is_file_exists(){
        String filePath = FOLDER_PATH + File.separator + FILE_WITH_CONTENT_NAME;
        Assertions.assertTrue(FilesManager.checkIsFileExist(filePath));
    }

    @Test
    @Order(5)
    public void read_file_bytes(){
        String filePath = FOLDER_PATH + File.separator + FILE_WITH_CONTENT_NAME;
        Assertions.assertArrayEquals(FilesManager.readFileAsString(filePath).getBytes(),
                FilesManager.readFileBytes(new File(filePath)));
    }

    @Test
    @Order(6)
    public void create_folder(){
        String folderPath = FOLDER_PATH + File.separator + FOLDER_TO_CREATE_NAME;
        FilesManager.createDirectory(folderPath);
        Assertions.assertTrue(FilesManager.checkIsFileExist(folderPath));
    }

    @Test
    @Order(7)
    public void check_is_directory(){
        String folderPath = FOLDER_PATH + File.separator + FOLDER_TO_CREATE_NAME;
        Assertions.assertTrue(FilesManager.isDirectory(folderPath));
    }

    @Test
    @Order(8)
    public void delete_file(){
        String filePath = FOLDER_PATH + File.separator + FILE_TO_CREATE_NAME;
        FilesManager.delete(filePath);
        Assertions.assertFalse(new File(filePath).exists());
    }

    @Test
    @Order(9)
    public void delete_folder(){
        String folderPath = FOLDER_PATH + File.separator + FOLDER_TO_CREATE_NAME;
        FilesManager.delete(folderPath);
        Assertions.assertFalse(new File(folderPath).exists());
    }

    @AfterAll
    public static void afterAll(){
        String copyFilePath = FOLDER_PATH + File.separator + FILE_TO_COPY_NAME;
        FilesManager.delete(copyFilePath);
    }

}
