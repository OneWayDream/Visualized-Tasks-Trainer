package ru.itis.visualtasks.desktopapp.application.generators;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.itis.visualtasks.desktopapp.application.entities.project.VisualizationType;
import ru.itis.visualtasks.desktopapp.application.entities.templates.java.*;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.exceptions.generation.TemplateCreationException;
import ru.itis.visualtasks.desktopapp.exceptions.generation.TemplateLoadingException;
import ru.itis.visualtasks.desktopapp.exceptions.unexpected.UnsupportedVisualizationTypeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaFilesGenerator extends FreeMarkerFilesGenerator {

    private final String SOLUTION_TEMPLATE_PATH = "java/solution.ftl";
    private final String SOLUTION_DESTINATION_PATH = "Solution.java";
    private final String TEST_SOLUTION_TEMPLATE_PATH = "java/test-solution.ftl";
    private final String TEST_SOLUTION_DESTINATION_PATH = "TestSolution.java";
    private final String CUSTOM_PANEL_TEMPLATE_PATH = "java/custom-panel.ftl";
    private final String CUSTOM_PANEL_DESTINATION_PATH = "visualization/CustomPanel.java";
    private final String VISUALIZATION_SCENE_SWING_PANEL_TEMPLATE_PATH = "java/visualization-scene-swing-panel.ftl";
    private final String VISUALIZATION_SCENE_JAVA_FX_PANEL_TEMPLATE_PATH = "java/visualization-scene-java-fx-panel.ftl";
    private final String VISUALIZATION_SCENE_PANEL_DESTINATION_PATH = "visualization/VisualizationScenePanel.java";
    private final String CODE_FILE_TEMPLATE_PATH = "java/code-file.ftl";
    public JavaFilesGenerator(){
        super();
    }

    @Override
    protected JavaSolutionFileContent prepareSolutionFileContent(){
        return JavaSolutionFileContent.builder()
                .imports(getSolutionFileImports())
                .comment(prepareSolutionFileComment())
                .build();
    }

    private List<String> getSolutionFileImports(){
        return List.of(
                "wrappers.*",
                "ru.itis.graduationwork.desktopapp.application.entities.project.SolutionScheme"
        );
    }

    private String prepareSolutionFileComment(){
        String rawComment = LocalizationManager.getLocaleTextByKey("templates.solution-file-template.comment");
        rawComment = rawComment.substring(0, rawComment.length() - 1);
        return rawComment.replace("\n", "  \n    ");
    }

    @Override
    protected String getSolutionTemplatePath(){
        return SOLUTION_TEMPLATE_PATH;
    }

    @Override
    protected String getSolutionDestinationPath() {
        return ConfigManager.getProjectPath() + File.separator + SOLUTION_DESTINATION_PATH;
    }


    @Override
    protected JavaTestSolutionFileContent prepareTestSolutionFileContent(){
        return JavaTestSolutionFileContent.builder()
                .imports(getTestSolutionFileImports())
                .comment(prepareTestSolutionComment())
                .build();
    }

    private List<String> getTestSolutionFileImports(){
        return List.of(
                "wrappers.*",
                "ru.itis.graduationwork.desktopapp.application.entities.project.SolutionScheme"
        );
    }

    private String prepareTestSolutionComment(){
        String rawComment = LocalizationManager.getLocaleTextByKey("templates.test-solution-file-template.comment");
        rawComment = rawComment.substring(0, rawComment.length() - 1);
        return rawComment.replace("\n", "  \n    ");
    }

    @Override
    protected String getTestSolutionTemplatePath(){
        return TEST_SOLUTION_TEMPLATE_PATH;
    }

    @Override
    protected String getTestSolutionDestinationPath() {
        return ConfigManager.getProjectPath() + File.separator + TEST_SOLUTION_DESTINATION_PATH;
    }


    @Override
    protected JavaCodeFileContent prepareCodeFileContent(String destinationPath) {
        String packageValue = getPackageValue(destinationPath);
        boolean isWrapperClass = checkIfIsAWrapperClass(packageValue);
        return JavaCodeFileContent.builder()
                .packageValue(packageValue)
                .isWrapperClass(isWrapperClass)
                .imports(getCodeFileImports(isWrapperClass))
                .fileName(getCodeFileName(destinationPath))
                .annotationComment(getCodeFileAnnotationComment(isWrapperClass))
                .visualizationActionRegistrationComment(getCodeFileVisualizationActionRegistrationComment(isWrapperClass))
                .build();
    }

    private String getPackageValue(String destinationPath){
        String destinationFolder = destinationPath.substring(0, destinationPath.lastIndexOf(File.separatorChar));
        String packagePath = destinationFolder.substring(ConfigManager.getProjectPath().length() + 1);
        return packagePath.replace(File.separatorChar, '.');
    }

    private boolean checkIfIsAWrapperClass(String packageValue){
        return packageValue.contains("wrappers");
    }

    private List<String> getCodeFileImports(boolean isWrapperClass){
        List<String> imports = null;
        if (isWrapperClass){
            imports = List.of(
                    "ru.itis.graduationwork.desktopapp.application.managers.project.visualization.registration.java.JavaVisualizationActionRegistrationManager",
                    "import ru.itis.graduationwork.desktopapp.application.entities.project.VisualizationAction",
                    "wrappers.*",
                    "ru.itis.graduationwork.desktopapp.application.ui.core.ide.visualization.core.WrappedClass"
            );
        }
        return imports;
    }

    private String getCodeFileName(String destinationPath){
        String fileName = destinationPath.substring(destinationPath.lastIndexOf(File.separatorChar) + 1);
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }

    private String getCodeFileAnnotationComment(boolean isWrapperClass){
        String comment = null;
        if (isWrapperClass){
            comment = prepareCodeFileAnnotationComment();
        }
        return comment;
    }

    private String prepareCodeFileAnnotationComment() {
        String rawString = LocalizationManager.getLocaleTextByKey("templates.code-file-template.java.annotation-comment");
        rawString = rawString.substring(0, rawString.length() - 1);
        return rawString.replace("\n", "  \n    ");
    }

    private String getCodeFileVisualizationActionRegistrationComment(boolean isWrapperClass){
        String comment = null;
        if (isWrapperClass){
            comment = prepareCodeFileVisualizationActionRegistrationComment();
        }
        return comment;
    }

    private String prepareCodeFileVisualizationActionRegistrationComment() {
        String rawString = LocalizationManager.getLocaleTextByKey("templates.code-file-template.java.visualization-action-registration-comment");
        rawString = rawString.substring(0, rawString.length() - 1);
        return rawString.replace("\n", "  \n    ");
    }

    @Override
    protected String getCodeFileTemplatePath(){
        return CODE_FILE_TEMPLATE_PATH;
    }


    public void generateCustomPanelFile(){
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        VisualizationType visualizationType = ConfigManager.getProjectVisualizationType();
        freemarkerDataModel.put("content", prepareCustomPanelFileContent(visualizationType));
        try{
            Template template = configuration.getTemplate(CUSTOM_PANEL_TEMPLATE_PATH);
            File destinationFile = getCustomPanelDestinationFile();
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, getCustomPanelDestinationPath());
        }
    }

    private CustomPanelContent prepareCustomPanelFileContent(VisualizationType visualizationType){
        return CustomPanelContent.builder()
                .packageValue("visualization")
                .imports(getCustomPanelFileImports(visualizationType))
                .comment(prepareCustomPanelComment())
                .parentClassName(getCustomPanelParentClassName(visualizationType))
                .build();
    }

    private List<String> getCustomPanelFileImports(VisualizationType visualizationType){
        List<String> imports;
        switch (visualizationType){
            case SWING -> imports = List.of("javax.swing.*");
            case JAVA_FX -> imports = List.of("javafx.embed.swing.JFXPanel", "javafx.scene.*");
            default -> throw new UnsupportedVisualizationTypeException();
        }
        return imports;
    }

    private String prepareCustomPanelComment(){
        String rawString = LocalizationManager.getLocaleTextByKey("templates.custom-panel-file-template.comment");
        rawString = rawString.substring(0, rawString.length() - 1);
        return rawString.replace("\n", "  \n    ");
    }

    private String getCustomPanelParentClassName(VisualizationType visualizationType){
        String parentClassName;
        switch (visualizationType){
            case SWING -> parentClassName = "JPanel";
            case JAVA_FX -> parentClassName = "JFXPanel";
            default -> throw new UnsupportedVisualizationTypeException();
        }
        return parentClassName;
    }

    private File getCustomPanelDestinationFile(){
        String destinationFilePath = getCustomPanelDestinationPath();
        return getDestinationFile(destinationFilePath);
    }

    private String getCustomPanelDestinationPath() {
        return ConfigManager.getProjectPath() + File.separator + CUSTOM_PANEL_DESTINATION_PATH;
    }


    public void generateVisualizationScenePanelFile(){
        VisualizationType visualizationType = ConfigManager.getProjectVisualizationType();
        switch (visualizationType){
            case SWING -> generateVisualizationSceneSwingPanelFile();
            case JAVA_FX -> generateVisualizationSceneJavaFxPanelFile();
            default -> throw new UnsupportedVisualizationTypeException();
        }
    }

    private void generateVisualizationSceneSwingPanelFile(){
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("content", prepareVisualizationSceneSwingPanelFileContent());
        try{
            Template template = configuration.getTemplate(VISUALIZATION_SCENE_SWING_PANEL_TEMPLATE_PATH);
            File destinationFile = getVisualizationScenePanelDestinationFile();
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, getVisualizationScenePanelDestinationPath());
        }
    }

    private VisualizationSceneSwingPanelContent prepareVisualizationSceneSwingPanelFileContent() {
        return VisualizationSceneSwingPanelContent.builder()
                .packageValue("visualization")
                .imports(getVisualizationSceneSwingPanelFileImports())
                .generalComment(prepareVisualizationSceneSwingPanelGeneralComment())
                .buttonFlagsComment(prepareVisualizationSceneSwingPanelButtonFlagsComment())
                .build();
    }

    private List<String> getVisualizationSceneSwingPanelFileImports() {
        return List.of(
                "java.awt.*",
                "ru.itis.graduationwork.desktopapp.application.ui.core.ide.visualization.core.VisualizationSceneSwingPanelScheme",
                "javax.swing.*"
        );
    }

    private String prepareVisualizationSceneSwingPanelGeneralComment(){
        String rawComment = LocalizationManager.getLocaleTextByKey("templates.visualization-scene-panel-template.general-swing-comment");
        rawComment = rawComment.substring(0, rawComment.length() - 1);
        return rawComment.replace("\n", "  \n    ");
    }

    private String prepareVisualizationSceneSwingPanelButtonFlagsComment(){
        String rawComment = LocalizationManager.getLocaleTextByKey("templates.visualization-scene-panel-template.button-flags-comment");
        rawComment = rawComment.substring(0, rawComment.length() - 1);
        return rawComment.replace("\n", "    \n          ");
    }


    private void generateVisualizationSceneJavaFxPanelFile(){
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("content", prepareVisualizationSceneJavaFxPanelFileContent());
        try{
            Template template = configuration.getTemplate(VISUALIZATION_SCENE_JAVA_FX_PANEL_TEMPLATE_PATH);
            File destinationFile = getVisualizationScenePanelDestinationFile();
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, getVisualizationScenePanelDestinationPath());
        }
    }

    private VisualizationSceneJavaFXPanelContent prepareVisualizationSceneJavaFxPanelFileContent() {
        return VisualizationSceneJavaFXPanelContent.builder()
                .packageValue("visualization")
                .imports(getVisualizationSceneJavaFxPanelFileImports())
                .generalComment(prepareVisualizationSceneJavafxPanelGeneralComment())
                .buttonFlagsComment(prepareVisualizationSceneJavafxPanelButtonFlagsComment())
                .build();
    }

    private List<String> getVisualizationSceneJavaFxPanelFileImports() {
        return List.of(
//                "java.awt.*",
                "ru.itis.graduationwork.desktopapp.application.ui.core.ide.visualization.core.VisualizationSceneJavaFxPanelScheme",
                "javafx.embed.swing.JFXPanel",
                "javafx.scene.*"
        );
    }

    private String prepareVisualizationSceneJavafxPanelGeneralComment(){
        String rawString = LocalizationManager.getLocaleTextByKey("templates.visualization-scene-panel-template.general-java-fx-comment");
        rawString = rawString.substring(0, rawString.length() - 1);
        return rawString.replace("\n", "  \n    ");
    }

    private String prepareVisualizationSceneJavafxPanelButtonFlagsComment(){
        String rawString = LocalizationManager.getLocaleTextByKey("templates.visualization-scene-panel-template.button-flags-comment");
        rawString = rawString.substring(0, rawString.length() - 1);
        return rawString.replace("\n", "    \n          ");
    }

    private File getVisualizationScenePanelDestinationFile(){
        String destinationFilePath = getVisualizationScenePanelDestinationPath();
        return getDestinationFile(destinationFilePath);
    }

    private String getVisualizationScenePanelDestinationPath() {
        return ConfigManager.getProjectPath() + File.separator + VISUALIZATION_SCENE_PANEL_DESTINATION_PATH;
    }


    @Override
    public String getSolutionFileName() {
        return SOLUTION_DESTINATION_PATH;
    }

    @Override
    public String getTestSolutionFileName() {
        return TEST_SOLUTION_DESTINATION_PATH;
    }

    @Override
    public void generateAdditionalFiles() {

    }

}
