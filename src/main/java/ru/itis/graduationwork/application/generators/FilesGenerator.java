package ru.itis.graduationwork.application.generators;

import ru.itis.graduationwork.application.managers.files.ConfigManager;

public interface FilesGenerator {

    String getSolutionFileName();
    String getTestSolutionFileName();

    void generateVisualizationReadMeFile();
    void generateWrappersReadMeFile();
    void generateInformationalReadMeFile();
    void generateSolutionFile();
    void generateTestSolutionFile();

    void generateCodeFile(String destinationFilePath);
    void generateAdditionalFiles();

    default boolean checkIsCodeFile(String fileName){
        return fileName.endsWith(ConfigManager.getProjectLanguage().getExtension());
    }

}
