package ru.itis.visualtasks.desktopapp.application.generators;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.itis.visualtasks.desktopapp.application.entities.templates.python.PythonCodeFileContent;
import ru.itis.visualtasks.desktopapp.application.entities.templates.python.PythonSolutionFileContent;
import ru.itis.visualtasks.desktopapp.application.entities.templates.python.PythonTestSolutionFileContent;
import ru.itis.visualtasks.desktopapp.application.entities.templates.python.WrappersAnalysisContent;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.exceptions.files.FileGenerationException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FolderAlreadyExistsException;
import ru.itis.visualtasks.desktopapp.exceptions.files.FolderCreationException;
import ru.itis.visualtasks.desktopapp.exceptions.generation.TemplateCreationException;
import ru.itis.visualtasks.desktopapp.exceptions.generation.TemplateLoadingException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PythonFilesGenerator extends FreeMarkerFilesGenerator {

    private final String CODE_FILE_TEMPLATE_PATH = "python/code-file.ftl";
    private final String SOLUTION_TEMPLATE_PATH = "python/solution.ftl";
    private final String SOLUTION_DESTINATION_PATH = "solution.py";
    private final String TEST_SOLUTION_TEMPLATE_PATH = "python/test-solution.ftl";
    private final String TEST_SOLUTION_DESTINATION_PATH = "test_solution.py";
    private final String LIBRARY_FOLDER_NAME = "lib";
    private final String APPLICATION_LIB_FOLDER_NAME = "app";
    private final String APPLICATION_UTILS_SCRYPT_PATH = "application_utils.py";
    private final String APPLICATION_UTILS_SCRYPT_TEMPLATE_PATH = "python/application_utils.ftl";
    private final String WRAPPERS_ANALYSIS_TEMPLATE_PATH = "python/wrappers-analysis.ftl";
    private final String WRAPPERS_ANALYSIS_DESTINATION_PATH = "target/wrappers_analysis.py";

    @Override
    protected Object prepareSolutionFileContent() {
        return PythonSolutionFileContent.builder()
                .imports(getSolutionFileImports())
                .comment(getSolutionFileComment())
                .build();
    }

    private Map<String, String> getSolutionFileImports() {
        Map<String, String> imports = new HashMap<>();
        imports.put("lib.app.application_utils", "write_visualization_actions");
        return imports;
    }

    private String getSolutionFileComment() {
        return LocalizationManager.getLocaleTextByKey("templates.solution-file-template.comment");
    }

    @Override
    protected String getSolutionTemplatePath() {
        return SOLUTION_TEMPLATE_PATH;
    }

    @Override
    protected String getSolutionDestinationPath() {
        return ConfigManager.getProjectPath() + File.separator + SOLUTION_DESTINATION_PATH;
    }


    @Override
    protected Object prepareTestSolutionFileContent() {
        return PythonTestSolutionFileContent.builder()
                .imports(getTestSolutionFileImports())
                .comment(getTestSolutionFileComment())
                .build();
    }

    private Map<String, String> getTestSolutionFileImports() {
        Map<String, String> imports = new HashMap<>();
        imports.put("lib.app.application_utils", "write_visualization_actions");
        return imports;
    }

    private String getTestSolutionFileComment() {
        return LocalizationManager.getLocaleTextByKey("templates.test-solution-file-template.comment");
    }

    @Override
    protected String getTestSolutionTemplatePath() {
        return TEST_SOLUTION_TEMPLATE_PATH;
    }

    @Override
    protected String getTestSolutionDestinationPath() {
        return ConfigManager.getProjectPath() + File.separator + TEST_SOLUTION_DESTINATION_PATH;
    }


    @Override
    protected PythonCodeFileContent prepareCodeFileContent(String destinationFilePath) {
        boolean isWrapperScrypt = checkIfIsAWrapperScrypt(destinationFilePath);
        return PythonCodeFileContent.builder()
                .isWrapperScrypt(isWrapperScrypt)
                .imports(getCodeFileImports(isWrapperScrypt))
                .classAnnotationComment(getCodeFileClassAnnotationComment(isWrapperScrypt))
                .functionAnnotationComment(getCodeFileFunctionAnnotationComment(isWrapperScrypt))
                .visualizationActionRegistrationComment(getCodeFileVisualizationActionRegistrationComment(isWrapperScrypt))
                .build();
    }

    private boolean checkIfIsAWrapperScrypt(String destinationFilePath) {
        return destinationFilePath.contains("wrappers");
    }

    private Map<String, String> getCodeFileImports(boolean isWrapperScrypt) {
        Map<String, String> imports = new HashMap<>();
        if (isWrapperScrypt){
            imports.put("lib.app.application_utils", "register_visualization_action");
        }
        return imports;
    }

    private String getCodeFileClassAnnotationComment(boolean isWrapperScrypt) {
        String comment = null;
        if (isWrapperScrypt){
            comment = LocalizationManager.getLocaleTextByKey("templates.code-file-template.python.class-annotation-comment");
        }
        return comment;
    }

    private String getCodeFileFunctionAnnotationComment(boolean isWrapperScrypt) {
        String comment = null;
        if (isWrapperScrypt){
            comment = LocalizationManager.getLocaleTextByKey("templates.code-file-template.python.function-annotation-comment");
        }
        return comment;
    }

    private String getCodeFileVisualizationActionRegistrationComment(boolean isWrapperScrypt) {
        String comment = null;
        if (isWrapperScrypt){
            comment = LocalizationManager.getLocaleTextByKey("templates.code-file-template.python.visualization-action-registration-comment");
        }
        return comment;
    }

    @Override
    protected String getCodeFileTemplatePath(){
        return CODE_FILE_TEMPLATE_PATH;
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
        createLibFolderIfNotExists();
        createApplicationUtilsScryptIfNotExists();
    }

    private void createLibFolderIfNotExists() {
        String libFolderPath = getLibFolderPath();
        if (!FilesManager.checkIsFileExist(libFolderPath)){
            try{
                FilesManager.createDirectory(libFolderPath);
            } catch (FolderAlreadyExistsException | FolderCreationException exception){
                throw new FileGenerationException(libFolderPath);
            }
        }
    }

    private String getLibFolderPath(){
        return ConfigManager.getProjectPath() + File.separator + LIBRARY_FOLDER_NAME;
    }

    private void createApplicationUtilsScryptIfNotExists() {
        generateApplicationLibFolderIfNotExists();
        String applicationUtilsScryptPath = getApplicationUtilsScryptPath();
        if (!FilesManager.checkIsFileExist(applicationUtilsScryptPath)){
            generateApplicationUtilsScrypt();
        }
    }

    private void generateApplicationLibFolderIfNotExists() {
        String applicationLibFolderPath = getApplicationLibFolderPath();
        if (!FilesManager.checkIsFileExist(applicationLibFolderPath)){
            try{
                FilesManager.createDirectory(applicationLibFolderPath);
            } catch (FolderAlreadyExistsException | FolderCreationException exception){
                throw new FileGenerationException(applicationLibFolderPath);
            }
        }
    }

    private String getApplicationLibFolderPath() {
        String libFolderPath = getLibFolderPath();
        return libFolderPath + File.separator + APPLICATION_LIB_FOLDER_NAME;
    }

    private String getApplicationUtilsScryptPath() {
        String applicationLibFolderPath = getApplicationLibFolderPath();
        return applicationLibFolderPath + File.separator + APPLICATION_UTILS_SCRYPT_PATH;
    }

    private void generateApplicationUtilsScrypt() {
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        try{
            Template template = configuration.getTemplate(APPLICATION_UTILS_SCRYPT_TEMPLATE_PATH);
            File destinationFile = getApplicationUtilsScryptDestinationFile();
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, getSolutionDestinationPath());
        }
    }

    private File getApplicationUtilsScryptDestinationFile(){
        String applicationUtilsScryptDestinationPath = getApplicationUtilsScryptPath();
        return getDestinationFile(applicationUtilsScryptDestinationPath);
    }


    public void generateWrappersAnalysisFile(List<String> modules){
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("content", prepareWrappersAnalysisFileContent(modules));
        try{
            Template template = configuration.getTemplate(WRAPPERS_ANALYSIS_TEMPLATE_PATH);
            File destinationFile = getWrappersAnalysisDestinationFile();
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, getSolutionDestinationPath());
        }
    }

    private Object prepareWrappersAnalysisFileContent(List<String> modules) {
        return WrappersAnalysisContent.builder()
                .modules(modules)
                .build();
    }

    private File getWrappersAnalysisDestinationFile() {
        String destinationFilePath = getWrappersAnalysisDestinationPath();
        return getDestinationFile(destinationFilePath);
    }

    private String getWrappersAnalysisDestinationPath() {
        return ConfigManager.getProjectPath() + File.separator + WRAPPERS_ANALYSIS_DESTINATION_PATH;
    }

}
