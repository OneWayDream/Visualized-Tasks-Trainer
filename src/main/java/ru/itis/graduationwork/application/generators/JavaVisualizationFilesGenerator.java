package ru.itis.graduationwork.application.generators;

public class JavaVisualizationFilesGenerator {

    private static final JavaFilesGenerator javaFilesGenerator = new JavaFilesGenerator();

    public static void generateCustomPanelFile(){
        javaFilesGenerator.generateCustomPanelFile();
    }

    public static void generateVisualizationScenePanelFile(){
        javaFilesGenerator.generateVisualizationScenePanelFile();
    }

}
