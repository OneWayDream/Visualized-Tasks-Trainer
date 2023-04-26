package ru.itis.visualtasks.tasksserver.services;

import com.github.rjeschke.txtmark.Processor;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.visualtasks.tasksserver.dto.NewTaskInfo;
import ru.itis.visualtasks.tasksserver.entities.ProjectConfig;
import ru.itis.visualtasks.tasksserver.exceptions.tasks.ConfigNotFoundException;
import ru.itis.visualtasks.tasksserver.exceptions.tasks.IncompleteConfigException;
import ru.itis.visualtasks.tasksserver.exceptions.tasks.TaskArchiveReadingException;
import ru.itis.visualtasks.tasksserver.exceptions.tasks.UnsupportedTaskExtensionException;
import ru.itis.visualtasks.tasksserver.models.TaskArchive;
import ru.itis.visualtasks.tasksserver.models.TaskDescriptionFile;
import ru.itis.visualtasks.tasksserver.models.TaskInfo;
import ru.itis.visualtasks.tasksserver.utils.ProjectConfigUtils;
import ru.itis.visualtasks.tasksserver.utils.ZipArchiveUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {

    private final TaskInfoService taskInfoService;
    private final TaskArchiveService taskArchiveService;
    private final TaskDescriptionFileService taskDescriptionFileService;

    @Override
    public List<TaskInfo> getTasksInfos() {
        return taskInfoService.findAll();
    }

    @Override
    @Transactional
    public void saveTask(MultipartFile file, NewTaskInfo newTaskInfo) {
        checkFileExtension(file);
        Map<String, byte[]> taskFiles = getArchiveFiles(file);
        ProjectConfig projectConfig = checkArchiveFiles(taskFiles);
        newTaskInfo.setLanguage(projectConfig.getLanguage());
        newTaskInfo.setTaskName(projectConfig.getProjectName());
        TaskInfo taskInfo = saveNewTaskInfo(newTaskInfo);
        saveTaskDescriptionFile(taskFiles, projectConfig.getGeneralDescriptionFilePath(), taskInfo.getId());
        saveTaskArchive(file, taskInfo.getId());
    }

    private void checkFileExtension(MultipartFile file){
        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".zip")){
            throw new UnsupportedTaskExtensionException();
        }
    }

    private Map<String, byte[]> getArchiveFiles(MultipartFile file){
        try{
            return ZipArchiveUtils.unzipArchiveContent(file.getBytes());
        } catch (IOException e) {
            throw new TaskArchiveReadingException(e);
        }
    }

    private ProjectConfig checkArchiveFiles(Map<String, byte[]> taskFiles) {
        ProjectConfig projectConfig = getProjectConfig(taskFiles);
        checkConfigFile(projectConfig);
        return projectConfig;
    }

    private ProjectConfig getProjectConfig(Map<String, byte[]> files){
        byte[] configFileContent = getConfigFileContent(files);
        return ProjectConfigUtils.readProjectConfig(configFileContent);
    }

    private byte[] getConfigFileContent(Map<String, byte[]> files){
        for (Map.Entry<String, byte[]> entry: files.entrySet()){
            if (checkIsConfigFileRelativePath(entry.getKey())){
                return entry.getValue();
            }
        }
        throw new ConfigNotFoundException();
    }

    private boolean checkIsConfigFileRelativePath(String relativePath){
        String subFolderPath = relativePath.substring(relativePath.indexOf("/") + 1);
        return subFolderPath.endsWith("config.json");
    }

    private void checkConfigFile(ProjectConfig projectConfig) {
        if (!checkIsConfigFileValid(projectConfig)){
            throw new IncompleteConfigException();
        }
    }

    private boolean checkIsConfigFileValid(ProjectConfig projectConfig) {
        return projectConfig.getGeneralDescriptionFilePath() != null
                && projectConfig.getStudyingContentFilePath() != null
                && projectConfig.getTaskTermsFilePath() != null;
    }

    private TaskInfo saveNewTaskInfo(NewTaskInfo newTaskInfo) {
        return taskInfoService.add(TaskInfo.builder()
                        .authorId(newTaskInfo.getAuthorId())
                        .authorName(newTaskInfo.getAuthorName())
                        .language(newTaskInfo.getLanguage())
                        .taskName(newTaskInfo.getTaskName())
                        .additionDate(LocalDateTime.now())
                        .build());
    }

    private void saveTaskDescriptionFile(Map<String, byte[]> files, String descriptionFilePath, Long taskId) {
        descriptionFilePath = prepareTaskDescriptionFilePath(files, descriptionFilePath);
        byte[] descriptionFileBytes = files.get(descriptionFilePath);
        String descriptionFileContent = prepareTaskDescriptionContent(descriptionFileBytes, descriptionFilePath);
        taskDescriptionFileService.add(TaskDescriptionFile.builder()
                    .taskId(taskId)
                    .content(descriptionFileContent)
                    .build());
    }

    private String prepareTaskDescriptionFilePath(Map<String, byte[]> files, String descriptionFilePath){
        descriptionFilePath = getTaskFolder(files) + File.separator + descriptionFilePath;
        return descriptionFilePath.replace(File.separatorChar, '/');
    }

    private String prepareTaskDescriptionContent(byte[] descriptionFileBytes, String descriptionFilePath){
        String descriptionFileContent = new String(descriptionFileBytes);
        if (descriptionFilePath.endsWith(".md")){
            descriptionFileContent = Processor.process(descriptionFileContent);
        }
        return descriptionFileContent;
    }

    private String getTaskFolder(Map<String, byte[]> files){
        for (String path: files.keySet()){
            return path.substring(0, path.indexOf("/"));
        }
        return "";
    }

    private void saveTaskArchive(MultipartFile file, Long taskId){
        try{
            taskArchiveService.add(TaskArchive.builder()
                    .taskId(taskId)
                    .archiveName(file.getOriginalFilename())
                    .archive(file.getBytes())
                    .build());
        } catch (Exception e) {
            throw new TaskArchiveReadingException(e);
        }
    }

    @Override
    public TaskArchive getTaskArchiveById(String id) {
        return taskArchiveService.findById(id);
    }

    @Override
    public TaskArchive getTaskArchiveByTaskId(Long taskId) {
        return taskArchiveService.findByTaskId(taskId);
    }

}
