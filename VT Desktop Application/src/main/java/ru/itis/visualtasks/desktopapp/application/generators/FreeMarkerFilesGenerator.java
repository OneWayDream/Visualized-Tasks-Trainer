package ru.itis.visualtasks.desktopapp.application.generators;

import freemarker.core.PlainTextOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import ru.itis.visualtasks.desktopapp.application.entities.templates.readme.InformationalReadMeContent;
import ru.itis.visualtasks.desktopapp.application.entities.templates.readme.VisualizationReadMeContent;
import ru.itis.visualtasks.desktopapp.application.entities.templates.readme.WrappersReadMeContent;
import ru.itis.visualtasks.desktopapp.application.managers.files.ConfigManager;
import ru.itis.visualtasks.desktopapp.application.managers.files.FilesManager;
import ru.itis.visualtasks.desktopapp.application.managers.settings.LocalizationManager;
import ru.itis.visualtasks.desktopapp.exceptions.files.*;
import ru.itis.visualtasks.desktopapp.exceptions.generation.TemplateCreationException;
import ru.itis.visualtasks.desktopapp.exceptions.generation.TemplateLoadingException;
import ru.itis.visualtasks.desktopapp.utils.PropertiesUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class FreeMarkerFilesGenerator implements FilesGenerator {

    protected static final String TEMPLATES_FOLDER;
    private static final String VISUALIZATION_READ_ME_TEMPLATE_PATH = "readme/visualization-readme.ftl";
    private static final String WRAPPERS_READ_ME_TEMPLATE_PATH = "readme/wrappers-readme.ftl";
    private static final String INFORMATIONAL_READ_ME_TEMPLATE_PATH = "readme/informational-readme.ftl";
    private static final String VISUALIZATION_FOLDER = "visualization";
    private static final String WRAPPERS_FOLDER = "wrappers";
    private static final String INFORMATIONAL_FOLDER = "info";
    private static final String READ_ME_FILE_NAME  = "readme.md";

    static {
        Properties properties = PropertiesUtils.getInstance().getProperties();
        TEMPLATES_FOLDER = properties.getProperty("templates-folder");
    }

    protected Configuration configuration;

    protected FreeMarkerFilesGenerator(){
        initFreeMarkerConfiguration();
    }

    protected void initFreeMarkerConfiguration(){
        configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setClassLoaderForTemplateLoading(getClass().getClassLoader(), TEMPLATES_FOLDER);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setOutputFormat(PlainTextOutputFormat.INSTANCE);
    }


    @Override
    public void generateSolutionFile() {
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("content", prepareSolutionFileContent());
        try{
            Template template = configuration.getTemplate(getSolutionTemplatePath());
            File destinationFile = getSolutionDestinationFile();
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, getSolutionDestinationPath());
        }
    }

    protected abstract Object prepareSolutionFileContent();

    protected abstract String getSolutionTemplatePath();

    private File getSolutionDestinationFile(){
        String destinationFilePath = getSolutionDestinationPath();
        return getDestinationFile(destinationFilePath);
    }

    protected abstract String getSolutionDestinationPath();


    @Override
    public void generateTestSolutionFile(){
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("content", prepareTestSolutionFileContent());
        try{
            Template template = configuration.getTemplate(getTestSolutionTemplatePath());
            File destinationFile = getTestSolutionDestinationFile();
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, getTestSolutionDestinationPath());
        }
    }

    protected abstract Object prepareTestSolutionFileContent();

    protected abstract String getTestSolutionTemplatePath();

    private File getTestSolutionDestinationFile(){
        String destinationFilePath = getTestSolutionDestinationPath();
        return getDestinationFile(destinationFilePath);
    }

    protected abstract String getTestSolutionDestinationPath();


    @Override
    public void generateVisualizationReadMeFile() {
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("content", prepareVisualizationReadMeContent());
        try{
            Template template = configuration.getTemplate(VISUALIZATION_READ_ME_TEMPLATE_PATH);
            File destinationFile = getVisualizationReadMeDestinationFile();
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, getVisualizationReadMeDestinationPath());
        }
    }

    private VisualizationReadMeContent prepareVisualizationReadMeContent(){
        return VisualizationReadMeContent.builder()
                .title(LocalizationManager.getLocaleTextByKey("templates.visualization-read-me-file.title"))
                .description(LocalizationManager.getLocaleTextByKey("templates.visualization-read-me-file.description"))
                .build();
    }

    private File getVisualizationReadMeDestinationFile(){
        String destinationFilePath = getVisualizationReadMeDestinationPath();
        createVisualizationDirectoryIfNotExists();
        try{
            FilesManager.createFile(destinationFilePath);
        } catch (FileAlreadyExistsException exception){
            //ignore
        } catch (FileCreationException exception){
            throw new FileGenerationException(destinationFilePath);
        }
        return new File(destinationFilePath);
    }

    private void createVisualizationDirectoryIfNotExists() {
        String folderPath = getVisualizationDirectoryPath();
        try{
            FilesManager.createDirectory(folderPath);
        } catch (FolderAlreadyExistsException exception){
            //ignore
        } catch (FolderCreationException exception){
            throw new FileGenerationException(folderPath);
        }
    }

    private String getVisualizationDirectoryPath() {
        return ConfigManager.getProjectPath() + File.separator + VISUALIZATION_FOLDER;
    }

    private String getVisualizationReadMeDestinationPath() {
        return getVisualizationDirectoryPath() + File.separator + READ_ME_FILE_NAME;
    }


    @Override
    public void generateWrappersReadMeFile() {
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("content", prepareWrappersReadMeContent());
        try{
            Template template = configuration.getTemplate(WRAPPERS_READ_ME_TEMPLATE_PATH);
            File destinationFile = getWrappersReadMeDestinationFile();
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, getWrappersReadMeDestinationPath());
        }
    }

    private WrappersReadMeContent prepareWrappersReadMeContent(){
        return WrappersReadMeContent.builder()
                .title(LocalizationManager.getLocaleTextByKey("templates.wrappers-read-me-file.title"))
                .description(LocalizationManager.getLocaleTextByKey("templates.wrappers-read-me-file.description"))
                .build();
    }

    private File getWrappersReadMeDestinationFile(){
        String destinationFilePath = getWrappersReadMeDestinationPath();
        createWrappersDirectoryIfNotExists();
        try{
            FilesManager.createFile(destinationFilePath);
        } catch (FileAlreadyExistsException exception){
            //ignore
        } catch (FileCreationException exception){
            throw new FileGenerationException(destinationFilePath);
        }
        return new File(destinationFilePath);
    }

    private void createWrappersDirectoryIfNotExists() {
        String folderPath = getWrappersDirectoryPath();
        try{
            FilesManager.createDirectory(folderPath);
        } catch (FolderAlreadyExistsException exception){
            //ignore
        } catch (FolderCreationException exception){
            throw new FileGenerationException(folderPath);
        }
    }

    private String getWrappersDirectoryPath() {
        return ConfigManager.getProjectPath() + File.separator + WRAPPERS_FOLDER;
    }

    private String getWrappersReadMeDestinationPath() {
        return getWrappersDirectoryPath() + File.separator + READ_ME_FILE_NAME;
    }


    @Override
    public void generateInformationalReadMeFile() {
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("content", prepareInformationalReadMeContent());
        try{
            Template template = configuration.getTemplate(INFORMATIONAL_READ_ME_TEMPLATE_PATH);
            File destinationFile = getInformationalReadMeDestinationFile();
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, getInformationalReadMeDestinationPath());
        }
    }

    private InformationalReadMeContent prepareInformationalReadMeContent(){
        return InformationalReadMeContent.builder()
                .title(LocalizationManager.getLocaleTextByKey("templates.informational-read-me-file.title"))
                .description(LocalizationManager.getLocaleTextByKey("templates.informational-read-me-file.description"))
                .build();
    }

    private File getInformationalReadMeDestinationFile(){
        String destinationFilePath = getInformationalReadMeDestinationPath();
        createInformationalDirectoryIfNotExists();
        try{
            FilesManager.createFile(destinationFilePath);
        } catch (FileAlreadyExistsException exception){
            //ignore
        } catch (FileCreationException exception){
            throw new FileGenerationException(destinationFilePath);
        }
        return new File(destinationFilePath);
    }

    private void createInformationalDirectoryIfNotExists() {
        String folderPath = getInformationalDirectoryPath();
        try{
            FilesManager.createDirectory(folderPath);
        } catch (FolderAlreadyExistsException exception){
            //ignore
        } catch (FolderCreationException exception){
            throw new FileGenerationException(folderPath);
        }
    }

    private String getInformationalDirectoryPath() {
        return ConfigManager.getProjectPath() + File.separator + INFORMATIONAL_FOLDER;
    }

    private String getInformationalReadMeDestinationPath() {
        return getInformationalDirectoryPath() + File.separator + READ_ME_FILE_NAME;
    }

    protected File getDestinationFile(String destinationPath){
        try{
            FilesManager.createFile(destinationPath);
        } catch (FileAlreadyExistsException exception){
            //ignore
        } catch (FileCreationException exception){
            throw new FileGenerationException(destinationPath);
        }
        return new File(destinationPath);
    }


    @Override
    public void generateCodeFile(String destinationFilePath) {
        Map<String, Object> freemarkerDataModel = new HashMap<>();
        freemarkerDataModel.put("content", prepareCodeFileContent(destinationFilePath));
        try{
            Template template = configuration.getTemplate(getCodeFileTemplatePath());
            File destinationFile = getDestinationFile(destinationFilePath);
            Writer fileWriter = new FileWriter(destinationFile);
            template.process(freemarkerDataModel, fileWriter);
        } catch (IOException ex) {
            throw new TemplateLoadingException(ex);
        } catch (TemplateException ex) {
            throw new TemplateCreationException(ex, destinationFilePath);
        }
    }

    protected abstract Object prepareCodeFileContent(String destinationFilePath);

    protected abstract String getCodeFileTemplatePath();

}
